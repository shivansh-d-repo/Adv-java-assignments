import model.Product;
import service.CartService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        {
            Scanner scanner = new Scanner(System.in);
            CartService cartService = new CartService();

            int choice;
            do {
                System.out.println("\n========= Shopping Cart =========");
                System.out.println("1. Add Product to Cart");
                System.out.println("2. Update Product Quantity");
                System.out.println("3. Remove Product from Cart");
                System.out.println("4. View Cart");
                System.out.println("5. Checkout & Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Product Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = scanner.nextInt();

                        Product newProduct = new Product(id, name, price);
                        cartService.addProduct(newProduct, qty);
                        System.out.println("Product added to cart.");
                        break;

                    case 2:
                        System.out.print("Enter Product ID to update: ");
                        int updateId = scanner.nextInt();
                        System.out.print("Enter new Quantity: ");
                        int newQty = scanner.nextInt();
                        cartService.updateQuantity(updateId, newQty);
                        break;

                    case 3:
                        System.out.print("Enter Product ID to remove: ");
                        int removeId = scanner.nextInt();
                        cartService.removeProduct(removeId);
                        break;

                    case 4:
                        System.out.println("\nYour Cart:");
                        cartService.displayCart();
                        break;

                    case 5:
                        System.out.println("\nFinal Cart:");
                        cartService.displayCart();
                        System.out.println("Thank you for shopping! Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 5);

            scanner.close();
        }
    }
}