package rules;
 /**
  * Demonstrates THI00-J.
  * The program uses start() instead of run()
  * to start a new thread.
  *
  * @author Jocelyn Jude
  */
public class THI00 implements Runnable {

    /**
     * Code executed by the new thread.
     */
    @Override
    public void run() {
        System.out.println("Thread is running.");
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args)
            throws InterruptedException {

        Thread thread =
                new Thread(new THI00());

        thread.start();

        thread.join();

        System.out.println("Program finished.");
    }
}