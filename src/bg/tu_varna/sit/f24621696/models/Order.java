package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private static int nextID = 1;
    private int ID;
    private int idOfTable;
    private HashMap<Integer, MenuItem> orderList = new HashMap<>();
    private OrderStatus status;
    private String dateAndTime;
    private double finalSum;

    public Order(int idOfTable) {
        this.ID = nextID++;
        this.idOfTable = idOfTable;
        this.status = OrderStatus.OPEN;
    }

    public String getDateAndTime() {return dateAndTime;}
    public double getFinalSum() {return finalSum;}
    public int getID() {return ID;}
    public int getIdOfTable() {return idOfTable;}
    public OrderStatus getStatus() {return status;}
    public HashMap<Integer, MenuItem> getOrderList() {
        return orderList;
    }

    public void setDateAndTime(String dateAndTime) {this.dateAndTime = dateAndTime;}
    public void setFinalSum(double finalSum) {this.finalSum = finalSum;}
    public void setIdOfTable(int idOfTable) {this.idOfTable = idOfTable;}
    public void setStatus(OrderStatus status) {this.status = status;}

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", finalSum=" + finalSum +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", idOfTable=" + idOfTable +
                ", orderList=" + orderList +
                ", status=" + status +
                '}';
    }
}
