import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class ReportExporter {
    private final GradeRepository repo;

    public ReportExporter(GradeRepository repo) {
        this.repo = repo;
    }

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

    public String format(List<Student> students) {
        StringBuilder report = new StringBuilder();
        for (Student student : students) {
            report.append(student.getName()).append(",").append(GradeStats.average(student.getGrades())).append("\n");
        }
        return report.toString();
    }
}