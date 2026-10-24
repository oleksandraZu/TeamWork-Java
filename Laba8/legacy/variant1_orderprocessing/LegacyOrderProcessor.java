package variant1_orderprocessing;

import java.util.Scanner;

public class LegacyOrderProcessor {
    public void processConsoleOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Оформлення замовлення ===");
        System.out.print("Введіть ім'я клієнта: ");
        String name = scanner.nextLine();
        System.out.print("Введіть тип клієнта (New/Regular/VIP): ");
        String customerType = scanner.nextLine();

        Customer customer = new Customer();
        customer.id = 1;
        customer.name = name;
        customer.email = "client@example.com";
        customer.type = customerType;
        customer.totalSpent = 15000.0;

        Order order = new Order();
        order.id = 101;
        order.customer = customer;
        order.status = "New";

        Product p1 = new Product();
        p1.id = 1; p1.name = "Ноутбук"; p1.price = 30000.0; p1.category = "Electronics";
        Product p2 = new Product();
        p2.id = 2; p2.name = "Мишка"; p2.price = 800.0; p2.category = "Accessories";

        OrderItem item1 = new OrderItem(); item1.product = p1; item1.quantity = 1;
        OrderItem item2 = new OrderItem(); item2.product = p2; item2.quantity = 2;
        order.items.add(item1);
        order.items.add(item2);

        double subtotal = 0.0;
        for (OrderItem item : order.items) {
            subtotal += item.product.price * item.quantity;
        }
        order.totalAmount = subtotal;

        double discountRate = 0.0;
        if ("VIP".equalsIgnoreCase(order.customer.type)) {
            discountRate = 0.15;
        } else if ("Regular".equalsIgnoreCase(order.customer.type)) {
            discountRate = 0.05;
        } else if ("New".equalsIgnoreCase(order.customer.type)) {
            discountRate = 0.0;
        }
        order.discountAmount = order.totalAmount * discountRate;

        order.taxAmount = (order.totalAmount - order.discountAmount) * 0.20;
        order.finalAmount = order.totalAmount - order.discountAmount + order.taxAmount;
        order.status = "Processed";

        String receipt = "Замовлення #" + order.id + " для " + order.customer.name +
                "\nСума: " + order.totalAmount + " грн" +
                "\nЗнижка: " + order.discountAmount + " грн" +
                "\nПДВ: " + order.taxAmount + " грн" +
                "\nДо сплати: " + order.finalAmount + " грн";
        System.out.println(receipt);

        System.out.println("[БД] Замовлення #" + order.id + " збережено у файл orders.txt.");
        System.out.println("[Email] Повідомлення відправлено на " + order.customer.email);
    }
}
