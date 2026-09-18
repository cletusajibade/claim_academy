public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Maya");
        System.out.println(account.deposit(500));
        System.out.println(account.deposit(0));
        System.out.println(account.getBalanceCents());
    }
}
