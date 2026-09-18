public class BankAccount {
    private String owner;
    private int balanceCents;

    public BankAccount(String owner) {
        this.owner = owner;
        this.balanceCents = 0;
    }

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
        balanceCents += amountCents;
        return true;
    }
}
