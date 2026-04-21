package bg.tu_varna.sit.f24621696.enums;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    OPEN("open"),
    PAID_FOR("paid_for"),
    CANCELLED("cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus getStatus(String orderStatus) {
        for (OrderStatus s : OrderStatus.values()) {
            if (s.status.equals(orderStatus)) {
                return s;
            }
        }

        throw new CommandException("Unknown status " + orderStatus + "\nValid: open, paid_for, cancelled");
    }
}
