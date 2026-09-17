/**
 * Rule: Use braces for the body of an if, for, or while statement
 * 
 * @author Lennart
 */

public class EXP52 {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("no arguments");
        } else {
            System.out.println(args[0]);
        }
    }
}