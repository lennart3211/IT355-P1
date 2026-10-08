import java.util.List;
/**
 * Provide methods for calculating stats from student grades
 */
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
   /**
     * Finds the highest grade
     *
     * @param grades list of grades
     * @return the highest grade
     */
    public static int highest(List<Integer> grades) {
        if (grades == null || grades.isEmpty()) {
            return 0;
        }
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    /**
     * Finds the lowest grade.
     *
     * @param grades list of grades
     * @return the lowest grade
     */
    public static int lowest(List<Integer> grades) {
        if (grades == null || grades.isEmpty()){
            return 0;
        }
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
    private GradeStats() {}
}
