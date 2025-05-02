import java.util.ArrayList;

public class ServiceProvider {
    private int p_id;
    public static int p_id_count = 0;
    private String p_name;
    private Double voiceCallCost;
    private Double messagingCost;
    private Double internetCost;
    private int discountRatio;
    ArrayList<Subscriber> subscribersList;

    public String getProviderName() {
        return p_name;
    }

    ServiceProvider(String p_name, Double voiceCallCost, Double messagingCost, Double internetCost, int discountRatio) {
        p_id = 500+p_id_count;
        this.p_name = p_name;
        this.voiceCallCost = voiceCallCost;
        this.messagingCost = messagingCost;
        this.internetCost = internetCost;
        this.discountRatio = discountRatio;
        this.subscribersList = new ArrayList<Subscriber>();
        p_id_count++;
    }

    public double calculateVoiceCallCost(int minute, Subscriber caller) {

        boolean isTeenager = (caller.getAge() > 10 && caller.getAge() < 18);
        boolean isElder = (caller.getAge() >= 65);
        if (isElder) {
            return minute * voiceCallCost * (100 - discountRatio) / 100;
        }

        if (isTeenager) {
            if (caller.getVoiceCallMinute() < 5) {
                if((caller.getVoiceCallMinute() + minute) - 5 <= 0){
                    return 0;
                }else if ((caller.getVoiceCallMinute() + minute) - 5 > 0) {
                    double cost = (caller.getVoiceCallMinute() + minute) - 5 * voiceCallCost;
                    return cost * (100 - discountRatio) / 100;
                }
            }
            return minute * voiceCallCost * (100 - discountRatio) / 100;
        }


        double cost = minute * voiceCallCost;
        return cost;
    }

    public double calculateMessagingCost(int quantity, Subscriber sender, Subscriber receiver) {
        boolean isTeenager = (sender.getAge() > 10 && sender.getAge() < 18);
        if (isTeenager) {
            if(sender.getTextMessageCount() < 10){
                if((sender.getTextMessageCount() + quantity) - 10 <= 0){
                    return 0;
                }else {
                    if (sender.s_provider.p_id == receiver.s_provider.p_id) {
                        double cost = (sender.getTextMessageCount() + quantity) - 10 * messagingCost;
                        return cost * (100 - discountRatio) / 100;
                    }
                    return (sender.getTextMessageCount() + quantity) - 10 * messagingCost;
                }
            }
        }

        if (sender.s_provider.p_id == receiver.s_provider.p_id) {
            return quantity * messagingCost * (100 - discountRatio) / 100;
        }

        double cost = quantity * messagingCost;
        return cost;
    }

    public double calculateInternetCost(double amount, Subscriber user) {
        boolean isTeenager = (user.getAge() > 10 && user.getAge() < 18);
        if (isTeenager) {
            if(user.getInternetUsage() < 5){
                if((user.getInternetUsage() + amount) - 5 <= 0){
                    return 0;
                }else {
                    return (user.getInternetUsage() + amount) - 5 * messagingCost;
                }
            }
        }

        double cost = amount * internetCost;
        return cost;
    }

    public boolean addSubscriber(Subscriber subscriber) {
        if (subscribersList.contains(subscriber)) {
            return false;
        }

        subscribersList.add(subscriber);
        return true;
    }

    public boolean removeSubscriber(Subscriber subscriber) {
        if (!subscribersList.contains(subscriber)) {
            return false;
        }

        subscribersList.remove(subscriber);
        return true;
    }

    @Override
    public String toString() {
        return this.p_name + " service provider, message cost is " + this.messagingCost + ", voice call cost is " + this.voiceCallCost + ", internet cost is " + this.internetCost + " also discount ratio is " + this.discountRatio;
    }
}
