// Email model class
class Email {
    private String id;
    private String from;
    private String to;
    private String subject;
    private String content;
    private long timestamp;

    public Email(String from, String to, String subject, String content) {
        this.id = generateId();
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    private String generateId() {
        return "EMAIL_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
    }

    public String getId() { return id; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Email{id='%s', from='%s', to='%s', subject='%s'}",
                id, from, to, subject);
    }
}

interface EmailMediator {
    void notify(Object sender, String event, Email email);
}

abstract class EmailComponent {
    protected EmailMediator mediator;

    public EmailComponent(EmailMediator mediator) {
        this.mediator = mediator;
    }

    public void setMediator(EmailMediator mediator) {
        this.mediator = mediator;
    }
}

class EmailComposer extends EmailComponent {
    private String currentUser;

    public EmailComposer(EmailMediator mediator, String currentUser) {
        super(mediator);
        this.currentUser = currentUser;
    }

    public void composeEmail(String to, String subject, String content) {
        System.out.println("EmailComposer: Soạn thảo email mới...");
        Email email = new Email(currentUser, to, subject, content);
        System.out.println("EmailComposer: Đã soạn email - " + email);
        sendMessage(email);
    }

    public void sendMessage(Email email) {
        System.out.println("EmailComposer: Gửi email thông qua mediator...");
        mediator.notify(this, "EMAIL_COMPOSED", email);
    }

    public void onEmailSent(Email email) {
        System.out.println("EmailComposer: Email đã được gửi thành công - " + email.getId());
    }
}

class EmailViewer extends EmailComponent {

    public EmailViewer(EmailMediator mediator) {
        super(mediator);
    }

    public void viewEmail(String emailId) {
        System.out.println("EmailViewer: Yêu cầu xem email - " + emailId);
        mediator.notify(this, "REQUEST_EMAIL", null);
    }

    public void displayMessage(Email email) {
        System.out.println("EmailViewer: Hiển thị email:");
        System.out.println("  From: " + email.getFrom());
        System.out.println("  To: " + email.getTo());
        System.out.println("  Subject: " + email.getSubject());
        System.out.println("  Content: " + email.getContent());
        System.out.println("  Time: " + new java.util.Date(email.getTimestamp()));
    }

    public void refreshEmailList() {
        System.out.println("EmailViewer: Làm mới danh sách email...");
        mediator.notify(this, "REFRESH_REQUEST", null);
    }
}

class EmailStorage extends EmailComponent {
    private java.util.Map<String, Email> emailDatabase;

    public EmailStorage(EmailMediator mediator) {
        super(mediator);
        this.emailDatabase = new java.util.HashMap<>();
    }

    public void storeEmail(Email email) {
        System.out.println("EmailStorage: Lưu trữ email - " + email.getId());
        emailDatabase.put(email.getId(), email);
        System.out.println("EmailStorage: Email đã được lưu thành công!");
        mediator.notify(this, "EMAIL_STORED", email);
    }

    public Email retrieveEmail(String emailId) {
        System.out.println("EmailStorage: Truy xuất email - " + emailId);
        Email email = emailDatabase.get(emailId);
        if (email != null) {
            System.out.println("EmailStorage: Tìm thấy email - " + email);
        } else {
            System.out.println("EmailStorage: Không tìm thấy email với ID: " + emailId);
        }
        return email;
    }

    public java.util.List<Email> getAllEmails() {
        System.out.println("EmailStorage: Lấy tất cả email...");
        return new java.util.ArrayList<>(emailDatabase.values());
    }
}

class NotificationService extends EmailComponent {

    public NotificationService(EmailMediator mediator) {
        super(mediator);
    }

    public void sendNotification(String message, String recipient) {
        System.out.println("NotificationService: Gửi thông báo đến " + recipient);
        System.out.println("  Nội dung: " + message);
        alertUser(message);
    }

    public void alertUser(String message) {
        System.out.println("NotificationService: [ALERT] " + message);
    }

    public void notifyEmailReceived(Email email) {
        String message = String.format("Bạn có email mới từ %s với tiêu đề: %s",
                email.getFrom(), email.getSubject());
        sendNotification(message, email.getTo());
    }

