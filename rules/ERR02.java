/**
 * Rule: Prevent exceptions while logging data
 *
 * @author Lennart
 */

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Demonstrates safe logging of exceptions using java.util.logging.
 */
public class ERR02 {

    // Logger for this class
    private static final Logger logger = Logger.getLogger(ERR02.class.getName());

    /**
     * Main method demonstrating safe exception logging.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
        } catch (Exception e) {

            // Using logger instead of printing the stack trace
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}