import java.io.Serializable;
import java.util.List;

public final class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int grade;
    private int id;

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getGrade() {
        return grade;
    }
    
    public List<Integer> getGrades() {
        return null;
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
                ", grade=" + grade +
                '}';
    }
}