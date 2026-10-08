/**
 * Rule: Do not return references to private mutable class members
 *
 * @author Lennart
 */

/**
 * Demonstrates avoiding returning references to private mutable class members by returning a copy instead.
 */
class Data {
    public int value;

    /**
     * Constructs a new Data object with the specified value.
     *
     * @param value the value to set for this Data object
     */
    public Data(int value) {
        this.value = value;
    }

    /**
     * Creates and returns a copy of this Data object.
     *
     * @return a new Data object with the same value as this one
     */
    public Data clone() {
        Data cloned = new Data(this.value);
        return cloned;
    }
}

/**
 * Demonstrates a class with a private mutable member and how to safely provide access to it.
 */
class MutableClass {

    // Private mutable class member
    private Data data;

    /**
     * Constructs a new MutableClass object and initializes the private Data member.
     */
    public MutableClass() {
        this.data = new Data(5);
    }

    /**
     * Returns a copy of the private Data member.
     *
     * @return a new Data object with the same value as the private member
     */
    public Data getData() {
        return data.clone();
    }
}

/**
 * Demonstrates the usage of the MutableClass and how modifying a copy of the private Data member does not affect the original.
 */
public class OBJ05 {

    /**
     * Main method demonstrating the usage of the MutableClass and how modifying a copy of the private Data member does not affect the original.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MutableClass mc = new MutableClass();
        Data data = mc.getData();

        // modifying the copy of the private Data member does not change the original private member
        data.value = 10;
        System.out.println(data.value);
        System.out.println(mc.getData().value);
    }
}