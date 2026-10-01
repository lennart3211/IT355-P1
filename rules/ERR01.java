import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Rule: Do not allow exceptions to expose sensitive information.
 *
 * @author Kimlay Neng
 */
public class ERR01 {

    public static void readGradeFile(String fileName) {
        try(BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            System.out.println(reader.readLine());

        } catch (IOException e) {
            //don't show sensitive exception details to the user
            System.out.println("Error: The grade file could not be read.");
        }
    }

    public static void main(String[] args) {
        readGradeFile("grades.txt");
    }
}