package bg.tu_varna.sit.f24621696.models;

import java.util.ArrayList;

public class Order {
    private int ID;
    private int idOfTable;
    private ArrayList<MenuItem> orderList;
    private OrderStatus status;
    private String dateAndTime;
    private double finalSum;

    public Order(int ID, int idOfTable, ArrayList<MenuItem> orderList, OrderStatus status, String dateAndTime, double finalSum) {
        this.ID = ID;
        this.idOfTable = idOfTable;
        this.orderList = orderList;
        this.status = status;
        this.dateAndTime = dateAndTime;
        this.finalSum = finalSum;
    }

    public String getDateAndTime() {return dateAndTime;}
    public double getFinalSum() {return finalSum;}
    public int getID() {return ID;}
    public int getIdOfTable() {return idOfTable;}
    public ArrayList<MenuItem> getOrderList() {return orderList;}
    public OrderStatus getStatus() {return status;}

    public void setDateAndTime(String dateAndTime) {this.dateAndTime = dateAndTime;}
    public void setFinalSum(double finalSum) {this.finalSum = finalSum;}
    public void setID(int ID) {this.ID = ID;}
    public void setIdOfTable(int idOfTable) {this.idOfTable = idOfTable;}
    public void setOrderList(ArrayList<MenuItem> orderList) {this.orderList = orderList;}
    public void setStatus(OrderStatus status) {this.status = status;}

}
