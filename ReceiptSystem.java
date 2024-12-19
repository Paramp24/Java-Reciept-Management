import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReceiptSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a list to store receipts
        ArrayList<Receipt> receipts = new ArrayList<>();

        // Create a list to store customers
        ArrayList<Customer> customers = new ArrayList<>();

        // Create a list to store stores
        ArrayList<Store> stores = new ArrayList<>();

        // Main loop to interact with the user
        while (true) {
            System.out.println("1. Add Receipt");
            System.out.println("2. View Receipts");
            System.out.println("3. Generate Reports");
            System.out.println("4. Exit");
            
            int choice = -1; // Initialize with an invalid value
            boolean validInput = false; // To control the loop
            
            while (!validInput) {
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                    validInput = true; // Exit the loop if input is valid
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
            
            switch (choice) {
                case 1:
                    // Add a receipt
                    Receipt receipt = new Receipt();
                    System.out.println("Enter store name: ");
                    String storeName = scanner.next();
                    
                    //check if the store is already exists before add it
                    Store store = findStoreByName(stores, storeName);
                    if (store == null) {
                        store = new Store(storeName);
                        stores.add(store);
                    }
                    receipt.setStore(store);

                    System.out.println("Enter customer name: ");
                    String customerName = scanner.next();

                    //check if the customer is already exists before add it
                    Customer customer = findCustomerByName(customers, customerName);
                    if (customer == null) {
                        customer = new Customer(customerName);
                        customers.add(customer);
                    }
                    receipt.setCustomer(customer);

                    // Add items to the receipt
                    while (true) {
                        System.out.println("Enter item name (or 'done' to finish): ");
                        String itemName = scanner.next();
                        if (itemName.equals("done")) {
                            break;
                        }
                        System.out.println("Enter item price: ");
                        double price = scanner.nextDouble();
                        System.out.println("Enter item quantity: ");
                        int quantity = scanner.nextInt();
                        Item item = new Item(itemName, price, quantity);
                        receipt.addItem(item);
                    }

                    // Calculate total and add receipt to lists
                    receipt.calculateTotal();
                    receipts.add(receipt);
                    store.addReceipt(receipt);
                    customer.addReceipt(receipt);

                    break;

                case 2:
                    // View receipts
                    System.out.println("View receipts by:");
                    System.out.println("1. Customer");
                    System.out.println("2. Store");
                    System.out.print("Enter your choice: ");
                    int viewChoice = scanner.nextInt();

                    if (viewChoice == 1) {
                        System.out.print("Enter customer name: ");
                        String customerNameToView = scanner.next();
                        Customer customerToView = findCustomerByName(customers, customerNameToView);
                        if (customerToView != null) {
                            customerToView.viewReceipts();
                        } else {
                            System.out.println("Customer not found.");
                        }
                    } else if (viewChoice == 2) {
                        System.out.print("Enter store name: ");
                        String storeNameToView = scanner.next();
                        Store storeToView = findStoreByName(stores, storeNameToView);
                        if (storeToView != null) {
                            storeToView.viewReceipts();
                        } else {
                            System.out.println("Store not found.");
                        }
                    }

                    break;

                case 3:

                    System.out.println("Generate reports by:");
                    System.out.println("A. Customer");
                    System.out.println("B. Store");
                    System.out.println("C. Item");
                    System.out.print("Enter your choice: ");

                    String reportChoice = scanner.next();
                    
                    switch (reportChoice.charAt(0)) {
                        case 'a':
                            // Generate report by customer
                            System.out.println("Customer Spending Report:");
                            for (Customer customer13 : customers) {
                                double totalSpending = 0.0;
                                for (Receipt receipt13 : customer13.getReceipts()) {
                                    totalSpending += receipt13.calculateTotal();
                                }
                                System.out.println("Customer: " + customer13.getName() + ", Total Spending: $" + totalSpending);
                            }
                            break;
                
                        case 'B':

                            System.out.println("Store Revenue Report:");
                            for (Store store23 : stores) {
                                double totalRevenue = 0.0;
                                for (Receipt receipt23 : store23.getReceipts()) {
                                    totalRevenue += receipt23.calculateTotal();
                                }
                                System.out.println("Store: " + store23.getName() + ", Total Revenue: $" + totalRevenue);
                            }
                            break;
                        }

                        case 'C':
                            // Generate report by item
                            System.out.println("Item Sales Report:");
                            HashMap<String, Double> itemSales = new HashMap<>();
                            HashMap<String, Integer> itemQuantities = new HashMap<>();

                            for (Receipt receipt33 : receipts) {
                                for (Item item : receipt33.getItems()) {
                                    String itemName = item.getName();
                                    double totalItemCost = item.getTotalCost();
                                    int quantity = item.getQuantity();

                                    // Update sales and quantity
                                    itemSales.put(itemName, itemSales.getOrDefault(itemName, 0.0) + totalItemCost);
                                    itemQuantities.put(itemName, itemQuantities.getOrDefault(itemName, 0) + quantity);
                                }
                            }

                            for (String itemName : itemSales.keySet()) {
                                System.out.println("Item: " + itemName +
                                        ", Total Revenue: $" + itemSales.get(itemName) +
                                        ", Total Quantity Sold: " + itemQuantities.get(itemName));
                            }
                            break;

                        default:
                            System.out.println("Invalid choice.");


                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    // Helper methods
    private static Customer findCustomerByName(ArrayList<Customer> customers, String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    // Helper method: Find store by name
    private static Store findStoreByName(ArrayList<Store> stores, String name) {
        for (Store store : stores) {
            if (store.getName().equalsIgnoreCase(name)) {
                return store;
            }
        }
        return null;
    }
}