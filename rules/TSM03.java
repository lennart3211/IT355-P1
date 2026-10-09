public class TSM03 {
    /**
     * Declares reference as volatile to 
     * ensure safe publication across threads
     */
    private static volatile TSM03 instance;
    private final int val;

    private TSM03(int val) {
        this.val = val;
    }

    /**
     * Retrieve thread safe instance
     * 
     * @param val inital value
     * @return the TSM03 instance
     */
    public static TSM03 getInstance(int value) {
        if (instance == null) {
            synchronized (TSM03.class) {
                if (instance == null) {
                    instance = new TSM03(value);
                }
            }
        }
        return instance;
    }
}

