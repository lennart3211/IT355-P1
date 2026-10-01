import java.io.Serializable;

/**
 * Rule: Do not serialize instances of inner classes.
 *
 * @author Kimlay Neng
 */
public class SER05 {

    //static nested class does not have an implicit reference to an outer class instance.
    static class Grade implements Serializable {

        private static final long serialVersionUID = 1L; //id use for serialization
        private double score;

        public Grade(double score){
            this.score = score;
        }

        public double getScore(){
            return score;
        }
    }

    public static void main(String[] args) {
        Grade grade = new Grade(90.0);
        System.out.println("Grade: " + grade.getScore());
    }
}