
public class GradeRepository implements AutoCloseable {
    public GradeRepository(String dbUrl) throws GradebookException {}
    public int addStudent(String name) {}
    public void addGrade(int studentId, int score) {}
    public List<Student> findStudentsByName(String name) {}
    public Optional<Student> findStudentById(int id) {}
    public List<Student> getAllStudents() {}
    public void replaceAll(List<Student> students) {}
    public boolean deleteStudent(int id) {}
    @Override public void close() {}
}