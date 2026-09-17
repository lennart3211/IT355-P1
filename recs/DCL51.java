/**
 * Rule: Do not shadow or obscure identifiers in subscopes
 * 
 * @author Lennart
 */

import java.util.Arrays;

public class DCL51 {
    private int i;

    public static int sum(int nums[]) {
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            result += nums[i];
        }   
        return result;
    }

    public static void main(String args[]) {
        int nums[] = { 0, 1, 2, 3 };
        System.out.println("Sum of " + Arrays.toString(nums) + " is " + DCL51.sum(nums));
    }
}