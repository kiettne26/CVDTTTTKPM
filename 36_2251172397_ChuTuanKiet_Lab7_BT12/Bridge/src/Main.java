// Implementation Interface - EmailProvider
interface EmailProvider {
    void connect();
    void send(String message, String recipient);
    String receive();
    void delete(String messageId);
    String search(String criteria);
}

// Concrete Implementation 1 - GmailProvider
class GmailProvider implements EmailProvider {
    @Override
    public void connect() {
        System.out.println("Connecting to Gmail using OAuth2...");
    }

    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending via Gmail API: " + message + " to " + recipient);
    }

    @Override
    public String receive() {
        System.out.println("Receiving emails from Gmail server...");
        return "Gmail inbox messages";
    }

    @Override
    public void delete(String messageId) {
        System.out.println("Deleting Gmail message ID: " + messageId);
    }

    @Override
    public String search(String criteria) {
        System.out.println("Searching Gmail with criteria: " + criteria);
        return "Gmail search results";
    }

    public void useGmailAPI() {
        System.out.println("Using Gmail-specific API features");
    }
}

// Concrete Implementation 2 - OutlookProvider
class OutlookProvider implements EmailProvider {
    @Override
    public void connect() {
        System.out.println("Connecting to Outlook using Microsoft Graph API...");
    }

    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending via Outlook: " + message + " to " + recipient);
    }

    @Override
    public String receive() {
        System.out.println("Receiving emails from Outlook server...");
        return "Outlook inbox messages";
    }

    @Override
    public void delete(String messageId) {
        System.out.println("Deleting Outlook message ID: " + messageId);
    }

    @Override
    public String search(String criteria) {
        System.out.println("Searching Outlook with criteria: " + criteria);
        return "Outlook search results";
    }

    public void useGraphAPI() {
        System.out.println("Using Microsoft Graph API features");
    }
}

// Abstraction - Email
abstract class Email {
    protected EmailProvider provider;

    public Email(EmailProvider provider) {
        this.provider = provider;
    }

    public void sendMessage(String message, String recipient) {
        provider.connect();
        provider.send(message, recipient);
    }

    public String receiveMessage() {
        provider.connect();
        return provider.receive();
    }

    public void deleteMessage(String messageId) {
        provider.delete(messageId);
    }

    public String searchMessages(String criteria) {
        return provider.search(criteria);
    }
}

// Refined Abstraction 1 - BusinessEmail
class BusinessEmail extends Email {
    public BusinessEmail(EmailProvider provider) {
        super(provider);
    }

    public void sendBulkEmail(String message, String[] recipients) {
        System.out.println("Preparing bulk email...");
        provider.connect();
        for (String recipient : recipients) {
            provider.send(message, recipient);
        }
        System.out.println("Bulk email sent to " + recipients.length + " recipients");
    }

    public void scheduleEmail(String message, String recipient, String scheduleTime) {
        System.out.println("Scheduling email for: " + scheduleTime);
        // Schedule logic here
        System.out.println("Email scheduled: " + message + " to " + recipient);
    }

    public void trackDelivery(String messageId) {
        System.out.println("Tracking delivery status for message: " + messageId);
        // Tracking logic here
    }

    public String generateReport() {
        System.out.println("Generating business email report...");
        return "Business Email Report: Sent: 150, Received: 75, Pending: 5";
    }
}

// Refined Abstraction 2 - PersonalEmail
class PersonalEmail extends Email {
    public PersonalEmail(EmailProvider provider) {
        super(provider);
    }

    public void organizeInFolders(String messageId, String folderName) {
        System.out.println("Moving message " + messageId + " to folder: " + folderName);
        // Folder organization logic
    }

    public void setAutoReply(String message) {
        System.out.println("Setting auto-reply message: " + message);
        // Auto-reply logic
    }

    public void createFilter(String criteria, String action) {
        System.out.println("Creating filter - Criteria: " + criteria + ", Action: " + action);
        // Filter creation logic
    }

    public void markAsImportant(String messageId) {
        System.out.println("Marking message as important: " + messageId);
        // Mark as important logic
    }
}

// Client
class EmailClient {
    public static void main(String[] args) {
        System.out.println("=== Email Management System with Bridge Pattern ===\n");

        // Create providers
        EmailProvider gmailProvider = new GmailProvider();
        EmailProvider outlookProvider = new OutlookProvider();

        // Business Email with Gmail
        System.out.println("--- Business Email with Gmail ---");
        BusinessEmail businessGmail = new BusinessEmail(gmailProvider);
        businessGmail.sendMessage("Business proposal", "client@company.com");
        String[] recipients = {"team1@company.com", "team2@company.com", "team3@company.com"};
        businessGmail.sendBulkEmail("Monthly newsletter", recipients);
        businessGmail.scheduleEmail("Meeting reminder", "boss@company.com", "2024-12-01 09:00");
        businessGmail.trackDelivery("MSG001");
        System.out.println(businessGmail.generateReport());

        System.out.println("\n--- Business Email with Outlook ---");
        BusinessEmail businessOutlook = new BusinessEmail(outlookProvider);
        businessOutlook.sendMessage("Project update", "manager@company.com");
        businessOutlook.generateReport();

        System.out.println("\n--- Personal Email with Gmail ---");
        PersonalEmail personalGmail = new PersonalEmail(gmailProvider);
        personalGmail.sendMessage("Hello friend!", "friend@gmail.com");
        personalGmail.organizeInFolders("MSG002", "Family");
        personalGmail.setAutoReply("I'm on vacation, will reply soon!");
        personalGmail.createFilter("from:newsletter", "move to folder:Newsletters");
        personalGmail.markAsImportant("MSG003");

        System.out.println("\n--- Personal Email with Outlook ---");
        PersonalEmail personalOutlook = new PersonalEmail(outlookProvider);
        personalOutlook.sendMessage("Weekend plans", "buddy@outlook.com");
        personalOutlook.receiveMessage();

        System.out.println("\n--- Demonstrating Bridge Pattern Benefits ---");
        System.out.println("✓ Same email types work with different providers");
        System.out.println("✓ Can easily add new providers (Yahoo, ProtonMail)");
        System.out.println("✓ Can add new email types (EnterpriseEmail, MobileEmail)");
        System.out.println("✓ Changes in one hierarchy don't affect the other");
    }
}

// Additional Example: Adding a new provider (Yahoo)
class YahooProvider implements EmailProvider {
    @Override
    public void connect() {
        System.out.println("Connecting to Yahoo Mail...");
    }

    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending via Yahoo: " + message + " to " + recipient);
    }

    @Override
    public String receive() {
        System.out.println("Receiving emails from Yahoo server...");
        return "Yahoo inbox messages";
    }

    @Override
    public void delete(String messageId) {
        System.out.println("Deleting Yahoo message ID: " + messageId);
    }

    @Override
    public String search(String criteria) {
        System.out.println("Searching Yahoo with criteria: " + criteria);
        return "Yahoo search results";
    }
}

// Additional Example: Adding a new email type (Enterprise)
class EnterpriseEmail extends Email {
    public EnterpriseEmail(EmailProvider provider) {
        super(provider);
    }

    public void sendSecureEmail(String message, String recipient) {
        System.out.println("Sending encrypted enterprise email...");
        provider.connect();
        // Add encryption logic
        provider.send("[ENCRYPTED] " + message, recipient);
    }

    public void auditEmailActivity() {
        System.out.println("Performing enterprise email audit...");
        // Audit logic
    }

    public void enforceComplianceRules() {
        System.out.println("Enforcing enterprise compliance rules...");
        // Compliance logic
    }
}