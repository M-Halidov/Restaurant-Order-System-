package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;

import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * "Represents a customer order placed at a table.
 * Each order has a unique ID, table, items, status, date and time and finalSum.
 */
public class Order implements Serializable {
    /**
     * Auto-incrementing counter used to assign a unique ID to each new order.
     */
    private static int nextID = 1;

    /**
     * The unique identifier for this order.
     */
    private int ID;

    /**
     * The table object which the order is taking place at.
     */
    private Table table;

    /**
     * Hashmap of menu items and their quantities for this order.
     */
    private HashMap<MenuItem, Integer> items = new HashMap<>();

    /**
     * Current status of the order.
     */
    private OrderStatus status;

    /**
     * Date and time the order was started.
     */
    private LocalDateTime dateAndTime;

    /**
     * Formatter for displaying date and time in the European standard (dd/MM/yyyy HH:mm).
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Final sum of the order.
     */
    private double finalSum;

    /**
     * Creates a new Order with the specified table and assigns it a unique ID.
     * @param table The specified table of this order.
     */
    public Order(Table table) {
        this.ID = nextID++;
        this.table = table;
        this.status = OrderStatus.OPEN;
        this.dateAndTime = LocalDateTime.now();
    }

    /**
     * Returns the date and time of the order.
     * @return the orders date and time.
     */
    public LocalDateTime getDateAndTime() {return dateAndTime;}

    /**
     * Returns the final sum of the order.
     * @return the order final sum.
     */
    public double getFinalSum() {return finalSum;}

    /**
     * Returns the ID of the order.
     * @return the order ID.
     */
    public int getID() {return ID;}

    /**
     * Returns the table of the order.
     * @return the order table.
     */
    public Table getTable() {return table;}

    /**
     * Returns the status of the order.
     * @return the order status.
     */
    public OrderStatus getStatus() {return status;}

    /**
     * Returns the items of the order.
     * @return the order items.
     */
    public HashMap<MenuItem, Integer> getItems() {return items;}

    /**
     * Sets the date and time of the order.
     * @param dateAndTime the new date and time of the order.
     */
    public void setDateAndTime(LocalDateTime dateAndTime) {this.dateAndTime = dateAndTime;}

    /**
     * Returns the final sum of the order.
     * @param finalSum the new final sum of the order.
     */
    public void setFinalSum(double finalSum) {this.finalSum = finalSum;}

    /**
     * Returns the status of the order.
     * @param status the new status of the order.
     */
    public void setStatus(OrderStatus status) {this.status = status;}

    /**
     * Builds a string representation of the order.
     * If the items HashMap is empty, notifies the user of the lack of items.
     * Otherwise, displays each item and its ordered amount.
     * @return the string representation of the order.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID ").append(ID).append(":\n").append(table).append("\n");
        sb.append("status: ").append(status).append(' ').append(dateAndTime.format(formatter)).append(", final sum: ").append(finalSum).append("\n");

        sb.append("Items ordered: ");
        if (items.isEmpty()) {
            sb.append("No items found!");
        } else {
            for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
                sb.append("\n").append(entry.getKey().getName()).append(", amount ordered: ").append(entry.getValue());
            }
        }

        return sb.toString();
    }
}
