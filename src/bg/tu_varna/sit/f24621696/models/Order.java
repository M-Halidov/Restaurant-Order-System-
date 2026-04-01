package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private static int nextID = 1;
    private int ID;
    private Table table;
    private HashMap<Integer, MenuItem> items = new HashMap<>();
    private OrderStatus status;
    private String dateAndTime;
    private double finalSum;

    public Order(Table table) {
        this.ID = nextID++;
        this.table = table;
        this.status = OrderStatus.OPEN;
    }

    public String getDateAndTime() {return dateAndTime;}
    public double getFinalSum() {return finalSum;}
    public int getID() {return ID;}
    public Table getTable() {return table;}
    public OrderStatus getStatus() {return status;}
    public HashMap<Integer, MenuItem> getItems() {return items;}

    public void setDateAndTime(String dateAndTime) {this.dateAndTime = dateAndTime;}
    public void setFinalSum(double finalSum) {this.finalSum = finalSum;}
    public void setStatus(OrderStatus status) {this.status = status;}

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", finalSum=" + finalSum +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", table=" + table +
                ", orderList=" + items +
                ", status=" + status +
                '}';
    }
}
