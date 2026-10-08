/**
 * Rule: Convert integers to floating point for floating-point operations
 * 
 * @author Lennart
 */

/**
 * Demonstrates converting integers to floating point for floating-point operations.
 */
public class NUM50 {

    /**
     * Main method demonstrating converting integers to floating point for floating-point operations.
     *
     * @param args command-line arguments
     */
    public static void main(String args[]) {
        int a = 5;
        float b = 2.f;

        // a is cast to float before the division
        float c = (float)a / b;
        System.out.println(a + "/" + b + " = " + c);
    }
}