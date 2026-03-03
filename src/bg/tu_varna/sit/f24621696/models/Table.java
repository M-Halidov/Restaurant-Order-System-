package bg.tu_varna.sit.f24621696.models;

public class Table {
    private int ID;
    private int numOfSeats;
    private TableStatus status;

    public Table(int ID, int numOfSeats, TableStatus status) {
        this.ID = ID;
        this.numOfSeats = numOfSeats;
        this.status = status;
    }

    public int getId() {return ID;}
    public int getNumOfSeats() {return numOfSeats;}
    public TableStatus getStatus() {return status;}

    public void setId(int ID) {this.ID = ID;}
    public void setNumOfSeats(int numOfSeats) {this.numOfSeats = numOfSeats;}
    public void setStatus(TableStatus status) {this.status = status;}
}
