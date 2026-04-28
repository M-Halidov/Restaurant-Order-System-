package bg.tu_varna.sit.f24621696.enums;

import java.io.Serializable;

/**
 * Represents the current status of a table.
 */
public enum TableStatus implements Serializable {
    /**
     * Indicates that the table is available for seating.
     */
    AVAILABLE,

    /**
     * Indicates that the table is currently occupied by customers.
     */
    OCCUPIED
}
