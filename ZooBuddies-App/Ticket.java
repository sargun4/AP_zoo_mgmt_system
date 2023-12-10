public class Ticket {
    private Attraction attraction;
    private int visitsAllowed;
    private int visitsRemaining;
    private double price;

    public Ticket(Attraction attraction, int quantity) {
        this.attraction = attraction;
        this.visitsAllowed = quantity;
        this.visitsRemaining = quantity;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public int getVisitsAllowed() {
        return visitsAllowed;
    }

    public int getVisitsRemaining() {
        return visitsRemaining;
    }

    public void decrementVisits() {
        if (visitsRemaining > 0) {
            visitsRemaining--;
        }
    }

    public double getPrice() {
        return price;
    }

    public void applyDiscount(Discount discount) {
        double discountAmount = price * (discount.getPercentage() / 100);
        price -= discountAmount;
    }
    public boolean isUsed() {
        return visitsRemaining == 0;
    }
}
 