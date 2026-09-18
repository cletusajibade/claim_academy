public class BankAccount {
    private String owner;
    private int balanceCents;

    // Constructor
    // The parameter 'owner' is different from the class field 'owner'
    public BankAccount(String owner) {
        this.owner = owner;
        this.balanceCents = 0;
    }

    //Getters and Setters
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBalanceCents() {
        return balanceCents;
    }

    public boolean deposit(int amountCents) {
        if (amountCents <= 0) {
            return false;
        }

        // balanceCents=balanceCents+amountCents
        balanceCents += amountCents;
        return true;
    }

    public boolean withdraw(int amountCents) {
        //balanceCents= 10
        //amountCents=5
        if (amountCents <= 0 || amountCents > balanceCents) {
            return false;
        }
        // balanceCents = balanceCents - amountCents
        balanceCents -= amountCents;
        return true;
    }
}
