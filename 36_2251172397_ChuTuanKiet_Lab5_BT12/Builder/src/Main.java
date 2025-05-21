// Interface
interface EmailBuilder {
    void reset();
    void BuilderEmail(String email);
    void BuilderTitle(String title);
    void BuilderContent(String content);
    void BuilderSend(Boolean send);
}

// API Email Builder
class ApiEmailBuilder implements EmailBuilder {
    private ApiEmail result;

    public ApiEmailBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.result = new ApiEmail();
    }

    @Override
    public void BuilderEmail(String email) {
        result.setEmail(email);
    }

    @Override
    public void BuilderTitle(String title) {
        result.setTitle(title);
    }

    @Override
    public void BuilderContent(String content) {
        result.setContent(content);
    }

    @Override
    public void BuilderSend(Boolean send) {
        result.setSend(send);
    }

    public void BuilderApiKey(String apiKey) {
        result.setApiKey(apiKey);
    }

    public ApiEmail getResult() {
        return result;
    }
}

// SMTP Email Builder
class SmtpEmailBuilder implements EmailBuilder {
    private SmtpEmail result;

    public SmtpEmailBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.result = new SmtpEmail();
    }

    @Override
    public void BuilderEmail(String email) {
        result.setEmail(email);
    }

    @Override
    public void BuilderTitle(String title) {
        result.setTitle(title);
    }

    @Override
    public void BuilderContent(String content) {
        result.setContent(content);
    }

    @Override
    public void BuilderSend(Boolean send) {
        result.setSend(send);
    }

    public void BuilderSmtpHost(String smtpHost) {
        result.setSmtpHost(smtpHost);
    }

    public SmtpEmail getResult() {
        return result;
    }
}

// Director
class Director {
    private EmailBuilder builder;

    public void ChangeBuilder(EmailBuilder builder) {
        this.builder = builder;
    }

    public void MakeSendApi(ApiEmailBuilder builder) {
        builder.reset();
        builder.BuilderEmail("Kiet@Email.com");
        builder.BuilderTitle("API Email");
        builder.BuilderContent("This is content via API");
        builder.BuilderSend(true);
        builder.BuilderApiKey("API_KEY_HERE");
    }

    public void MakeSendSmtp(SmtpEmailBuilder builder) {
        builder.reset();
        builder.BuilderEmail("Kiet@Email.com");
        builder.BuilderTitle("SMTP Email");
        builder.BuilderContent("This is content via SMTP");
        builder.BuilderSend(true);
        builder.BuilderSmtpHost("smtp.example.com");
    }
}

// ApiEmail Class
class ApiEmail {
    private String email;
    private String title;
    private String content;
    private Boolean send;
    private String apiKey;

    // Getters & Setters
    public void setEmail(String email) { this.email = email; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setSend(Boolean send) { this.send = send; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    @Override
    public String toString() {
        return "ApiEmail{" +
                "email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", send=" + send +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}

// SmtpEmail Class
class SmtpEmail {
    private String email;
    private String title;
    private String content;
    private Boolean send;
    private String smtpHost;

    // Getters & Setters
    public void setEmail(String email) { this.email = email; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setSend(Boolean send) { this.send = send; }
    public void setSmtpHost(String smtpHost) { this.smtpHost = smtpHost; }

    @Override
    public String toString() {
        return "SmtpEmail{" +
                "email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", send=" + send +
                ", smtpHost='" + smtpHost + '\'' +
                '}';
    }

}

// Client Example
class Client {
    public static void main(String[] args) {
        Director director = new Director();

        ApiEmailBuilder apiBuilder = new ApiEmailBuilder();
        director.MakeSendApi(apiBuilder);
        ApiEmail apiEmail = apiBuilder.getResult();

        SmtpEmailBuilder smtpBuilder = new SmtpEmailBuilder();
        director.MakeSendSmtp(smtpBuilder);
        SmtpEmail smtpEmail = smtpBuilder.getResult();

        System.out.println(apiEmail);
        System.out.println(smtpEmail);

    }
}
