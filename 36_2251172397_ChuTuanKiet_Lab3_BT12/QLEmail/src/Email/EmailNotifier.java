package Email;

public class EmailNotifier {
    private IEmailService emailService;

    public EmailNotifier(IEmailService emailService) {
        this.emailService = emailService;
    }

    public void notify(String recipient, String message ) {
        boolean success = emailService.sendEmail(recipient,"Thông báo", message);
        System.out.println("Kết quả gửi: " + (success ? "Thành công" : "Thất bại"));
    }

}
