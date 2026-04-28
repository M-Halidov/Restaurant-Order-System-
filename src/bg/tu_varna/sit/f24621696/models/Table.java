package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.TableStatus;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class which represents a table used for orders.
 * Each table has a unique ID, number of seats and status.
 */
public class Table implements Serializable {
    /**
     * The unique identifier for this table.
     */
    private int ID;

    /**
     * The number of seats available for this table.
     */
    private int numOfSeats;

    /**
     * The current table status i.e. available or occupied.
     */
    private TableStatus status;

    /**
     * Creates a new Table with the specified params and assigns the table status to be available.
     * @param ID the unique ID of the table.
     * @param numOfSeats the number of seats available for this table.
     */
    public Table(int ID, int numOfSeats) {
        this.ID = ID;
        this.numOfSeats = numOfSeats;
        this.status = TableStatus.AVAILABLE;
    }

    /**
     * Returns the ID of the table.
     * @return the table ID.
     */
    public int getID() {return ID;}

    /**
     * Returns the number of seats available for the table.
     * @return the table seats.
     */
    public int getNumOfSeats() {return numOfSeats;}

    /**
     * Returns the status of the table.
     * @return the table status.
     */
    public TableStatus getStatus() {return status;}

    /**
     * Sets the number of seats available for the table.
     * @param numOfSeats the new number of seats available for the table.
     */
    public void setNumOfSeats(int numOfSeats) {this.numOfSeats = numOfSeats;}

    /**
     * Sets the status of the table.
     * @param status the new status of the table.
     */
    public void setStatus(TableStatus status) {this.status = status;}

    /**
     * Checks equality between two Table objects based ID.
     * @param o   the reference object with which to compare.
     * @return true if both objects have the same ID.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Table table)) return false;
        return ID == table.ID;
    }

    /**
     * Computes hashcode based on ID.
     * @return the hash code of this table.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    /**
     * Builds the string representation of this table.
     * @return the string representation of this table.
     */
    @Override
    public String toString() {
        return "Table ID " + ID + ", number of seats: " + numOfSeats + ", table status: " + status;
    }

}
