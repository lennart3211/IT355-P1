package recomendations;

import java.util.Objects;

/**
 * Demonstrates using exceptions only for exceptional conditions.
 */
public class err50 {

	/**
	 * Adds all values in an array using ordinary loop control.
	 * An empty array is valid input and produces a sum of zero.
	 *
	 * @param values the values to add
	 * @return the sum of the values, or zero for an empty array
	 * @throws NullPointerException if {@code values} is {@code null}
	 */
	public static int sumValues(int[] values) {
		Objects.requireNonNull(values, "Values array must not be null");

		int sum = 0;
		for (int value : values) {
			sum += value;
		}
		return sum;
	}

	/**
	 * Demonstrates normal processing for both populated and empty arrays.
	 *
	 * @param args command-line arguments, which are not used
     * 
     * @author Caleb
	 */
	public static void main(String[] args) {
		int[] populatedValues = {4, 7, 2};
		int[] emptyValues = {};

		System.out.println("Populated array sum: " + sumValues(populatedValues));
		System.out.println("Empty array sum: " + sumValues(emptyValues));
		System.out.println("Empty array handled normally: "
				+ (sumValues(emptyValues) == 0));
	}
}
