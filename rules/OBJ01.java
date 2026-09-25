package rules;
/**
 * Demonstrates OBJ01-J by keeping a counter's field private and
 * controlling changes through methods.
 */
public class OBJ01 {
    private int count;

    /**
     * Returns the current counter value.
     *
     * @return the nonnegative count
     */
    public int getCount() {
        return count;
    }

    /**
     * Increases the counter by one.
     *
     * @return the updated count
     * @throws ArithmeticException if the count cannot be increased
     */
    public int increment() {
        if (count == Integer.MAX_VALUE) {
            throw new ArithmeticException("Counter cannot increase further");
        }
        count++;
        return count;
    }

    /**
     * Decreases the counter by one if it is greater than zero.
     *
     * @return true if decreased; false if already zero
     */
    public boolean decrement() {
        if (count == 0) {
            return false;
        }
        count--;
        return true;
    }

    /**
     * Demonstrates that the counter cannot be decreased below zero.
     *
     * @param args command-line arguments, which are not used
     * 
     * @author Caleb
     */
    public static void main(String[] args) {
        OBJ01 counter = new OBJ01();

        System.out.println("Initial count: " + counter.getCount());
        counter.increment();
        counter.increment();
        System.out.println("After two increments: " + counter.getCount());

        counter.decrement();
        System.out.println("After one decrement: " + counter.getCount());

        counter.decrement();
        boolean decreasedAtZero = counter.decrement();
        System.out.println("Attempted decrement at zero: " + decreasedAtZero);
        System.out.println("Final count: " + counter.getCount());
    }
}
