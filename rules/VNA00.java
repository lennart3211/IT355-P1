package rules;
/**
  * Demonstrates VNA00-J.
  * volatile makes changes to a shared variable
  * visible between threads.
  *
  * @author Jocelyn Jude
  */
public class VNA00 {

    private volatile boolean running = true;

    /**
     * Runs while the program is active.
     */
    public void work() {

        while (running) {
            System.out.println("Working...");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Stopped.");
    }

    /**
     * Stops the worker.
     */
    public void stop() {
        running = false;
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args)
            throws InterruptedException {

        VNA00 example = new VNA00();

        Thread worker = new Thread(example::work);

        worker.start();

        Thread.sleep(1500);

        example.stop();

        worker.join();
    }
}