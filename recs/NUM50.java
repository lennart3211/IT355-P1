/**
 * Rule: Convert integers to floating point for floating-point operations
 * 
 * @author Lennart
 */

public class NUM50 {
    public static void main(String args[]) {
        int a = 5;
        float b = 2.f;
        float c = (float)a / b;
        System.out.println(a + "/" + b + " = " + c);
    }
}