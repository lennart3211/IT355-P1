package recs;
/**
  * Demonstrates ERR51-J.
  * The program uses a specific exception
  * instead of a general Exception.
  *
  * @author Jocelyn Jude
  */
public class ERR51 {

    /**
     * Checks whether an age is valid.
     *
     * @param age person's age
     * @throws IllegalArgumentException if age is negative
     */
    public static void checkAge(int age) {

        if (age < 0) {
            throw new IllegalArgumentException(
                    "Age cannot be negative.");
        }
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        try {
            checkAge(-5);

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }
}