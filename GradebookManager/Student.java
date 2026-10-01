import java.io.Serializable;
import java.util.List;

public final class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    public Student(int id, String name) {}
    public int getId() {
        return 0;
    }
    public String getName() {
        return null;
    }
    public List<Integer> getGrades() {
        return null;
    }
    public void addGrade(int score) {}
    public boolean hasSameGrades(Student other) {
        return false;
    }
    @Override public boolean equals(Object o) {
        return false;
    }
    @Override public int hashCode() {
        return 0;
    }
    @Override public String toString() {
        return null;
    }
}