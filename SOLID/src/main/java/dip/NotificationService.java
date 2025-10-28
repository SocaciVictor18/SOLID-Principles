package dip;

// High-level policy depends on abstraction and receives the dependency (constructor injection)
public class NotificationService {
    private final MessageSender sender;

    public NotificationService(MessageSender sender) { this.sender = sender; }

    public void notifyUser(String userId, String text) {
        sender.send(userId, text);
    }
}
