import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    // OBJ01-J: Limit accessibility of fields.
    private int id;
    private String name;
    private List<Integer> grades;

    /**
     * Creates a student with no grades.
     *
     * @param id the student's ID
     * @param name the student's name
     */
    public Student(int id, String name) {
        this(id, name, List.of());
    }

    /**
     * Creates a student with an existing list of grades.
     *
     * @param id the student's ID
     * @param name the student's name
     * @param grades the student's grades
     */
    public Student(int id, String name, List<Integer> grades) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>(grades);
    }

    /**
     * Returns the student's ID.
     *
     * @return the student's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the student's name.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the student's grades.
     *
     * @return a copy of the student's grades
     */
    public List<Integer> getGrades() {
        // OBJ05-J / OBJ13-J:
        // Do not expose the original mutable list.
        return new ArrayList<>(grades);
    }

    /**
     * Adds a grade to the student.
     *
     * @param score the grade to add
     */
    public void addGrade(int score) {
        grades.add(score);
    }

    /**
     * Checks if the student has the same grades as another student.
     *
     * @param other the other student
     * @return true if the students have the same grades, false otherwise
     */
    public boolean hasSameGrades(Student other) {
        return grades.equals(other.grades);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override public int hashCode() {
        return Integer.hashCode(id);
    }
    
    @Override public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }
}