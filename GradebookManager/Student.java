public final class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    public Student(int id, String name) {}
    public int getId() {}
    public String getName() {}
    public List<Integer> getGrades() {}
    public void addGrade(int score) {}
    public boolean hasSameGrades(Student other) {}
    @Override public boolean equals(Object o) {}
    @Override public int hashCode() {}
    @Override public String toString() {}
}