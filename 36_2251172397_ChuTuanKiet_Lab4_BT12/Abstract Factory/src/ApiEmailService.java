public class ApiEmailService implements IEmailService {
    @Override
    public boolean sendEmail(String to, String subject, String body) {
        if (to != null && !to.isEmpty()) {
            System.out.println("Gửi email tới " + to + " thành công qua API");
            return true;
        } else {
            System.out.println("Địa chỉ email không hợp lệ");
            return false;
        }
    }
}