import java.util.List;

public interface EmailClientInterface {
    void sendEmail(Email email);
    List<Email> getEmails(String folder);
    void deleteEmail(String emailId);
    void markAsRead(String emailId);
}
