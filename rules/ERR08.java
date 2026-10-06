package rules;
/**
  * Demonstrates ERR08-J.
  * The program checks for null instead of
  * catching NullPointerException.
  *
  * @author Jocelyn Jude
  */
public class ERR08 {

    /**
     * Prints the length of a name.
     *
     * @param name the person's name
     */
    public static void printName(String name) {

        if (name == null) {
            System.out.println("Name is missing.");
            return;
        }

        System.out.println(
                "Name length: " + name.length());
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        printName("Jocelyn");
        printName(null);
    }
}