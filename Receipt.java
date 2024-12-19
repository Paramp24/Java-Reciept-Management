import java.util.ArrayList;

/**
 * Represents a receipt containing details about the store, customer, items purchased, and the total amount.
 */
public class Receipt {
    private Store store;
    private Customer customer;
    private ArrayList<Item> items = new ArrayList<>();
    private double totalAmount;

    /**
     * Sets the store associated with this receipt.
     *
     * @param store The store where the purchase was made.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Sets the customer associated with this receipt.
     *
     * @param customer The customer who made the purchase.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Adds an item to the receipt.
     *
     * @param item The item purchased to be added to the receipt.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Retrieves the list of items associated with this receipt.
     *
     * @return An ArrayList of items purchased in this receipt.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Calculates the total amount for the receipt based on the items added.
     *
     * @return The total amount of the receipt as a double.
     */
    public double calculateTotal() {
        totalAmount = items.stream().mapToDouble(Item::getTotalCost).sum();
        return totalAmount;
    }

    /**
     * Displays the details of the receipt, including the store, customer, items purchased, and total amount.
     */
    public void displayReceipt() {
        System.out.println("Store: " + store.getName());
        System.out.println("Customer: " + customer.getName());
        for (Item item : items) {
            System.out.println(item.getName() + " - $" + item.getPrice() + " x " + item.getQuantity());
        }
        System.out.println("Total: $" + totalAmount);
    }
}
