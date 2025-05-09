package Email;

public class Main {
    public static void main(String[] args) {
        // kiểm tra với SMTP
        System.out.println("--- SMTP ---");
        EmailNotifier smtpNotifier = new EmailNotifier(new SmtpEmailService());
        smtpNotifier.notify("KietSMTP@example.com", "Chào bạn!");
        smtpNotifier.notify("", "Chào bạn!");

        // Kiểm tra với API
        System.out.println("\n--- API ---");
        EmailNotifier apiNotifier = new EmailNotifier(new ApiEmailService());
        apiNotifier.notify("KietAPI@example.com", "Chào bạn!");
        apiNotifier.notify("", "Chào bạn!");
    }
}
