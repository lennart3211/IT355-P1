/*
 * Author: Lennart
 * Rule: Prevent exceptions while logging data
 */

import java.util.logging.Logger;
import java.util.logging.Level;

public class ERR02 {
    private static final Logger logger = Logger.getLogger(ERR02.class.getName());

    public static void main(String[] args) {
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}