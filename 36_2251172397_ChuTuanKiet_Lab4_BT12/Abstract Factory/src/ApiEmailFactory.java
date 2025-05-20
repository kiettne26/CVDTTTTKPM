public class ApiEmailFactory implements EmailFactory {
    @Override
    public IEmailService createEmailService() {
        return new ApiEmailService();
    }
}