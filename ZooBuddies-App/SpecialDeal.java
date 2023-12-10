import java.util.*;

public class SpecialDeal {
    private String description;
    private Map<Integer, Double> conditions; // Map of (number of attractions, discount percentage)

    public SpecialDeal(int id, String description, int minAttractions, double discountPercentage) {
        this.description = description;
        this.conditions = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void addCondition(int numAttractions, double discountPercentage) {
        conditions.put(numAttractions, discountPercentage);
    }

    public void removeCondition(int numAttractions) {
        conditions.remove(numAttractions);
    }

    public Map<Integer, Double> getConditions() {
        return conditions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return 0;
    }
}
