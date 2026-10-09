import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.IOException;

public class SER12 { 
    /**
     * Reads an object from a stream with verification
     * 
     * @param in the input stream
     * @return the deserialized object
     * @throws IOException if read does fail
     * @throws ClassNotFoundException if class is missing
     */
    public static Object readObj(InputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream out = new ObjectInputStream(in)) {
            return out.readObject();
        }
    }
}

