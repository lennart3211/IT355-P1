/*
 * Author: Lennart
 * Rule: Validate method arguments
 */


public class MET00 {
    private String str = null;

    public void setStr(String str) {
        if (str == null) {
            System.err.println("Error: str cannot be null");
        }
        else {
            this.str = str;
        }
    }

    public static void main(String[] args) {
        MET00 met00 = new MET00();
        met00.setStr(null);
        met00.setStr("Hello");
    }
}