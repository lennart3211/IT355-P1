package recomendations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates avoiding ambiguous or confusing uses of overloading.
 */
public class met50 {
	private final List<Integer> values;

	/**
	 * Creates a collection containing the supplied values.
	 *
	 * @param initialValues the values to store
	 */
	public met50(Integer... initialValues) {
		values = new ArrayList<>(Arrays.asList(initialValues));
	}

	/**
	 * Removes the value at a specified list index.
	 *
	 * @param index the zero-based index to remove
	 * @return the value removed from that index
	 * @throws IndexOutOfBoundsException if the index is outside the list
	 */
	public Integer removeByIndex(int index) {
		return values.remove(index);
	}

	/**
	 * Removes the first occurrence of a specified value.
	 *
	 * @param value the value to remove
	 * @return {@code true} if a matching value was removed; {@code false}
	 *         otherwise
	 */
	public boolean removeByValue(Integer value) {
		return values.remove(value);
	}

	/**
	 * Returns a readable view of the current values for the demonstration.
	 *
	 * @return the current values
	 */
	private String valuesAsText() {
		return values.toString();
	}

	/**
	 * Demonstrates explicit method names for index-based and value-based
	 * removal.
	 *
	 * @param args command-line arguments, which are not used
     * 
     * @author Caleb
	 */
	public static void main(String[] args) {
		met50 numbers = new met50(10, 20, 30);

		Integer removedByIndex = numbers.removeByIndex(1);
		boolean removedByValue = numbers.removeByValue(30);

		System.out.println("Removed by index: " + removedByIndex);
		System.out.println("Removed by value: " + removedByValue);
		System.out.println("Remaining values: " + numbers.valuesAsText());
	}
}
