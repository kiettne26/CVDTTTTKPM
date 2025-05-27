public class EmailClient {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        EmailClientInterface client = new EmailAdapter(emailService);

        client.sendEmail("Kiet@example.com", "Hello", "This is a test email using Adapter Pattern.");
    }
}
