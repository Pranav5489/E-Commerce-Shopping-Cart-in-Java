import java.util.*;

// Encapsulation: Product class
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return price * quantity; }
}

// Polymorphism: Discount base class
abstract class Discount {
    public abstract double applyDiscount(double totalAmount);
}

class FestiveDiscount extends Discount {
    public double applyDiscount(double totalAmount) {
        return totalAmount * 0.9; // 10% off
    }
}

class BulkDiscount extends Discount {
    public double applyDiscount(double totalAmount) {
        return totalAmount * 0.8; // 20% off
    }
}

// Interface: Payment
interface Payment {
    void pay(double amount);
}

class OnlinePayment implements Payment {
    public void pay(double amount) {
        System.out.println("Total Amount Payable: " + amount);
    }
}

public class ECommerceCart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> cart = new ArrayList<>();

        // Input number of products
        int n = Integer.parseInt(sc.nextLine());

        // Input product details
        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split(" ");
            String name = input[0];
            double price = Double.parseDouble(input[1]);
            int quantity = Integer.parseInt(input[2]);
            cart.add(new Product(name, price, quantity));
        }

        // Input discount type
        String discountType = sc.nextLine().toLowerCase();

        // Calculate total amount
        double totalAmount = 0;
        for (Product p : cart) {
            totalAmount += p.getTotalPrice();
        }

        // Apply discount using polymorphism
        Discount discount;
        if (discountType.equals("festive")) {
            discount = new FestiveDiscount();
        } else if (discountType.equals("bulk")) {
            discount = new BulkDiscount();
        } else {
            discount = new Discount() {
                public double applyDiscount(double total) { return total; }
            };
        }

        double finalAmount = discount.applyDiscount(totalAmount);

        // Print cart details
        for (Product p : cart) {
            System.out.println("Product: " + p.getName() + ", Price: " + p.getPrice() + ", Quantity: " + p.getQuantity());
        }

        // Payment
        Payment payment = new OnlinePayment();
        payment.pay(finalAmount);
    }
}
