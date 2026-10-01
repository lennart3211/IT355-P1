import java.util.Optional;
import java.util.List;

public class GradeRepository implements AutoCloseable {
    public GradeRepository(String dbUrl) throws GradebookException {}
    public int addStudent(String name) {
        return 0;
    }
    public void addGrade(int studentId, int score) {}
    public Optional<Student> findStudentsByName(String name) {
        return Optional.empty();
    }
    public Optional<Student> findStudentById(int id) {
        return Optional.empty();
    }
    public List<Student> getAllStudents() {
        return null;
    }
    public void replaceAll(List<Student> students) {}
    public boolean deleteStudent(int id) {
        return false;
    }
    @Override public void close() {}
}