package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;

import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
    private static int nextID = 1;
    private int ID;
    private Table table;
    private HashMap<MenuItem, Integer> items = new HashMap<>();
    private OrderStatus status;
    private LocalDateTime dateAndTime;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private double finalSum;

    public Order(Table table) {
        this.ID = nextID++;
        this.table = table;
        this.status = OrderStatus.OPEN;
        this.dateAndTime = LocalDateTime.now();
    }

    public LocalDateTime getDateAndTime() {return dateAndTime;}
    public double getFinalSum() {return finalSum;}
    public int getID() {return ID;}
    public Table getTable() {return table;}
    public OrderStatus getStatus() {return status;}
    public HashMap<MenuItem, Integer> getItems() {return items;}

    public void setDateAndTime(LocalDateTime dateAndTime) {this.dateAndTime = dateAndTime;}
    public void setFinalSum(double finalSum) {this.finalSum = finalSum;}
    public void setStatus(OrderStatus status) {this.status = status;}

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
