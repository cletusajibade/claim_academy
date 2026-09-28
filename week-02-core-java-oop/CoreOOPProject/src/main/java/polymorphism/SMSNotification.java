package polymorphism;

public class SMSNotification implements Notification{
    @Override
    public void send() {
        IO.println("Sending SMS...");
    }
}
