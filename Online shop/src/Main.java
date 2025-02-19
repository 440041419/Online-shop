import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Define product categories and their respective items
        String[] categories = {"Electronics", "Clothing", "Books"};
        String[] electronics = {"Laptop", "Smartphone", "Headphones"};
        String[] clothing = {"T-Shirt", "Jeans", "Jacket"};
        String[] books = {"Java Programming", "Data Structures", "Algorithms"};
        
        // Define prices for each product category
        double[] electronicsPrices = {999.99, 499.99, 29.99};
        double[] clothingPrices = {19.99, 39.99, 89.99};
        double[] booksPrices = {59.99, 49.99, 69.99};
        
        // Define stock quantities for each product category
        int[] electronicsStock = {10, 0, 5};
        int[] clothingStock = {20, 15, 0};
        int[] booksStock = {7, 3, 0};
        
        // Initialize shopping cart arrays for each category
        int[] electronicsCart = new int[electronics.length];
        int[] clothingCart = new int[clothing.length];
        int[] booksCart = new int[books.length];

        // Initialize order history and shipment status lists
        ArrayList<String> orderHistory = new ArrayList<>();
        ArrayList<Boolean> orderShippedStatus = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean adminAsked = false;

        // Main loop for the online shop
        while (running) {
            if (!adminAsked) {
                // Check if the user is an admin
                System.out.print("Are you an admin? (yes/no): ");
                String isAdmin = scanner.next();
                if (isAdmin.equalsIgnoreCase("yes")) {
                    // Admin authentication
                    System.out.print("Enter admin password (password is 123): ");
                    String password = scanner.next();
                    if (!password.equals("123")) {
                        System.out.println("Incorrect password. Returning to main menu.");
                        continue;
                    } else {
                        System.out.println("Welcome, Admin!");
                        boolean adminRunning = true;
                        while (adminRunning) {
                            // Admin menu options
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. View Orders");
                            System.out.println("2. Mark Order as Shipped");
                            System.out.println("3. Exit Admin Menu");
                            System.out.print("Choose an option: ");
                            int adminChoice = scanner.nextInt();

                            if (adminChoice == 1) {
                                // View order history
                                System.out.println("\nOrder History:");
                                if (orderHistory.isEmpty()) {
                                    System.out.println("No orders have been made.");
                                } else {
                                    for (int i = 0; i < orderHistory.size(); i++) {
                                        System.out.println((i + 1) + ". " + orderHistory.get(i) + " - " + (orderShippedStatus.get(i) ? "Shipped" : "Not Shipped"));
                                    }
                                }
                            } else if (adminChoice == 2) {
                                // Mark an order as shipped
                                System.out.print("Enter the order number to mark as shipped: ");
                                int orderNumber = scanner.nextInt() - 1;
                                if (orderNumber >= 0 && orderNumber < orderHistory.size()) {
                                    orderShippedStatus.set(orderNumber, true);
                                    System.out.println("Order " + (orderNumber + 1) + " marked as shipped.");
                                } else {
                                    System.out.println("Invalid order number.");
                                }
                            } else if (adminChoice == 3) {
                                // Exit admin menu
                                adminRunning = false;
                            } else {
                                System.out.println("Invalid option. Please try again.");
                            }
                        }
                        continue;
                    }
                }
                adminAsked = true;
            }

            // Main menu options for the user
            System.out.println("\nWelcome to the Basic Online Shop!");
            System.out.println("1. Browse Product Categories");
            System.out.println("2. View Cart");
            System.out.println("3. Update Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Browse product categories
                System.out.println("\nProduct Categories:");
                for (int i = 0; i < categories.length; i++) {
                    System.out.println((i + 1) + ". " + categories[i]);
                }
                System.out.print("Select a category: ");
                int categoryChoice = scanner.nextInt() - 1;

                if (categoryChoice == 0) {
                    // Display electronics products
                    System.out.println("\nElectronics:");
                    for (int i = 0; i < electronics.length; i++) {
                        System.out.println((i + 1) + ". " + electronics[i] + " - $" + electronicsPrices[i] + " - " + (electronicsStock[i] > 0 ? "In Stock (" + electronicsStock[i] + ")" : "Out of Stock"));
                    }
                    System.out.print("Select a product to add to cart or 0 to go back: ");
                    int productChoice = scanner.nextInt() - 1;

                    if (productChoice >= 0 && productChoice < electronics.length) {
                        if (electronicsStock[productChoice] > 0) {
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();

                            if (quantity > 0 && quantity <= electronicsStock[productChoice]) {
                                electronicsCart[productChoice] += quantity;
                                electronicsStock[productChoice] -= quantity;
                                System.out.println("Added to cart: " + electronics[productChoice] + " x" + quantity);
                            } else {
                                System.out.println("Invalid quantity. Please try again.");
                            }
                        } else {
                            System.out.println("Product is out of stock.");
                        }
                    }
                } else if (categoryChoice == 1) {
                    // Display clothing products
                    System.out.println("\nClothing:");
                    for (int i = 0; i < clothing.length; i++) {
                        System.out.println((i + 1) + ". " + clothing[i] + " - $" + clothingPrices[i] + " - " + (clothingStock[i] > 0 ? "In Stock (" + clothingStock[i] + ")" : "Out of Stock"));
                    }
                    System.out.print("Select a product to add to cart or 0 to go back: ");
                    int productChoice = scanner.nextInt() - 1;

                    if (productChoice >= 0 && productChoice < clothing.length) {
                        if (clothingStock[productChoice] > 0) {
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();

                            if (quantity > 0 && quantity <= clothingStock[productChoice]) {
                                clothingCart[productChoice] += quantity;
                                clothingStock[productChoice] -= quantity;
                                System.out.println("Added to cart: " + clothing[productChoice] + " x" + quantity);
                            } else {
                                System.out.println("Invalid quantity. Please try again.");
                            }
                        } else {
                            System.out.println("Product is out of stock.");
                        }
                    }
                } else if (categoryChoice == 2) {
                    // Display books products
                    System.out.println("\nBooks:");
                    for (int i = 0; i < books.length; i++) {
                        System.out.println((i + 1) + ". " + books[i] + " - $" + booksPrices[i] + " - " + (booksStock[i] > 0 ? "In Stock (" + booksStock[i] + ")" : "Out of Stock"));
                    }
                    System.out.print("Select a product to add to cart or 0 to go back: ");
                    int productChoice = scanner.nextInt() - 1;

                    if (productChoice >= 0 && productChoice < books.length) {
                        if (booksStock[productChoice] > 0) {
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();

                            if (quantity > 0 && quantity <= booksStock[productChoice]) {
                                booksCart[productChoice] += quantity;
                                booksStock[productChoice] -= quantity;
                                System.out.println("Added to cart: " + books[productChoice] + " x" + quantity);
                            } else {
                                System.out.println("Invalid quantity. Please try again.");
                            }
                        } else {
                            System.out.println("Product is out of stock.");
                        }
                    }
                } else {
                    System.out.println("Invalid category. Please try again.");
                }
            } else if (choice == 2) {
                // View items in the cart
                System.out.println("\nYour Cart:");
                boolean isEmpty = true;
                for (int i = 0; i < electronicsCart.length; i++) {
                    if (electronicsCart[i] > 0) {
                        System.out.println(electronics[i] + " - Quantity: " + electronicsCart[i]);
                        isEmpty = false;
                    }
                }
                for (int i = 0; i < clothingCart.length; i++) {
                    if (clothingCart[i] > 0) {
                        System.out.println(clothing[i] + " - Quantity: " + clothingCart[i]);
                        isEmpty = false;
                    }
                }
                for (int i = 0; i < booksCart.length; i++) {
                    if (booksCart[i] > 0) {
                        System.out.println(books[i] + " - Quantity: " + booksCart[i]);
                        isEmpty = false;
                    }
                }
                if (isEmpty) {
                    System.out.println("Your cart is empty.");
                }
            } else if (choice == 3) {
                // Update quantities of items in the cart
                System.out.println("\nUpdate Cart:");

                ArrayList<String> cartItems = new ArrayList<>();
                ArrayList<Integer> cartQuantities = new ArrayList<>();
                ArrayList<Double> cartPrices = new ArrayList<>();
                ArrayList<int[]> cartStocks = new ArrayList<>();

                for (int i = 0; i < electronicsCart.length; i++) {
                    if (electronicsCart[i] > 0) {
                        cartItems.add(electronics[i]);
                        cartQuantities.add(electronicsCart[i]);
                        cartPrices.add(electronicsPrices[i]);
                        cartStocks.add(new int[]{electronicsCart[i], electronicsStock[i]});
                    }
                }
                for (int i = 0; i < clothingCart.length; i++) {
                    if (clothingCart[i] > 0) {
                        cartItems.add(clothing[i]);
                        cartQuantities.add(clothingCart[i]);
                        cartPrices.add(clothingPrices[i]);
                        cartStocks.add(new int[]{clothingCart[i], clothingStock[i]});
                    }
                }
                for (int i = 0; i < booksCart.length; i++) {
                    if (booksCart[i] > 0) {
                        cartItems.add(books[i]);
                        cartQuantities.add(booksCart[i]);
                        cartPrices.add(booksPrices[i]);
                        cartStocks.add(new int[]{booksCart[i], booksStock[i]});
                    }
                }

                if (cartItems.isEmpty()) {
                    System.out.println("Your cart is empty. Nothing to update.");
                    continue;
                }

                for (int i = 0; i < cartItems.size(); i++) {
                    System.out.printf("%d. %s - Quantity: %d, Price per item: $%.2f%n", (i + 1), cartItems.get(i), cartQuantities.get(i), cartPrices.get(i));
                }
                System.out.print("Select an item to update (or 0 to go back): ");
                int itemChoice = scanner.nextInt() - 1;

                if (itemChoice >= 0 && itemChoice < cartItems.size()) {
                    System.out.print("Enter new quantity (0 to remove): ");
                    int newQuantity = scanner.nextInt();

                    int[] stockInfo = cartStocks.get(itemChoice);
                    int currentQuantity = stockInfo[0];
                    int stockAvailable = stockInfo[1];

                    if (newQuantity >= 0 && newQuantity <= currentQuantity + stockAvailable) {
                        int quantityDifference = newQuantity - currentQuantity;
                        stockInfo[1] -= quantityDifference;

                        if (cartItems.get(itemChoice).equals(electronics[itemChoice])) {
                            electronicsCart[itemChoice] = newQuantity;
                            electronicsStock[itemChoice] += quantityDifference;
                        } else if (cartItems.get(itemChoice).equals(clothing[itemChoice])) {
                            clothingCart[itemChoice] = newQuantity;
                            clothingStock[itemChoice] += quantityDifference;
                        } else if (cartItems.get(itemChoice).equals(books[itemChoice])) {
                            booksCart[itemChoice] = newQuantity;
                            booksStock[itemChoice] += quantityDifference;
                        }

                        System.out.println("Updated cart: " + cartItems.get(itemChoice) + " x" + newQuantity);
                    } else {
                        System.out.println("Invalid quantity. Please try again.");
                    }
                } else {
                    System.out.println("Invalid choice. Returning to main menu.");
                }

            } else if (choice == 4) {
                // Remove items from the cart
                System.out.println("\nRemove from Cart:");
                System.out.println("Select a category to remove from (1. Electronics, 2. Clothing, 3. Books): ");
                int categoryChoice = scanner.nextInt() - 1;

                String[] selectedProducts;
                int[] selectedCart;
                int[] selectedStock;

                if (categoryChoice == 0) {
                    selectedProducts = electronics;
                    selectedCart = electronicsCart;
                    selectedStock = electronicsStock;
                } else if (categoryChoice == 1) {
                    selectedProducts = clothing;
                    selectedCart = clothingCart;
                    selectedStock = clothingStock;
                } else if (categoryChoice == 2) {
                    selectedProducts = books;
                    selectedCart = booksCart;
                    selectedStock = booksStock;
                } else {
                    System.out.println("Invalid category. Please try again.");
                    continue;
                }

                System.out.print("Select a product to remove or 0 to go back: ");
                int productChoice = scanner.nextInt() - 1;

                if (productChoice >= 0 && productChoice < selectedProducts.length && selectedCart[productChoice] > 0) {
                    selectedStock[productChoice] += selectedCart[productChoice];
                    selectedCart[productChoice] = 0;
                    System.out.println("Removed from cart: " + selectedProducts[productChoice]);
                } else {
                    System.out.println("Invalid product choice or product not in cart. Please try again.");
                }
            } else if (choice == 5) {
                // Checkout process
                System.out.println("\nCheckout:");
                double total = 0;
                StringBuilder orderSummary = new StringBuilder("Order Summary:\n");
                for (int i = 0; i < electronicsCart.length; i++) {
                    if (electronicsCart[i] > 0) {
                        double itemTotal = electronicsPrices[i] * electronicsCart[i];
                        total += itemTotal;
                        orderSummary.append(String.format("%s x%d - $%.2f%n", electronics[i], electronicsCart[i], itemTotal));
                    }
                }
                for (int i = 0; i < clothingCart.length; i++) {
                    if (clothingCart[i] > 0) {
                        double itemTotal = clothingPrices[i] * clothingCart[i];
                        total += itemTotal;
                        orderSummary.append(String.format("%s x%d - $%.2f%n", clothing[i], clothingCart[i], itemTotal));
                    }
                }
                for (int i = 0; i < booksCart.length; i++) {
                    if (booksCart[i] > 0) {
                        double itemTotal = booksPrices[i] * booksCart[i];
                        total += itemTotal;
                        orderSummary.append(String.format("%s x%d - $%.2f%n", books[i], booksCart[i], itemTotal));
                    }
                }
                orderSummary.append(String.format("Total: $%.2f%n", total));

                System.out.print("Do you want to confirm the order? (yes/no): ");
                System.out.println(orderSummary);
                String confirm = scanner.next();
                if (confirm.equalsIgnoreCase("yes")) {
                    System.out.println("Thank you for your purchase!");
                    orderHistory.add(orderSummary.toString());
                    orderShippedStatus.add(false);
                    for (int i = 0; i < electronicsCart.length; i++) {
                        electronicsCart[i] = 0;
                    }
                    for (int i = 0; i < clothingCart.length; i++) {
                        clothingCart[i] = 0;
                    }
                    for (int i = 0; i < booksCart.length; i++) {
                        booksCart[i] = 0;
                    }
                    adminAsked = false; 
                } else {
                    System.out.println("Order not confirmed. Returning to main menu.");
                }
            } else if (choice == 6) {
                // Exit the shop
                running = false;
                System.out.println("Thank you for visiting the Basic Online Shop!");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
