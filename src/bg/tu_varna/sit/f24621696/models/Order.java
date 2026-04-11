package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
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
        return "Order{" +
                "ID=" + ID +
                ", finalSum=" + finalSum +
                ", dateAndTime='" + dateAndTime.format(formatter) + '\'' +
                ", table=" + table +
                ", items=" + items +
                ", status=" + status +
                '}';
    }
}
