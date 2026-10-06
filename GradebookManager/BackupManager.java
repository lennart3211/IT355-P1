import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.ArrayList;

public class BackupManager {
    public BackupManager(GradeRepository repo) {}

    /**
     * Creates a backup of the student data.
     *
     * @param students   the list of students to back up
     * @param backupFile the file to save the backup to
     * @throws IOException if writing fails
     * @throws GradebookException if the backup fails
     * 
     FIO02-J: Report a failed backup instead of claiming success.
    */
    public BackupInfo createBackup(Path file) throws GradebookException {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(file))) {
            output.writeObject(new ArrayList<>(repo.getAllStudents()));
        } catch (IOException e) {
            throw new GradebookException("Failed to create backup", e);
        }

        try {
            return BackupInfo.from(file);
        } catch (IOException e) {
            throw new GradebookException("Failed to get backup info", e);
        }
    }

    /**
     * 
     * @param backupFile the file to restore from
     * @param expected   the expected backup info to validate against
     * @return the list of restored students
     * @throws GradebookException if the restore fails
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
        } catch (IOException | ClassNotFoundException exception) {
            throw new GradebookException(
                    "Failed to restore backup", exception);
        }
    }
}

public final class BackupInfo implements Serializable {
    public BackupInfo(long size, FileTime modified, Object fileKey) {}
    public boolean matches(Path file) throws IOException {}
}