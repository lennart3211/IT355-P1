import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.List;
import java.nio.file.NoSuchFileException;


/**
 * Imports grades from a CSV file into the grade repository.
 * Each line in the CSV file should contain a student's name and a grade, separated by a comma.
 */
public class CsvImporter {
    private final GradeRepository repo;

    /**
     * Constructs a CsvImporter with the specified grade repository.
     *
     * @param repo the grade repository to import grades into
     */
    public CsvImporter(GradeRepository repo) {
        this.repo = repo;
    }

    /**
     * Reads a single line from the specified BufferedReader.
     *
     * @param reader the BufferedReader to read from
     * @return the line read, or null if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     */
    private String readLine(BufferedReader reader) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            char ch = (char) c;
            if (ch == '\n') {
                return line.toString();
            }
            if (ch != '\r') {
                line.append(ch);
            }
        }
        return line.length() > 0 ? line.toString() : null;
    }

    /**
     * Imports grades from the specified CSV file.
     *
     * @param csvFile the path to the CSV file
     * @return the number of successfully imported rows
     * @throws GradebookException if an error occurs during import
     */
    public int importFile(Path csvFile) throws GradebookException {
        if (csvFile == null) {                               
            throw new IllegalArgumentException("csvFile must not be null");
        }
        int imported = 0;
        int lineNumber = 0;

        try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
            String line;
            while ((line = readLine(reader)) != null) {
                lineNumber++;
                if (lineNumber == 1 && line.trim().equalsIgnoreCase("name,score")) {
                    continue;       
                }
                if (importRow(line)) {
                    imported++;
                } else {
                    Logger.info("Skipped invalid row " + lineNumber);
                }
            }
        } catch (NoSuchFileException e) {
            throw new GradebookException("CSV file not found.", e);
        } catch (IOException e) {
            Logger.error("CSV import failed", e);
            throw new GradebookException("Could not read the CSV file.", e);
        }
        return imported;
    }

    private boolean importRow(String line) throws GradebookException {
        String[] parts = line.split(",");
        if (parts.length != 2) {
            return false;
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            return false;
        }
        int score;
        try {
            score = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            return false;
        }
        if (score < AppConfig.MIN_SCORE || score > AppConfig.MAX_SCORE) {
            return false;
        }

        Optional<Student> existing = repo.findStudentsByName(name);
        int studentId = existing.isPresent() ? existing.get().getId() : repo.addStudent(name);
        repo.addGrade(studentId, score);
        return true;
    }
}