import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Recommendation: Use a try-with-resources statement to safely handle closeable resources.
 *
 * @author Kimlay Neng
 */
public class ERR54 {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new FileReader("hello.txt"))) {
            System.out.println(reader.readLine());
        } catch (IOException e){
            System.out.println("Error reading file.");
        }
    }
}