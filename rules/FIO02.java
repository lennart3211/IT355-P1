package rules;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * Demonstrates detecting and handling file-related errors using
 * a temporary file created by this program.
 */
public class FIO02 {

    /**
     * Creates a temporary file, deletes it, and handles the expected
     * failure when deletion is attempted a second time.
     *
     * @throws IOException if file creation, deletion, or cleanup fails
     */
    public static void runDemo() throws IOException {
        Path temporaryFile = Files.createTempFile("fio02-demo-", ".txt");
        System.out.println("Created: " + temporaryFile.getFileName());

        try {
            Files.delete(temporaryFile);
            System.out.println("First deletion: succeeded");

            try {
                Files.delete(temporaryFile);
                System.out.println("Second deletion: succeeded unexpectedly");
            } catch (NoSuchFileException exception) {
                System.out.println(
                    "Second deletion: failed as expected; file does not exist"
                );
            }
        } finally {
            // Remove the demonstration file if an earlier step left it behind.
            if (Files.deleteIfExists(temporaryFile)) {
                System.out.println("Cleanup: removed remaining file");
            }
        }
    }

    /**
     * Runs the demonstration and reports an unexpected I/O failure.
     *
     * @param args command-line arguments, which are not used
     * 
     * @author Caleb
     */
    public static void main(String[] args) {
        try {
            runDemo();
        } catch (IOException exception) {
            System.err.println("File operation failed: " + exception.getMessage());
        }
    }
}
