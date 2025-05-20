public class EmailNotifier {
    private IEmailService emailService;

    public EmailNotifier(EmailFactory factory) {
        this.emailService = factory.createEmailService();
    }

    public void notify(String recipient, String message) {
        boolean result = emailService.sendEmail(recipient, "Thông báo", message);
        System.out.println("Kết quả gửi: " + result);
    }
}
