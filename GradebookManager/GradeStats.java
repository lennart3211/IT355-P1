import java.util.List;

public final class GradeStats {
    /**
    * Calculates the average of the student's grades.
    * @param grades list of grades
    * @return the average of the grades
    */
    public static double average(List<Integer> grades) {  
        if(grades == null || grades.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return (double) total/grades.size();
    }
    public static int highest(List<Integer> grades) { return 0; }
    public static int lowest(List<Integer> grades) { return 0; }
    private GradeStats() {}
}
