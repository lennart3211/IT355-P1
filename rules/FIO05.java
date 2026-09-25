package rules;

import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * Demonstrates protecting a character array from modification through a
 * returned buffer.
 */
public class FIO05 {
    private final char[] storedText;

    /**
     * Creates an object that owns a copy of the supplied characters.
     *
     * @param text the characters to store
     */
    public FIO05(char[] text) {
        storedText = Arrays.copyOf(text, text.length);
    }

    /**
     * Returns a mutable buffer backed by an independent copy of the stored
     * characters.
     *
     * @return a buffer whose changes cannot affect the stored characters
     */
    public CharBuffer getTextBuffer() {
        return CharBuffer.wrap(Arrays.copyOf(storedText, storedText.length));
    }

    /**
     * Returns the object's original text.
     *
     * @return the stored characters as a new string
     */
    public String getStoredText() {
        return new String(storedText);
    }

    /**
     * Demonstrates that changing the returned buffer does not change the
     * original stored text.
     *
     * @param args command-line arguments, which are not used
     * 
     * @author Caleb
     */
    public static void main(String[] args) {
        FIO05 protectedText = new FIO05("SAFE".toCharArray());
        CharBuffer returnedBuffer = protectedText.getTextBuffer();

        returnedBuffer.put(0, 'X');

        System.out.println("Modified buffer: " + returnedBuffer);
        System.out.println("Original stored text: " + protectedText.getStoredText());
        System.out.println("Original text protected: "
                + protectedText.getStoredText().equals("SAFE"));
    }
}
