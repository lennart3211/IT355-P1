import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Creates backups when changes have occured
 * 
 * FIO14-J:
 * Ensures proper cleanup during program termination
 * 
 * MET54-J:
 * Provides feedback via getPendingChanges()
 */
public class AutosaveTask implements Runnable {

    private final BackupManager backups;
    private final Path autosaveFile;

    private volatile boolean running;
    private final AtomicInteger pendingChanges;

    /**
     * Creates a new autosave task
     * 
     * @param backups backup manager creates backups
     * @param autosaveFile destination file for autosaves
     */
    public AutosaveTask(BackupManager backups, Path autosaveFile) {

        if(backups == null){
            throw new IllegalArgumentException("BackupManager cannot be null");
        }

        if (autosaveFile == null) {
            throw new IllegalArgumentException("Autosave file cannot be null");
        }

        this.backups = backups;
        this.autosaveFile = autosaveFile;
        this.running = true;
        this.pendingChanges = new AtomicInteger(0);
    }

    /**    
     * Runs auotsave loop
     * 
     * Checks for pending changes and 
     * writes a backup when necessary
     */
    @Override 
    public void run() {

        Logger.info("Autosave service started.");

        while (running) {
            try {
                Thread.sleep(AppConfig.AUTOSAVE_INTERVAL_MS);
                if (!running) {
                    break;
                }
                
                int changes = pendingChanges.getAndSet(0);

                if (changes > 0) {
                    backups.createBackup(autosaveFile);

                    Logger.info("Autosave completed. Saved " + changes
                                + " pending changes.");
                    pendingChanges.set(0);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                Logger.error("Autosave thread interrupted.", e);
                break;

            } catch (GradebookException e) {

                Logger.error("Autosave failed.", e);
            }
        }
        Logger.info("Autosave service stopped.");
    }

    /**
     * Stops the autosave task
     * 
     * FIO14-J:
     * Ensures proper cleanup during program termination
     */
    public void stop() {
        running = false;
    }

    /**
     * Records that application data has changed
     */
    public void markChanged() {
        pendingChanges.incrementAndGet();
    }

    /**
     * Returns number of unsaved changes
     * 
     * @return number of pending changes
     */
    public int getPendingChanges() {
        return pendingChanges.get();
    }
}
