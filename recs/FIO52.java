package recs;
import java.security.SecureRandom;

/**
 * Demonstrates FIO52-J.
 * The program creates a random session token
 * instead of storing a password on the client.
 *
 * @author Jocelyn Jude
 */
public class FIO52 {

    /**
     * Creates a random session token.
     *
     * @return random token
     */
    public static String createToken() {

        SecureRandom random = new SecureRandom();

        return String.valueOf(random.nextInt());
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        String username = "Jocelyn";

        // Do not store the user's password.
        String token = createToken();

        System.out.println("User: " + username);
        System.out.println("Session token: " + token);
    }
}