package Email;

public interface IEmailService {
    boolean sendEmail(String to, String subject, String body);
}
