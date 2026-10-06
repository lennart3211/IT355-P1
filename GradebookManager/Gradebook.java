/**
 * Handles the main gradebook operations.
 */
public class Gradebook {
    private final GradeRepository repo;

    /**
     * Creates a gradebook using the given repository.
     *
     * @param repo the repository used to store students
     */
    public Gradebook(GradeRepository repo) {
        this.repo = repo;
    }

    /**
     * Adds a new student to the gradebook.
     *
     * @param name the student's name
     */
    public void addStudent(String name) {

        // MET00-J: Validate the method argument.
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Student name cannot be empty."
            );
        }
      
        // MET01-J: Use an if statement instead of an assertion.
        repo.addStudent(name);
    }

    /**
     * Adds a grade to an existing student.
     *
     * @param studentName the student's name
     * @param grade the student's grade
     */
    public void addGrade(String studentName, float grade) {
        // MET00-J: Validate the student's name.
        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException(
                "Student name cannot be empty."
            );
        }

        // MET00-J: Validate the grade.
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException(
                "Grade must be between 0 and 100."
            );
        }

        if (grade != (int) grade) {
            throw new IllegalArgumentException(
                "Grade must be a whole number."
            );
        }

        // ERR08-J: We check for a missing student instead of
        // catching a NullPointerException.
        Student student = repo.findStudentsByName(studentName)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                            "Student not found."
                        ));

        repo.addGrade(student.getId(), (int) grade);
    }
}

    