import java.util.ArrayList;
import java.util.List;

public class Attraction {
    private int id;
    private String name;
    private String description;
    private double price;
    private int visitorCount; 
    public Attraction(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
     }

    public int getVisitorCount() {
        return visitorCount;
    }

    public void incrementVisitorCount() {
        visitorCount++;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
 
    private List<Ticket> purchasedTicketList = new ArrayList<>();
 
    public void purchaseTicket(Ticket ticket) {
        purchasedTicketList.add(ticket);
    } 
    public Ticket[] getPurchasedTickets() {
        List<Ticket> purchasedTickets = new ArrayList<>();

        for (Ticket ticket : purchasedTicketList) {
            if (ticket.getAttraction() == this) {
                purchasedTickets.add(ticket);
            }
        }
        return purchasedTickets.toArray(new Ticket[0]);
    }
 
}
