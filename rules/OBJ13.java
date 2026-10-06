package rules;
/**
  * Demonstrates OBJ13-J.
  * The program returns a copy of a mutable array
  * instead of exposing the original array.
  *
  * @author Jocelyn Jude
  */
public class OBJ13 {

    private String[] courses = {
        "Java", "AI", "Security"
    };

    /**
     * Returns a copy of the courses.
     *
     * @return copy of courses
     */
    public String[] getCourses() {
        return courses.clone();
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        OBJ13 student = new OBJ13();

        String[] courses = student.getCourses();

        courses[0] = "Changed";

        System.out.println(
                "Original course: "
                + student.getCourses()[0]);
    }
}