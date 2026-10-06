import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.nio.charset.StandardCharsets;

/**
 * Exports student grade reports to a file.
 * Each line in the report contains a student's name and their average grade, separated by a comma.
 */
public class ReportExporter {
    private final GradeRepository repo;

    /**
     * Constructs a ReportExporter with the specified grade repository.
     *
     * @param repo the grade repository to export reports from
     */
    public ReportExporter(GradeRepository repo) {
        this.repo = repo;
    }

    /**
     * Exports the student grade report to the specified file.
     *
     * @param target the path to the target file
     * @return the path to the exported report file
     * @throws GradebookException if an error occurs while exporting the report
     */
    public Path export(Path target) throws GradebookException {
        List<Student> students = repo.getAllStudents();
        String report = format(students);
        try {
            Files.writeString(target, report, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new GradebookException("Failed to export report", e);
        }
        return target;
    }

    /**
     * Formats the student grade report as a string.
     *
     * @param students the list of students to include in the report
     * @return the formatted report as a string
     */
    public String format(List<Student> students) {
        StringBuilder report = new StringBuilder();
        for (Student student : students) {
            report.append(student.getName()).append(",").append(GradeStats.average(student.getGrades())).append("\n");
        }
        return report.toString();
    }
}