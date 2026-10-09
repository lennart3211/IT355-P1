public class LCK01 {
    /**
     * Use dedicated, private lock object
     */
    private final Object lock = new Object();
    private int count = 0;
    /** 
     * Safely increments counter
     */
    public void increment(){
        synchronized(lock){
            count++;
        }
    }   
}

