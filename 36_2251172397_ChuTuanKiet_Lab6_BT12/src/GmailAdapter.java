import java.util.*;

public class GmailAdapter implements EmailClientInterface {
    private GmailService gmailService;

    public GmailAdapter(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    // Chuyển Email (class) thành Map (giống GmailService)
    private Map<String, Object> emailToGmailMap(Email email, String label) {
        Map<String, Object> map = new HashMap<>();
        map.put("from", email.getSender());
        map.put("to", email.getRecipient());
        map.put("subject", email.getSubject());
        map.put("body", email.getContent());
        map.put("isRead", email.isRead());
        map.put("label", label);
        // id sẽ được tạo khi gửi mail
        return map;
    }

    // Chuyển ngược lại từ Map sang Email
    private Email gmailMapToEmail(Map<String, Object> map) {
        return new Email(
                (String) map.get("id"),
                (String) map.get("from"),
                (String) map.get("to"),
                (String) map.get("subject"),
                (String) map.get("body"),
                (Boolean) map.getOrDefault("isRead", false)
        );
    }

    @Override
    public void sendEmail(Email email) {
        Map<String, Object> gmailEmail = emailToGmailMap(email, "inbox");
        gmailService.gmailSend(gmailEmail);
    }

    @Override
    public List<Email> getEmails(String folder) {
        List<Map<String, Object>> gmailEmails = gmailService.gmailFetch(folder);
        List<Email> emails = new ArrayList<>();
        for (Map<String, Object> map : gmailEmails) {
            emails.add(gmailMapToEmail(map));
        }
        return emails;
    }

    @Override
    public void deleteEmail(String emailId) {
        gmailService.gmailDelete(emailId);
    }

    @Override
    public void markAsRead(String emailId) {
        gmailService.gmailMarkRead(emailId);
    }
}
