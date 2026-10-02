/**
 * Recommendation: Do not assume that the remainder operator always returns a nonnegative result for integral operands.
 *
 * @author Kimlay Neng
 */
public class NUM51 {

    public static int getPositiveRemainder(int number, int divisor) {
        int remainder = number % divisor;
        if(remainder < 0){
            remainder += Math.abs(divisor);
        }
        return remainder;
    }

    public static void main(String[] args) {
        int number = -5;
        int divisor = 3;

        int result = getPositiveRemainder(number, divisor);
        System.out.println("Remainder: " + result);
    }
}