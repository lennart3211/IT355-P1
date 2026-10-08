/**
 * Rule: Use braces for the body of an if, for, or while statement
 * 
 * @author Lennart
 */

/**
 * Demonstrates using braces for the body of an if, for, or while statement.
 */
public class EXP52 {

    /**
     * Main method demonstrating the usage of braces for the body of an if, for, or while statement.
     *
     * @param args command-line arguments
     */
    public static void main(String args[]) {

        // Both if and else statements use braces for their bodies
        if (args.length < 1) {
            System.out.println("no arguments");
        } else {
            System.out.println(args[0]);
        }
    }
}