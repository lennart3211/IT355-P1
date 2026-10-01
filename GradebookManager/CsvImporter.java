import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.List;

public class CsvImporter {
    private final GradeRepository repo;

    public CsvImporter(GradeRepository repo) {
        this.repo = repo;
    }

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

    public int importFile(Path csvFile) throws GradebookException {
        int count = 0;

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
            throw new GradebookException("Failed to import CSV file", e);
        }

        return count;
    }
}