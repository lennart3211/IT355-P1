package rules;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Demonstrates correctly declared custom serialization methods.
 */
public class SER01 {

    /**
     * Serializes and restores a sample note entirely in memory.
     *
     * @param args command-line arguments, which are not used
     * @throws IOException if serialization or deserialization fails
     * @throws ClassNotFoundException if the serialized note class cannot be found
     */
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        SerializedNote original = new SerializedNote("Review serialization signatures");

        byte[] serializedData;
        try (ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput)) {
            objectOutput.writeObject(original);
            objectOutput.flush();
            serializedData = byteOutput.toByteArray();
        }

        SerializedNote restored;
        try (ObjectInputStream objectInput = new ObjectInputStream(
                new ByteArrayInputStream(serializedData))) {
            restored = (SerializedNote) objectInput.readObject();
        }

        System.out.println("Original message: " + original.getMessage());
        System.out.println("Restored message: " + restored.getMessage());
        System.out.println("Messages match: "
                + original.getMessage().equals(restored.getMessage()));
    }

    /**
     * A small serializable object with explicitly declared serialization hooks.
     */
    private static class SerializedNote implements Serializable {
        private static final long serialVersionUID = 1L;

        private String message;

        /**
         * Creates a note with the supplied message.
         *
         * @param message the text stored in the note
         */
        private SerializedNote(String message) {
            this.message = message;
        }

        /**
         * Returns the note message.
         *
         * @return the stored message
         */
        private String getMessage() {
            return message;
        }

        /**
         * Writes this object's serializable fields using the required signature.
         *
         * @param output the stream receiving this object's data
         * @throws IOException if writing the object fails
         */
        private void writeObject(ObjectOutputStream output) throws IOException {
            System.out.println("writeObject invoked");
            output.defaultWriteObject();
        }

        /**
         * Reads this object's serializable fields using the required signature.
         *
         * @param input the stream containing this object's data
         * @throws IOException if reading the object fails
         * @throws ClassNotFoundException if a serialized field type is unavailable
         * 
         * @author Caleb
         */
        private void readObject(ObjectInputStream input)
                throws IOException, ClassNotFoundException {
            System.out.println("readObject invoked");
            input.defaultReadObject();
        }
    }
}
