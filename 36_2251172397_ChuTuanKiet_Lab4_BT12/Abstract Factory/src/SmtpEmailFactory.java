public class SmtpEmailFactory implements EmailFactory {
    @Override
    public IEmailService createEmailService() {
        return new SmtpEmailService();
    }
}