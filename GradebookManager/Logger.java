import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Application Logger
 * 
 * ERR01-J:
 * Prevents sensitive information from being exposed
 * 
 * ERR02-J:
 * Prevent exceptions while logging data
 */
public final class Logger {

    private static final DateTimeFormatter FORMAT =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {
        // OBJ51-J:
        // Prevents instantiation
    }

    /**
     * Logs a mesasge
     * 
     * @param msg message to log
     */
    public static void info(String msg) {

        try {

            String message = String.valueOf(msg);

            System.out.println("[INFO] " + LocalDateTime.now().format(FORMAT)
                                + " - " + message);
        } catch (Exception ignored) {

            //ERR02-J
            //Logging won't crash the application
        }
    }

    /**
     * Logs an error
     * 
     * @param msg error message
     * @param t excepetion that occurred
     */
    public static void error(String msg, Throwable t) {

        try {

            String message = String.valueOf(msg);

            System.err.println("[ERROR] " + LocalDateTime.now().format(FORMAT) 
                                            + " - " + message);

            if (t != null) {

                System.err.println("Cause: " + t.getClass().getSimpleName());
                System.err.println("Details: " + userMessage(t));
            }
        } catch (Exception ignored) {
            //ERR02-J:
            //Prevents logging operations from creating additional errors
        }
    }

    /**
     * Returns a safe user message
     * 
     * ERR01-J:
     * Avoid exposing file paths, database information, etc.
     * 
     * @param t reported exception
     * @return safe message for users
     */
    public static String userMessage(Throwable t) {

        if (t instanceof GradebookException) {
            return t.getMessage();
        }

        return "An unexpected error occurred.";
    }
}
