package rules;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates an atomic compound update on a shared counter.
 */
public class VNA02 {
    private static final int INCREMENTS_PER_THREAD = 10_000;

    /**
     * Runs two worker threads and verifies that every increment is preserved.
     *
     * @param args command-line arguments, which are not used
     * @throws InterruptedException if the main thread is interrupted while
     *         waiting for a worker to finish
     */
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger sharedCounter = new AtomicInteger();

        Thread firstWorker = createIncrementingThread(sharedCounter, "Worker-1");
        Thread secondWorker = createIncrementingThread(sharedCounter, "Worker-2");

        firstWorker.start();
        secondWorker.start();
        firstWorker.join();
        secondWorker.join();

        int expectedCount = INCREMENTS_PER_THREAD * 2;
        int actualCount = sharedCounter.get();

        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count: " + actualCount);
        System.out.println("Atomic update check passed: "
                + (actualCount == expectedCount));
    }

    /**
     * Creates a thread that atomically increments the shared counter.
     *
     * @param sharedCounter the counter shared by the worker threads
     * @param threadName the name assigned to the new thread
     * @return a thread ready to start
     * 
     * @author Caleb
     */
    private static Thread createIncrementingThread(
            AtomicInteger sharedCounter, String threadName) {
        return new Thread(() -> {
            for (int increment = 0; increment < INCREMENTS_PER_THREAD; increment++) {
                sharedCounter.incrementAndGet();
            }
        }, threadName);
    }
}
