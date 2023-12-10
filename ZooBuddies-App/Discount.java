public class Discount {
    private int id;
    private String description;
    private double percentage;
    private String code;
    private String category;

    public Discount(int id, String description, double percentage, String code, String category) {
        this.id = id;
        this.description = description;
        this.percentage = percentage;
        this.code = code;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public void applyDiscount(Visitor visitor) {
        double discountAmount = visitor.getBalance() * (percentage / 100.0);
        visitor.deductBalance(discountAmount);
    }
}
