import java.util.Date;

public class Subscriber {
    private int s_id;
    static int s_count = 0;
    private String name;
    private int age;
    private boolean isActive;
    private int textMessageCount = 0;
    private int voiceCallMinute = 0;
    private double internetUsage = 0;

    ServiceProvider s_provider;
    Invoice invoice;

    public void setUsageLimit(double usageLimit) {
        invoice.changeUsageLimit(usageLimit);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getVoiceCallMinute() {
        return voiceCallMinute;
    }

    public int getTextMessageCount() {
        return textMessageCount;
    }

    public double getInternetUsage() {
        return internetUsage;
    }

    Subscriber(String name, int age, ServiceProvider s_provider, double usageLimit) {
        s_id = 1000000+ s_count;
        this.name = name;
        this.age = age;
        this.s_provider = s_provider;
        this.invoice = new Invoice(usageLimit, new Date());
        this.isActive = true;
        s_count++;
    }

    public void makePayment(double amount) {
        invoice.pay(amount);
    }

    public void updateStatus() {
        if (invoice.getLastDayToPay().before(new Date())) {
            isActive = false;
        }
    }

    public void makeVoiceCall(int minute, Subscriber receiver){
        if(this.isActive && receiver.isActive){
            double cost = s_provider.calculateVoiceCallCost(minute, this);
            if (invoice.isLimitExceeded(cost)) {
                System.out.println("Limit exceeded");
                return;
            }
            voiceCallMinute += minute;
            invoice.addCost(cost);
        }
    }

    public void sendMessage(int quantity, Subscriber receiver){
        if(this.isActive && receiver.isActive){
            double cost = s_provider.calculateMessagingCost(quantity, this, receiver);
            if (invoice.isLimitExceeded(cost)) {
                System.out.println("Limit exceeded");
                return;
            }
            textMessageCount += quantity;
            invoice.addCost(cost);
        }
    }

    public void connectToInternet(double amount){
        if(this.isActive){
            double cost = s_provider.calculateInternetCost(amount, this);
            if (invoice.isLimitExceeded(cost)) {
                System.out.println("Limit exceeded");
                return;
            }
            internetUsage += amount;
            invoice.addCost(cost);
        }
    }

    public void changeServiceProvider(ServiceProvider newProvider){
        if(invoice.getCurrentSpending() == 0){
            s_provider.removeSubscriber(this);
            s_provider = newProvider;
            s_provider.addSubscriber(this);
        }
    }

    @Override
    public String toString() {
        return this.name + " subscriber, service provider of subscriber is " + this.s_provider.getProviderName() + ", text messaging count " + this.textMessageCount + ", voice call minute " + this.voiceCallMinute + ", internet usage " + this.internetUsage + ", invoice " + this.invoice;
    }

}
