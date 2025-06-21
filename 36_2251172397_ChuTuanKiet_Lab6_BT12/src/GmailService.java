import java.util.*;

public class GmailService {
    private Map<String, Map<String, Object>> gmailData = new HashMap<>(); // giả lập dữ liệu email

    // Gửi email
    public void gmailSend(Map<String, Object> gmailEmailData) {
        String id = UUID.randomUUID().toString();
        gmailEmailData.put("id", id);
        gmailData.put(id, gmailEmailData);
        System.out.println("Gmail gửi: " + gmailEmailData);
    }

    // Lấy email theo nhãn
    public List<Map<String, Object>> gmailFetch(String label) {
        List<Map<String, Object>> emails = new ArrayList<>();
        for (Map<String, Object> email : gmailData.values()) {
            if (label.equals(email.getOrDefault("label", "inbox"))) {
                emails.add(email);
            }
        }
        System.out.println("Lấy email từ folder: " + label);
        return emails;
    }

    // Xóa email
    public void gmailDelete(String gmailMessageId) {
        gmailData.remove(gmailMessageId);
        System.out.println("Xóa Gmail: " + gmailMessageId);
    }

    // Đánh dấu đã đọc
    public void gmailMarkRead(String gmailMessageId) {
        Map<String, Object> email = gmailData.get(gmailMessageId);
        if (email != null) {
            email.put("isRead", true);
            System.out.println("Đánh dấu đã đọc: " + gmailMessageId);
        }
    }
}
