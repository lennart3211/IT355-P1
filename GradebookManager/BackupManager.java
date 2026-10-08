import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BackupManager {
    private final GradeRepository repo;

    /**
     * Creates a new backup manager.
     *
     * @param repo the grade repository to manage
     */
    public BackupManager(GradeRepository repo) {
         this.repo = repo;
    }

    /**
     * Creates a backup of the student data and records the saved file's metadata.
     * File-operation failures are reported instead of claiming success.
     *
     * @param file the destination backup file
     * @return metadata describing the completed backup file
     * @throws GradebookException if the repository returns no student list, writing fails, or the backup metadata cannot be read
     * 
     * FIO02-J: Report a failed backup instead of claiming success.
    */
    public BackupInfo createBackup(Path file) throws GradebookException {
        List<Student> students = repo.getAllStudents();

        if (students == null) {
            throw new GradebookException("The repository didn't return a student list");
        }

        List<Student> snapshot = new ArrayList<>(students);

        Path temporaryFile = null;

        try {
            Path destination = file.toAbsolutePath();

            temporaryFile = Files.createTempFile(destination.getParent(), "gradebook-", ".tmp");

            try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(temporaryFile))) {
                output.writeObject(snapshot);
            }

            Files.move( temporaryFile, destination, java.nio.file.StandardCopyOption.ATOMIC_MOVE);

        } catch (IOException e) {
            if (temporaryFile != null) {
                try {
                    Files.deleteIfExists(temporaryFile);
                } catch (IOException deleteException) {
                    // Preserve the cleanup failure alongside the original failure.
                    e.addSuppressed(deleteException);
                }
            }
            throw new GradebookException("Failed to create backup", e);
        }

        try {
            return BackupInfo.from(file);
        } catch (IOException e) {
            throw new GradebookException("Failed to get backup info", e);
        }
    }

    /**
     * Restores students from a backup whose metadata matches the expected values
     * and replaces the repository's current student data.
     *
     * @param file the backup file to restore from
     * @param expected the recorded metadata to compare against the backup file
     * @return the list of restored students
     * @throws GradebookException if expected metadata is missing or doesn't match, reading fails, a saved class is unavailable, arithmetic errors occur, or the backup doesn't contain a list of students
     */
     public List<Student> restoreBackup(
            Path file, BackupInfo expected) throws GradebookException {

        try {
            if (expected == null || !expected.matches(file)) {
                throw new GradebookException(
                        "Backup file does not match expected metadata");
            }

            try (ObjectInputStream input =
                         new ObjectInputStream(Files.newInputStream(file))) {

                Object savedData = input.readObject();

                if (!(savedData instanceof List<?>)) {
                    throw new InvalidObjectException(
                            "Backup does not contain a student list");
                }

                List<?> savedStudents = (List<?>) savedData;
                List<Student> restoredStudents =
                        new ArrayList<>(savedStudents.size());

                for (Object item : savedStudents) {
                    if (!(item instanceof Student)) {
                        throw new InvalidObjectException(
                                "Backup contains an item that is not a student");
                    }

                    restoredStudents.add((Student) item);
                }

                repo.replaceAll(restoredStudents);
                return restoredStudents;
            }

        } catch (GradebookException exception) {
            throw exception;
        } catch (IOException | ClassNotFoundException | ArithmeticException exception) {
            throw new GradebookException(
                    "Failed to restore backup", exception);
        }
    }
}