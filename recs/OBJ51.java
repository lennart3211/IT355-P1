package recomendations;

/**
 * Demonstrates minimizing the accessibility of a class's members.
 * The public API exposes one operation while its validation and formatting
 * helpers remain private implementation details.
 */
public class obj51 {

	/**
	 * Creates a short completion summary using the class's public operation.
	 *
	 * @param projectName the name of the project
	 * @param completedTasks the number of completed tasks
	 * @return a formatted completion summary
	 * @throws IllegalArgumentException if the project name is blank or the
	 *         task count is negative
	 */
	public String createSummary(String projectName, int completedTasks) {
		validateInput(projectName, completedTasks);
		return formatSummary(projectName, completedTasks);
	}

	/**
	 * Validates input before the public operation builds a summary.
	 *
	 * @param projectName the project name to validate
	 * @param completedTasks the task count to validate
	 * @throws IllegalArgumentException if either input is invalid
	 */
	private void validateInput(String projectName, int completedTasks) {
		if (projectName == null || projectName.isBlank()) {
			throw new IllegalArgumentException("Project name must not be blank");
		}
		if (completedTasks < 0) {
			throw new IllegalArgumentException("Completed tasks cannot be negative");
		}
	}

	/**
	 * Formats the validated data for the public operation.
	 *
	 * @param projectName the validated project name
	 * @param completedTasks the validated task count
	 * @return the formatted summary
	 */
	private String formatSummary(String projectName, int completedTasks) {
		return projectName + " completed tasks: " + completedTasks;
	}

	/**
	 * Demonstrates the narrow public API and its validation behavior.
	 *
	 * @param args command-line arguments, which are not used
     * 
     * @author Caleb
	 */
	public static void main(String[] args) {
		obj51 summary = new obj51();
		System.out.println(summary.createSummary("Secure Java Examples", 5));

		try {
			summary.createSummary("", 5);
		} catch (IllegalArgumentException exception) {
			System.out.println("Invalid input handled: " + exception.getMessage());
		}
	}
}
