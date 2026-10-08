/**
 * Rule: Distinguish between characters or bytes read from a stream and -1
 *
 * @author Lennart
 */

import java.io.FileReader;

/**
 * Demonstrates distinguishing between characters or bytes read from a stream and -1.
 */
public class FIO08 {

    /**
     * Main method demonstrating reading characters from a file and distinguishing
     * between valid characters and the end-of-file indicator (-1).
     */
    public static void main(String[] args) {
        try (FileReader input = new FileReader("test.txt")) {

            // different variables for buffer and data
            int buff;
            char data;

            // read characters from the input stream until end of file
            while ((buff = input.read()) != -1) {

                // 
                data = (char) buff;
                System.out.print(data);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}