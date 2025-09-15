package service;
import model.CartItem;
import model.Product;

import java.util.*;

public class CartService {
    private Map<Integer, CartItem> cart = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (cart.containsKey(product.getId())) {
            CartItem item = cart.get(product.getId());
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            cart.put(product.getId(), new CartItem(product, quantity));
        }
    }

    public void updateQuantity(int productId, int quantity) {
        if (cart.containsKey(productId)) {
            cart.get(productId).setQuantity(quantity);
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public void removeProduct(int productId) {
        if (cart.remove(productId) == null) {
            System.out.println("Product not found in cart.");
        }
    }

    public double calculateTotal() {
        return cart.values().stream()
                .mapToDouble(CartItem::getItemTotal)
                .sum();
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            cart.values().forEach(System.out::println);
            System.out.println("Total: $" + calculateTotal());
        }
    }
}
