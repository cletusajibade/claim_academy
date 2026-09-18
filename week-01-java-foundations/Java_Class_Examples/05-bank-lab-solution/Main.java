public class Main {
    public static void main(String[] args) {
        BankAccount maya = new BankAccount("Maya");
        BankAccount leo = new BankAccount("Leo");
        System.out.println("Maya deposit 10000: " + maya.deposit(10000));
        System.out.println("Maya withdraw 2500: " + maya.withdraw(2500));
        System.out.println("Maya withdraw 8000: " + maya.withdraw(8000));
        System.out.println("Maya withdraw 0: " + maya.withdraw(0));
        System.out.println("Maya deposit -100: " + maya.deposit(-100));
        System.out.println("Leo deposit 2000: " + leo.deposit(2000));
        System.out.println("Balances: " + maya.getBalanceCents() + ", " + leo.getBalanceCents());
        BankAccount alias = maya;
        System.out.println("Alias deposit 500: " + alias.deposit(500));
        System.out.println("Balances: " + maya.getBalanceCents() + ", " + leo.getBalanceCents());
        System.out.println("Same object: " + (alias == maya));
        maya.setOwner("Maya Chen");
        System.out.println("Owner: " + maya.getOwner());
        BankAccount missing = null;
        if (missing != null) {
            missing.deposit(100);
        } else {
            System.out.println("No account");
        }
    }
}
