/**
 * Rule: Do not use public static nonfinal fields.
 *
 * @author Kimlay Neng
 */
public class OBJ10 {

    //public static field is final
    public static final int MAX_GRADE = 100;

    public static boolean isValidGrade(int grade){
        return grade >= 0 && grade <= MAX_GRADE;
    }

    public static void main(String[] args){
        int grade = 95;
        if(isValidGrade(grade)){
            System.out.println(grade + " is a valid grade.");
        } else {
            System.out.println(grade + " is not a valid grade.");
        }
    }
}