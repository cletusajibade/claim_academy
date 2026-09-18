public class Main {
    static void main(String[] args) {
        BankAccount reggie = new BankAccount("Reggie");
        BankAccount jules = new BankAccount("Jules");

        // Get current balance
        IO.println("Reggie's current balance= " + reggie.getBalanceCents());
        IO.println("Jules's current balance= " + jules.getBalanceCents());

        IO.println();

        //Make a deposit for Reggie
        boolean success = reggie.deposit(1000);
        if (success) {
            IO.println("Deposit made, new balance = " + reggie.getBalanceCents());
        } else {
            IO.println("Error making deposit");
        }

        if (reggie.withdraw(600)) {
            IO.println("Withdrawal successful");
            IO.println("Reggie's new balance= " + reggie.getBalanceCents());
        }
        else {
            IO.println("Error doing withdrawal ");
        }

        if (reggie.withdraw(800)) {
            IO.println("Withdrawal successful");
            IO.println("Reggie's new balance= " + reggie.getBalanceCents());
        }
        else {
            IO.println("Error doing withdrawal, current balance is: "+reggie.getBalanceCents());
        }
    }
}
