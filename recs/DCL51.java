/**
 * Rule: Do not shadow or obscure identifiers in subscopes
 * 
 * @author Lennart
 */

import java.util.Arrays;

/**
 * Demonstrates avoiding shadowing or obscuring identifiers in subscopes.
 */
public class DCL51 {
    private int i;

    /**
     * Calculates the sum of the elements in the given array.
     *
     * @param nums the array of integers to sum
     * @return the sum of the elements in the array
     */
    public static int sum(int nums[]) {
        int result = 0;

        // using j as iterator in order to not shadow class member i
        for (int j = 0; j < nums.length; ++j) {
            result += nums[j];
        }   
        return result;
    }

    /**
     * Main method demonstrating the usage of the sum method and avoiding shadowing class members.
     *
     * @param args command-line arguments
     */
    public static void main(String args[]) {
        int nums[] = { 0, 1, 2, 3 };
        System.out.println("Sum of " + Arrays.toString(nums) + " is " + DCL51.sum(nums));
    }
}