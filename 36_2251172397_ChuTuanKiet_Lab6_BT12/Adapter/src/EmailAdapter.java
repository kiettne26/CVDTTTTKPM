public class EmailAdapter implements EmailClientInterface {
    private EmailService adaptee;

    public EmailAdapter(EmailService emailService) {
        this.adaptee = emailService;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        EmailData emailData = new EmailData(to, subject, body);
        adaptee.send(emailData);
    }
}
