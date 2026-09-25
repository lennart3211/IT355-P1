/**
 * Rule: Do not use the Object.equals() method to compare two arrays.
 *
 * @author Kimlay Neng
 */

import java.util.Arrays;

public class EXP02 {

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};

        //compares the contents of both arrays
        if (Arrays.equals(arr1, arr2)) {
            System.out.println("The arrays contain the same values.");
        } else {
            System.out.println("The arrays contain different values.");
        }
    }
}