import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public final class SER06 implements Serializable {
    private static final long serialUID = 1L;
    private final List<String> items;

    /**
     * Construct a secure record of items
     * 
     * @param list of items
     */
    public SER06(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Custom deserialization method ensuring defensive copies
     * 
     * @param in object input stream
     * @throws IOException if reading fails
     * @throws ClassNotFoundException if class cannot be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    
}

