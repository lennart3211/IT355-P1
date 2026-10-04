import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private List<Integer> grades;

    public Student(int id, String name) {
        this(id, name, List.of());
    }

    public Student(int id, String name, List<Integer> grades) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>(grades);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
    public List<Integer> getGrades() {
        return grades;
    }

    public void addGrade(int score) {}
    public boolean hasSameGrades(Student other) {
        return false;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override public int hashCode() {
        return 0;
    }
    
    @Override public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }
}