public class EXP04 {
    /**
     * Safely evaluate expression inside method argument lists.
     */
    public void evaluateValues() {
        int index = 0;

        /**
         * Evaluate side effects separately
         */
        int val1 = index;
        index++;
        int val2 = index;
        index++;

        compute(val1, val2);
    }

    private void compute(int a, int b) {
        /**
         * Computation logic done here
         */
    }
}

