/**
 * Rule: Validate method arguments
 *
 * @author Lennart
 */


/**
 * Demonstrates validating method arguments before setting instance variables.
 */
public class MET00 {
    private String string = null;

    /**
     * Sets the value of the instance variable 'string' after validating the input.
     *
     * @param str the string to set
     */
    public void setStr(String str) {

        // Validate the method argument before setting the instance variable
        if (str == null) {
            System.err.println("Error: str cannot be null");
        }
        else {
            this.string = str;
        }
    }

    /**
     * Main method demonstrating the usage of the setStr method with validation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MET00 met00 = new MET00();
        met00.setStr(null);
        met00.setStr("Hello");
    }
}