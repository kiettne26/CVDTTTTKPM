public class EmailService {
    public void send(EmailData emailData) {
        System.out.println("Sending email to: " + emailData.getTo());
        System.out.println("Subject: " + emailData.getSubject());
        System.out.println("Body: " + emailData.getBody());
    }
}
