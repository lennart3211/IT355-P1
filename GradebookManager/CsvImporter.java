import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.List;

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
     * Imports grades from the specified CSV file into the grade repository.
     *
     * @param csvFile the path to the CSV file
     * @return the number of characters read from the CSV file
     * @throws GradebookException if an error occurs while importing the CSV file
     */
    public int importFile(Path csvFile) throws GradebookException {
        int count = 0;
        System.out.println("Importing CSV file: " + csvFile);

        try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
            String line;

            while ((line = readLine(reader)) != null) {
                count += line.length();
                String[] parts = line.split(",");

                Optional<Student> studentOpt = repo.findStudentsByName(parts[0]);
                int studentId = studentOpt.isPresent() ? studentOpt.get().getId() : repo.addStudent(parts[0]);
                repo.addGrade(studentId, Integer.parseInt(parts[1]));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error importing CSV file: " + e.getMessage());
            throw new GradebookException("Failed to import CSV file", e);
        }

        return count;
    }
}