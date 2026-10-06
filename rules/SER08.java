package rules;
import java.io.*;
/**
 * Demonstrates SER08-J.
 * The program deserializes an object without
 * using unnecessary privileged operations.
 *
 * @author Jocelyn Jude
 */
public class SER08 {

    /**
     * A simple serializable User object.
     */
    static class User implements Serializable {
        String name;

        User(String name) {
            this.name = name;
        }
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) throws Exception {

        User user = new User("Jocelyn");

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        ObjectOutputStream objectOutput =
                new ObjectOutputStream(output);

        objectOutput.writeObject(user);
        objectOutput.close();

        ObjectInputStream input =
                new ObjectInputStream(
                        new ByteArrayInputStream(output.toByteArray()));

        User restored = (User) input.readObject();
        input.close();

        System.out.println("User: " + restored.name);
    }
}