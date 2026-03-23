package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.TableStatus;

public class Table {
    private int ID;
    private int numOfSeats;
    private TableStatus status;

    public Table(int ID, int numOfSeats) {
        this.ID = ID;
        this.numOfSeats = numOfSeats;
        this.status = TableStatus.AVAILABLE;
    }

    public int getID() {return ID;}
    public int getNumOfSeats() {return numOfSeats;}
    public TableStatus getStatus() {return status;}

    public void setNumOfSeats(int numOfSeats) {this.numOfSeats = numOfSeats;}
    public void setStatus(TableStatus status) {this.status = status;}

    @Override
    public String toString() {
        return "Table{" +
                "ID=" + ID +
                ", numOfSeats=" + numOfSeats +
                ", status=" + status +
                '}';
    }
}
