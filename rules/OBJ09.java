package rules;
 /**
  * Demonstrates OBJ09-J.
  * The program compares Class objects instead
  * of comparing class names as strings.
  *
  * @author Jocelyn Jude
  */
public class OBJ09 {

    /**
     * Represents a student.
     */
    static class Student {
    }

    /**
     * Checks whether an object is a Student.
     *
     * @param object object being checked
     * @return true if the object is a Student
     */
    public static boolean isStudent(Object object) {

        return object != null
                && object.getClass() == Student.class;
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        Student student = new Student();

        System.out.println(
                "Is student? " + isStudent(student));
    }
}