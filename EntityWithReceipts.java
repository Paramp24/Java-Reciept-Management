import java.util.ArrayList;

// Parent class for Store and Customer

public class EntityWithReceipts {
    private String name;
    private ArrayList<Receipt> receipts;

    // Constructor
    public EntityWithReceipts(String name) {
        this.name = name;
        this.receipts = new ArrayList<>();
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Add a receipt to the list
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    // Get receipts
    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    // View receipts
    public void viewReceipts() {
        if (receipts.isEmpty()) {
            System.out.println("No receipts found for: " + name);
            return;
        }

        System.out.println("Receipts for: " + name);
        for (Receipt receipt : receipts) {
            System.out.println("------------------------------");
            receipt.displayReceipt();
        }
    }
}
