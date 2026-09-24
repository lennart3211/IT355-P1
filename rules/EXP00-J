/**
 * Rule: Do not ignore values returned by methods; check or use return values
 * to properly handle operation results and prevent unexpected behavior.
 *
 * @author: luke
 */

import java.io.File;

public class CheckReturnValues {

    public static void deleteFile() {

        File someFile = new File("someFileName.txt");

        // Check whether the deletion was successful
        if (!someFile.delete()) {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void replaceText() {

        String original = "insecure";

        // Use the returned value from replace()
        original = original.replace('i', '9');

        System.out.println(original);
    }
}
