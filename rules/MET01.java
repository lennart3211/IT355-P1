/**
 * Rule: Never use assertions to validate method arguments.
 *
 * @author Kimlay Neng
 */
public class MET01 {

    public static void addGrade(int grade) {

        //validate the argument instead of using an assertion
        if(grade < 0 || grade > 100){
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        System.out.println("Grade added: " + grade);
    }
    public static void main(String[] args) {
        addGrade(90);
    }
}