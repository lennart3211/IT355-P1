/*
 * Author: Lennart
 * Rule: Be wary of letting constructors throw exceptions
 */

// Declaring the class final prevents subclassing, which blocks the finalizer attack
final class SomeClass {
    public SomeClass(int num) throws Exception {
        if (num != 5) {
            throw new Exception("Wrong number");
        }
    }
}

public class OBJ11 {
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