public interface IEmailService {
    boolean sendEmail(String to, String subject, String body);
}
