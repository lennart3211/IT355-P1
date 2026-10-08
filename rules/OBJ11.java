/**
 * Rule: Be wary of letting constructors throw exceptions
 *
 * @author Lennart
 */

/**
 * Declaring the class final prevents subclassing, which blocks the finalizer attack
 */
final class SomeClass {
    public SomeClass(int num) throws Exception {
        if (num != 5) {
            throw new Exception("Wrong number");
        }
    }
}

/**
 * Demonstrates being cautious about letting constructors throw exceptions and handling them properly.
 */
public class OBJ11 {

    /**
     * Main method demonstrating being cautious about letting constructors throw exceptions and handling them properly.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            SomeClass valid = new SomeClass(5);
            System.out.println("Constructed successfully: " + valid);
        } catch (Exception e) {
            System.out.println("Unexpected failure: " + e.getMessage());
        }

        try {
            SomeClass invalid = new SomeClass(1);
            System.out.println("Constructed successfully: " + invalid);
        } catch (Exception e) {
            System.out.println("Construction failed as expected: " + e.getMessage());
        }
    }
}