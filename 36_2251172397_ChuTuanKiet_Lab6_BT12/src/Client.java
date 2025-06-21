import java.util.List;

public class Client {
    public static void main(String[] args) {
        // Tạo service Gmail
        GmailService gmailService = new GmailService();

        // Tạo adapter
        EmailClientInterface emailClient = new GmailAdapter(gmailService);

        // Tạo email mới
        Email email1 = new Email(
                null,
                "Kiet@gmail.com",
                "abc@gmail.com",
                "Chào bạn!",
                "Đây là nội dung email gửi bằng Adapter Pattern.",
                false
        );

        // Gửi email
        System.out.println("\n--- Gửi Email ---");
        emailClient.sendEmail(email1);

        // Lấy email từ inbox
        System.out.println("\n--- Lấy Email từ inbox ---");
        List<Email> emails = emailClient.getEmails("inbox");
        for (Email e : emails) {
            System.out.println(e);
        }

        // Xóa một email
        if (!emails.isEmpty()) {
            String idToDelete = emails.get(0).getId();
            System.out.println("\n--- Xóa Email có id: " + idToDelete + " ---");
            emailClient.deleteEmail(idToDelete);
        }

        // Gửi thêm email, sau đó đánh dấu đã đọc
        Email email2 = new Email(
                null,
                "Kiet@Gmail.com",
                "xyz@gmail.com",
                "Test 2",
                "Email thứ 2 gửi bằng Adapter Pattern.",
                false
        );
        emailClient.sendEmail(email2);

        System.out.println("\n--- Danh sách Email mới nhất ---");
        emails = emailClient.getEmails("inbox");
        for (Email e : emails) {
            System.out.println(e);
        }

        if (!emails.isEmpty()) {
            String idToMarkRead = emails.get(0).getId();
            System.out.println("\n--- Đánh dấu đã đọc Email có id: " + idToMarkRead + " ---");
            emailClient.markAsRead(idToMarkRead);

            // Kiểm tra lại trạng thái đã đọc
            emails = emailClient.getEmails("inbox");
            for (Email e : emails) {
                System.out.println(e);
            }
        }
    }
}
