import java.util.Date;

public class Invoice {
    private double usageLimit;
    private double currentSpending;
    private Date lastDayToPay;

    public double getCurrentSpending() {
        return currentSpending;
    }

    public Date getLastDayToPay() {
        return lastDayToPay;
    }

    public double getUsageLimit() {
        return usageLimit;
    }

    public Invoice(double usageLimit, Date lastDayToPay) {
        this.usageLimit = usageLimit;
        Date today = new Date();
        Date afterThirtyDay = new Date(today.getTime() + (30 /* day */  * 24 /* day hour */ * 60 /* minute */ * 60 /* seconds */ * 1000));
        if (afterThirtyDay.before(lastDayToPay) && today.after(lastDayToPay)) {
            this.lastDayToPay = lastDayToPay;
        }else if (lastDayToPay == today){
            this.lastDayToPay = afterThirtyDay;
        }else{
            this.lastDayToPay = afterThirtyDay;
        }
    }

    public boolean isLimitExceeded(double amount) {
        if (currentSpending + amount > usageLimit) {
            return true;
        }
        return false;
    }

    public void addCost(double amount) {
        currentSpending += amount;
    }

    public void pay(double amount) {
        if(currentSpending < amount){
            System.out.println("Payment can not received! (more than current spending)");
        }else if (amount == currentSpending) {
            currentSpending = 0;

            Date today = new Date();
            Date afterThirtyDay = new Date(today.getTime() + (30 /* day */  * 24 /* day hour */ * 60 /* minute */ * 60 /* seconds */ * 1000));
            lastDayToPay = afterThirtyDay;
            System.out.println("Payment Successful!");
        }else if(amount < currentSpending){
            currentSpending -= amount;
        }

    }

    public void changeUsageLimit(double newLimit) {
        usageLimit = newLimit;
    }

    @Override
    public String toString() {
        return "Current Invoice Detail: " +
                "usageLimit = " + usageLimit +
                ", currentSpending = " + currentSpending +
                ", lastDayToPay = " + lastDayToPay;
    }
}
