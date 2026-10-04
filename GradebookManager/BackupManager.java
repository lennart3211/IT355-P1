import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.ArrayList;

public class BackupManager {
    public BackupManager(GradeRepository repo) {}

    /**
     * Creates a backup of the student data.
     *
     * @param students   the list of students to back up
     * @param backupFile the file to save the backup to
     * @throws IOException if writing fails
     * @throws GradebookException if the backup fails
     * 
     FIO02-J: Report a failed backup instead of claiming success.
    */
    public BackupInfo createBackup(Path file) throws GradebookException {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(file))) {
            output.writeObject(new ArrayList<>(repo.getAllStudents()));
        } catch (IOException e) {
            throw new GradebookException("Failed to create backup", e);
        }

        try {
            return BackupInfo.from(file);
        } catch (IOException e) {
            throw new GradebookException("Failed to get backup info", e);
        }
    }

    public List<Student> restoreBackup(Path file, BackupInfo expected) throws GradebookException {}
}

public final class BackupInfo implements Serializable {
    public BackupInfo(long size, FileTime modified, Object fileKey) {}
    public boolean matches(Path file) throws IOException {}
}