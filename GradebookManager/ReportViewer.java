import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ReportViewer {

    public enum Viewer {
        NOTEPAD, LESS, CAT
    }

    /**
     * Opens the specified report using a trusted viewer.
     *
     * @param report the report file to open
     * @param viewer the trusted viewer to use
     * @throws GradebookException if the report cannot be opened
     */
    public void open(Path report, Viewer viewer) throws GradebookException {

        if (report == null || viewer == null) {
            throw new GradebookException("Invalid report or viewer.");
        }

        if (!Files.exists(report)) {
            throw new GradebookException("Report file does not exist.");
        }

        String command;

        switch (viewer) {
            case NOTEPAD:
                command = "notepad.exe";
                break;

            case LESS:
                command = "less";
                break;

            case CAT:
                command = "cat";
                break;

            default:
                throw new GradebookException("Invalid viewer.");
        }

        try {
            // The command comes only from the trusted Viewer whitelist.
            Runtime.getRuntime().exec(
                new String[] { command, report.toString() }
            );
        } catch (IOException e) {
            throw new GradebookException("Failed to open report.", e);
        }
    }

    /**
     * Returns the list of trusted viewers available to the application.
     *
     * @return list of available viewers
     */
    public static List<Viewer> availableViewers() {
        return Arrays.asList(Viewer.values());
    }
}
