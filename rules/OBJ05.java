/*
 * Author: Lennart
 * Rule: Do not return references to private mutable class members
 */

class Data {
    public int value;

    public Data(int value) {
        this.value = value;
    }

    public Data clone() {
        Data cloned = new Data(this.value);
        return cloned;
    }
}

class MutableClass {
    private Data data;

    public MutableClass() {
        this.data = new Data(5);
    }

    public Data getData() {
        return data.clone();
    }
}

public class OBJ05 {

    public static void main(String[] args) {
        MutableClass mc = new MutableClass();
        Data data = mc.getData();
        data.value = 10;
        System.out.println(data.value);
        System.out.println(mc.getData().value);
    }
}