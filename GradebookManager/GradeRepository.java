import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores students and grades while the program is running.
 */
public class GradeRepository {

    private final List<Student> students;
    private int nextId;

    /**
     * Creates an empty grade repository.
     */
    public GradeRepository() {
        students = new ArrayList<>();
        nextId = 1;
    }

        
    /**
     * Adds a new student to the repository.
     *
     * @param name the student's name
     * @return the ID assigned to the student
     */
    public int addStudent(String name) {
        Student student = new Student(nextId, name);
        students.add(student);
        nextId++;

        return student.getId();
    }

    /**
     * Adds a grade to an existing student.
     *
     * @param studentId the student's ID
     * @param score the grade to add
     */
    public void addGrade(int studentId, int score) {
        Optional<Student> student = findStudentById(studentId);

        if (student.isEmpty()) {
            throw new IllegalArgumentException(
                "Student not found."
            );
        }

        student.get().addGrade(score);
    }

    /**
     * Finds a student by name.
     *
     * @param name the student's name
     * @return the student if found
     */
    public Optional<Student> findStudentsByName(String name) {
        for (Student student : students) {
            // EXP52-J: Use braces for the body of if statements.
            if (student.getName().equals(name)) {
                return Optional.of(student);
            }
        }

        return Optional.empty();
    }

    /**
     * Finds a student by ID.
     *
     * @param id the student's ID
     * @return the student if found
     */
    public Optional<Student> findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return Optional.of(student);
            }
        }

        return Optional.empty();
    }

    /**
     * Returns all students in the repository.
     *
     * @return a list of all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Cleans up resources when the gradebook closes.
     *
     * FIO14-J: Perform proper cleanup at program termination.
     */
    public void close() {
        students.clear();
    }

    /**
     * Replaces all students in the repository with the given list.
     *
     * @param restoredStudents the list of students to replace the current list
     * @throws ArithmeticException if the next ID calculation overflows
     */
    public void replaceAll(List<Student> restoredStudents) {
        List<Student> replacement = new ArrayList<>(restoredStudents);

        int highestId = 0;
        for (Student student : replacement) {
            highestId = Math.max(highestId, student.getId());
        }

        int updatedNextId = Math.addExact(highestId, 1);

        students.clear();
        students.addAll(replacement);
        nextId = updatedNextId;
    }
}