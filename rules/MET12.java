public class MET12 implements AutoCloseable {
    private boolean isOpen = true;

    /**
     *  Closes the connection safely instead of relying on finalizers
     */
    @Override 
    public void close() {
        if (isOpen) {
            isOpen = false;
        }
    }
}

