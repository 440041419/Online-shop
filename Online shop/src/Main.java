import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] categories = {"Electronics", "Clothing", "Books"};
        String[] electronics = {"Laptop", "Smartphone", "Headphones"};
        String[] clothing = {"T-Shirt", "Jeans", "Jacket"};
        String[] books = {"Java Programming", "Data Structures", "Algorithms"};
        
        double[] electronicsPrices = {999.99, 499.99, 29.99};
        double[] clothingPrices = {19.99, 39.99, 89.99};
        double[] booksPrices = {59.99, 49.99, 69.99};
        
        int[] electronicsStock = {10, 0, 5};
        int[] clothingStock = {20, 15, 0};
        int[] booksStock = {7, 3, 0};
        
        int[] electronicsCart = new int[electronics.length];
        int[] clothingCart = new int[clothing.length];
        int[] booksCart = new int[books.length];

        ArrayList<String> orderHistory = new ArrayList<>();
        ArrayList<Boolean> orderShippedStatus = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean adminAsked = false;

        while (running) {
            if (!adminAsked) {
                System.out.print("Are you an admin? (yes/no): ");
                String isAdmin = scanner.next();
                if (isAdmin.equalsIgnoreCase("yes")) {
                    System.out.print("Enter admin password (password is 123): ");
                    String password = scanner.next();
                    if (!password.equals("123")) {
                        System.out.println("Incorrect password. Returning to main menu.");
                        continue;
                    } else {
                        System.out.println("Welcome, Admin!");
                        // Admin-specific functionality
                        boolean adminRunning = true;
                        while (adminRunning) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. View Orders");
                            System.out.println("2. Mark Order as Shipped");
                            System.out.println("3. Exit Admin Menu");
                            System.out.print("Choose an option: ");
                            int adminChoice = scanner.nextInt();

                            if (adminChoice == 1) {
                                System.out.println("\nOrder History:");
                                if (orderHistory.isEmpty()) {
                                    System.out.println("No orders have been made.");
                                } else {
                                    for (int i = 0; i < orderHistory.size(); i++) {
                                        System.out.println((i + 1) + ". " + orderHistory.get(i) + " - " + (orderShippedStatus.get(i) ? "Shipped" : "Not Shipped"));
                                    }
                                }
                            } else if (adminChoice == 2) {
                                System.out.print("Enter the order number to mark as shipped: ");
                                int orderNumber = scanner.nextInt() - 1;
                                if (orderNumber >= 0 && orderNumber < orderHistory.size()) {
                                    orderShippedStatus.set(orderNumber, true);
                                    System.out.println("Order " + (orderNumber + 1) + " marked as shipped.");
                                } else {
                                    System.out.println("Invalid order number.");
                                }
                            } else if (adminChoice == 3) {
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
                System.out.println("\nProduct Categories:");
                for (int i = 0; i < categories.length; i++) {
                    System.out.println((i + 1) + ". " + categories[i]);
                }
                System.out.print("Select a category: ");
                int categoryChoice = scanner.nextInt() - 1;

                if (categoryChoice == 0) {
                    System.out.println("\nElectronics:");
                    for (int i = 0; i < electronics.length; i++) {
                        System.out.println((i + 1) + ". " + electronics[i] + " - $" + electronicsPrices[i] + " - " + (electronicsStock[i] > 0 ? "In Stock" : "Out of Stock"));
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
                    System.out.println("\nClothing:");
                    for (int i = 0; i < clothing.length; i++) {
                        System.out.println((i + 1) + ". " + clothing[i] + " - $" + clothingPrices[i] + " - " + (clothingStock[i] > 0 ? "In Stock" : "Out of Stock"));
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
                    System.out.println("\nBooks:");
                    for (int i = 0; i < books.length; i++) {
                        System.out.println((i + 1) + ". " + books[i] + " - $" + booksPrices[i] + " - " + (booksStock[i] > 0 ? "In Stock" : "Out of Stock"));
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
                System.out.println("\nUpdate Cart:");
                System.out.println("Select a category to update (1. Electronics, 2. Clothing, 3. Books): ");
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

                System.out.print("Select a product to update quantity or 0 to go back: ");
                int productChoice = scanner.nextInt() - 1;

                if (productChoice >= 0 && productChoice < selectedProducts.length && selectedCart[productChoice] > 0) {
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();

                    if (newQuantity >= 0 && newQuantity <= selectedCart[productChoice] + selectedStock[productChoice]) {
                        selectedStock[productChoice] += selectedCart[productChoice] - newQuantity;
                        selectedCart[productChoice] = newQuantity;
                        System.out.println("Updated cart: " + selectedProducts[productChoice] + " x" + newQuantity);
                    } else {
                        System.out.println("Invalid quantity. Please try again.");
                    }
                } else {
                    System.out.println("Invalid product choice or product not in cart. Please try again.");
                }
            } else if (choice == 4) {
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
                running = false;
                System.out.println("Thank you for visiting the Basic Online Shop!");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
