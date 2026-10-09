public class TPS04 implements Runnable {
    private static final ThreadLocal<String> user = ThreadLocal.withInitial(() -> "deafult");
    private final String userID;

    /**
     * Construct task worker with specific user ID
     * 
     * @param userId the user identifier for the task
     */
    public TPS04(String userID){
        this.userID = userID;
    }

    @Override
    public void run(){
        try {
            user.set(userID);
            /**
             * Perform operations 
             */
        } finally {
            /**
             * Ensure ThreadLocal is cleard to prevent data leakage
             */
            user.remove();
        }
    }
}
