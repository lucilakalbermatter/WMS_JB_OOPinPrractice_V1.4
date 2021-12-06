package main.java.data;

import java.util.Date;
import java.util.List;

public class Order {

    String status;
    boolean isPaymentDone;
    int warehouse;
    Date dateOfOrder;
    List<OrderItem> orderItems;
    double totalCost;
}
