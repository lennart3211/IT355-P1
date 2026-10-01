public class ReportExporter {
    private final GradeRepository repo;

    public ReportExporter(GradeRepository repo) {
        this.repo = repo;
    }

    public Path export(Path target) throws GradebookException {
        List<Student> students = repo.getAllStudents();
        String report = formatReport(students);
        try {
            Files.writeString(target, report);
        } catch (IOException e) {
            throw new GradebookException("Failed to export report", e);
        }
        return target;
    }

    public String format(List<Student> students) {
        StringBuilder report = new StringBuilder();
        for (Student student : students) {
            report.append(student.getName()).append(",").append(student.getAverageGrade()).append("\n");
        }
        return report.toString();
    }
}