    public void notifyEmailSent(Email email) {
        String message = String.format("Email của bạn đã được gửi thành công đến %s",
                email.getTo());
        sendNotification(message, email.getFrom());
    }
}

class EmailManagerMediator implements EmailMediator {
    private EmailComposer emailComposer;
    private EmailViewer emailViewer;
    private EmailStorage emailStorage;
    private NotificationService notificationService;

    public EmailManagerMediator() {
        // Components will be set after initialization
    }

    // Setters for components
    public void setEmailComposer(EmailComposer emailComposer) {
        this.emailComposer = emailComposer;
    }

    public void setEmailViewer(EmailViewer emailViewer) {
        this.emailViewer = emailViewer;
    }

    public void setEmailStorage(EmailStorage emailStorage) {
        this.emailStorage = emailStorage;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void notify(Object sender, String event, Email email) {
        System.out.println("EmailManagerMediator: Nhận sự kiện '" + event + "' từ " +
                sender.getClass().getSimpleName());

        if (sender == emailComposer) {
            reactOnSend(event, email);
        } else if (sender == emailViewer) {
            reactOnView(event, email);
        } else if (sender == emailStorage) {
            reactOnStore(event, email);
        } else if (sender == notificationService) {
            reactOnNotification(event, email);
        }
    }

    private void reactOnSend(String event, Email email) {
        if ("EMAIL_COMPOSED".equals(event)) {
            System.out.println("EmailManagerMediator: Xử lý email đã soạn...");

            // 1. Lưu email vào storage
            emailStorage.storeEmail(email);

            // 2. Gửi thông báo
            notificationService.notifyEmailSent(email);

            // 3. Thông báo cho composer
            emailComposer.onEmailSent(email);

            // 4. Làm mới viewer
            emailViewer.refreshEmailList();
        }
    }

    private void reactOnView(String event, Email email) {
        if ("REQUEST_EMAIL".equals(event)) {
            System.out.println("EmailManagerMediator: Xử lý yêu cầu xem email...");
            java.util.List<Email> emails = emailStorage.getAllEmails();
            if (!emails.isEmpty()) {
                emailViewer.displayMessage(emails.get(emails.size() - 1));
            }
        } else if ("REFRESH_REQUEST".equals(event)) {
            System.out.println("EmailManagerMediator: Xử lý yêu cầu làm mới...");
            java.util.List<Email> emails = emailStorage.getAllEmails();
            System.out.println("EmailManagerMediator: Có " + emails.size() + " emails trong hệ thống");
        }
    }

    private void reactOnStore(String event, Email email) {
        if ("EMAIL_STORED".equals(event)) {
            System.out.println("EmailManagerMediator: Email đã được lưu trữ thành công");
        }
    }

    private void reactOnNotification(String event, Email email) {
        System.out.println("EmailManagerMediator: Xử lý sự kiện thông báo - " + event);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Email Management System - Mediator Pattern Demo ===\n");

        // Tạo mediator
        EmailManagerMediator mediator = new EmailManagerMediator();

        // Tạo các components
        EmailComposer composer = new EmailComposer(mediator, "john.doe@company.com");
        EmailViewer viewer = new EmailViewer(mediator);
        EmailStorage storage = new EmailStorage(mediator);
        NotificationService notification = new NotificationService(mediator);

        // Thiết lập mediator với các components
        mediator.setEmailComposer(composer);
        mediator.setEmailViewer(viewer);
        mediator.setEmailStorage(storage);
        mediator.setNotificationService(notification);

        System.out.println("=== Kịch bản 1: Soạn và gửi email ===");
        composer.composeEmail("Kiet@gmail.com",
                "Báo cáo tuần",
                "Xin chào Kiet, đây là báo cáo tuần...");

        System.out.println("\n=== Kịch bản 2: Xem email ===");
        viewer.viewEmail("latest");

        System.out.println("\n=== Kịch bản 3: Gửi thêm email khác ===");
        composer.composeEmail("manager@gmail.com",
                "Xin nghỉ phép",
                "Kính gửi quản lý, tôi xin phép nghỉ 1 ngày...");

        System.out.println("\n=== Kịch bản 4: Làm mới danh sách email ===");
        viewer.refreshEmailList();

        System.out.println("\n=== Done ===");
    }
}