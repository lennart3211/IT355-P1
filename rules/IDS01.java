import java.text.Normalizer;

public class IDS01 {
    /**
     * Normalizes and validates input string against allowed characters
     *
     * @param input untrusted string
     * @return true if valid, false otherwise
     */
    public static boolean validateInput(String input){
        if (input == null) {
            return false;
        }
        //Normalize to cannonical composition form before validation
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC);
        return normalized.matches("^[a-zA-Z0-9]+$");
    }
}
