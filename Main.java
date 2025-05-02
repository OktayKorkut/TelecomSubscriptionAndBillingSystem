import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
        ArrayList<ServiceProvider> providers = new ArrayList<ServiceProvider>();

        int menu = 0;
        Scanner scn = new Scanner(System.in);
        do {
            System.out.println(
                    "1- Creating a new Service Provider\n" +
                            "2- Create a new Subscriber\n" +
                            "3- Voice Call: A subscriber calls another subscriber\n" +
                            "4- Messaging: A subscriber sends a message to another subscriber\n" +
                            "5- Internet: A subscriber connects to the Internet\n" +
                            "6- Pay Invoice: A subscriber pays his/her invoice\n" +
                            "7- Change ServiceProvider: A subscriber changes his/her provider\n" +
                            "8- Change Limit: A subscriber changes his/her usage limit for the Invoice\n" +
                            "9- List all Subscribers\n" +
                            "10- List all Service Providers\n" +
                            "11- Exit");
            System.out.print("Enter the number of the menu: ");
            menu = scn.nextInt();

            switch (menu){
                case 1:
                    System.out.println("Enter the name of the service provider");
                    String name = scn.next();
                    System.out.println("Enter the voice call cost");
                    double voiceCallCost = scn.nextDouble();
                    System.out.println("Enter the messaging cost");
                    double messagingCost = scn.nextDouble();
                    System.out.println("Enter the internet cost");
                    double internetCost = scn.nextDouble();
                    System.out.println("Enter the discount ratio");
                    int discountRatio = scn.nextInt();
                    providers.add(new ServiceProvider(name, voiceCallCost, messagingCost, internetCost, discountRatio));
                    break;
                case 2:
                    System.out.println("Enter the name of the subscriber");
                    name = scn.next();
                    System.out.println("Enter the age of the subscriber");
                    int age = scn.nextInt();
                    System.out.println("Enter the limit of the subscriber");
                    int usageLimit = scn.nextInt();
                    System.out.println("Select Provider of the subscriber");
                    int subscIndex = 0;
                    for (int i = 1; i <= providers.size(); i++) {
                        System.out.println(i + "- " + providers.get(i - 1).getProviderName());
                    }
                    subscIndex = scn.nextInt();
                    subscribers.add(new Subscriber(name, age, providers.get(subscIndex-1),usageLimit ));
                    break;
                case 3:
                    System.out.println("Select the caller");
                    int callerIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    callerIndex = scn.nextInt();
                    Subscriber caller = subscribers.get(callerIndex-1);
                    System.out.println("Select the receiver");
                    int receiverIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    receiverIndex = scn.nextInt();
                    Subscriber receiver = subscribers.get(receiverIndex-1);
                    System.out.println("Enter the duration of the call");
                    int duration = scn.nextInt();

                    caller.makeVoiceCall(duration, receiver);

                    break;
                case 4:
                    System.out.println("Select the sender");
                    int senderIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    senderIndex = scn.nextInt();
                    Subscriber sender = subscribers.get(senderIndex-1);
                    System.out.println("Select the receiver");
                    receiverIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    receiverIndex = scn.nextInt();
                    receiver = subscribers.get(receiverIndex-1);
                    System.out.println("Enter message quantity");
                    int messageCount = scn.nextInt();
                    sender.sendMessage(messageCount, receiver);

                    break;
                case 5:
                    System.out.println("Select the subscriber");
                    int subscriberIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    subscriberIndex = scn.nextInt();
                    Subscriber subscriber = subscribers.get(subscriberIndex-1);
                    System.out.println("Enter the MB that used");
                    int mbAmount = scn.nextInt();
                    subscriber.connectToInternet(mbAmount);
                    break;
                case 6:
                    System.out.println("Select the subscriber");
                    subscriberIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    subscriberIndex = scn.nextInt();
                    subscriber = subscribers.get(subscriberIndex-1);
                    System.out.println(subscriber.invoice.toString());
                    System.out.println("Enter the amount of money that subscriber will pay");

                    double amount = scn.nextDouble();
                    subscriber.makePayment(amount);
                    break;
                case 7:
                    System.out.println("Select the subscriber");
                    subscriberIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    subscriberIndex = scn.nextInt();
                    subscriber = subscribers.get(subscriberIndex-1);
                    System.out.println("Select the new provider");
                    int providerIndex = 0;
                    for (int i = 1; i <= providers.size(); i++) {
                        System.out.println(i + "- " + providers.get(i - 1).getProviderName());
                    }
                    providerIndex = scn.nextInt();
                    subscriber.changeServiceProvider(providers.get(providerIndex-1));
                    break;
                case 8:
                    System.out.println("Select the subscriber");
                    subscriberIndex = 0;
                    for (int i = 1; i <= subscribers.size(); i++) {
                        System.out.println(i + "- " + subscribers.get(i - 1).getName());
                    }
                    subscriberIndex = scn.nextInt();
                    subscriber = subscribers.get(subscriberIndex-1);
                    System.out.println("Enter the new limit");
                    int newLimit = scn.nextInt();
                    subscriber.setUsageLimit(newLimit);
                    break;
                case 9:
                    for (Subscriber subsc : subscribers) {
                        System.out.println(subsc);
                    }
                    break;
                case 10:
                    for (ServiceProvider provider : providers) {
                        System.out.println(provider);
                    }
                    break;
            }
        }while (menu != 11);
    }
}