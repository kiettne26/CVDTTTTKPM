public class Main {
    public static void main(String[] args) {
        // Sử dụng SMTP
        EmailFactory smtpFactory = new SmtpEmailFactory();
        EmailNotifier notifier1 = new EmailNotifier(smtpFactory);
        notifier1.notify("user@example.com", "Chào bạn!");

        // Sử dụng API 
        EmailFactory apiFactory = new ApiEmailFactory();
        EmailNotifier notifier2 = new EmailNotifier(apiFactory);
        notifier2.notify("", "Chào bạn!");
    }
}
