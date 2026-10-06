package recs;
 /**
  * Demonstrates OBJ57-J.
  * The final keyword prevents this class
  * from being extended by another class.
  *
  * @author Jocelyn Jude
  */
public final class OBJ57 {

    private double balance;

    /**
     * Creates an account.
     *
     * @param balance starting balance
     */
    public OBJ57(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the account balance.
     *
     * @return account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Runs the example.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        OBJ57 account =
                new OBJ57(500.00);

        System.out.println(
                "Balance: $" + account.getBalance());
    }
}