package Email;

public class SmtpEmailService implements IEmailService {
    @Override
    public boolean sendEmail(String to, String subject, String body) {
        if (to != null && !to.isEmpty()) {
            System.out.println("Gửi email tới " + to + " Thành công qua SMTP");
            return true;
        }
        else {
            System.out.println("Email không hợp hệ");
            return false;
        }
    }
}
