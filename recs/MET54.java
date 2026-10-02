/**
 * Recommendation: Always provide feedback about the resulting value of a method
 *
 * @author Kimlay Neng
 */
public class MET54 {

    public static boolean updateGrade(int oldGrade, int newGrade) {

        if (newGrade >= 0 && newGrade <= 100){
            System.out.println("Grade changed from "+ oldGrade + " to " + newGrade);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        boolean result = updateGrade(80, 101);
        if(result){
            System.out.println("Update successful.");
        } else{
            System.out.println("Update failed.");
        }
    }
}