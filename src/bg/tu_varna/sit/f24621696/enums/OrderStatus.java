package bg.tu_varna.sit.f24621696.enums;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;

import java.io.Serializable;

/**
 * Represents the current status of the order
 */
public enum OrderStatus implements Serializable {
    /**
     * Indicates that the order is currently ongoing.
     */
    OPEN("open"),

    /**
     * Indicates that the order was paid for and concluded.
     */
    PAID_FOR("paid_for"),

    /**
     * Indicates that the order was cancelled.
     */
    CANCELLED("cancelled");

    /**
     * The string representation of this status.
     */
    private final String status;

    /**
     * Constructs OrderStatus with the specified string value.
     *
     * @param status the string representation of the status.
     */
    OrderStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the OrderStatus constant matching the given string value.
     * @param orderStatus the string value to search for.
     * @return the matching OrderStatus constant.
     * @throws CommandException if the given status was not found in the enum.
     */
    public static OrderStatus getStatus(String orderStatus) {
        for (OrderStatus s : OrderStatus.values()) {
            if (s.status.equals(orderStatus)) {
                return s;
            }
        }

        throw new CommandException("Unknown status " + orderStatus + "\nValid: open, paid_for, cancelled");
    }
}
