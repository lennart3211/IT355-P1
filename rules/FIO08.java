/*
 * Author: Lennart
 * Rule: Distinguish between characters or bytes read from a stream and -1
 */

import java.io.FileReader;

public class FIO08 {


    public static void main(String[] args) {
        try (FileReader input = new FileReader("test.txt")) {
            int buff;
            char data;
            while ((buff = input.read()) != -1) {
                data = (char) buff;
                System.out.print(data);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}