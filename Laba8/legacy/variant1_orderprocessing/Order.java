package variant1_orderprocessing;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public int id;
    public Customer customer;
    public List<OrderItem> items = new ArrayList<>();
    public double totalAmount;
    public double discountAmount;
    public double taxAmount;
    public double finalAmount;
    public String status;
}
