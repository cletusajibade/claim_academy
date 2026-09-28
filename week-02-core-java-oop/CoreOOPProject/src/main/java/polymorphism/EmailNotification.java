package polymorphism;

public class EmailNotification implements Notification{
    @Override
    public void send() {
        IO.println("Sending email...");
    }
}
