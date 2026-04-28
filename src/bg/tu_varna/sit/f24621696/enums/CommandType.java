package bg.tu_varna.sit.f24621696.enums;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;

/**
 * Represents the types of commands available in the application.
 */
public enum CommandType {
    /**
     * Opens a file.
     * If file wasn't given it opens one automatically in the resources' directory.
     */
    OPEN("open"),

    /**
     * Closes the currently open file.
     */
    CLOSE("close"),

    /**
     * Saves the current state to the existing file.
     */
    SAVE("save"),

    /**
     * Saves the current state to a new file location given by the user.
     */
    SAVEAS("saveas"),

    /**
     * Displays all the available commands in the application.
     */
    HELP("help"),

    /**
     * Exits out of the application.
     */
    EXIT("exit"),

    /**
     * Adds an item to the menu,
     */
    ADDITEM("additem"),

    /**
     * Removes an item from the menu.
     */
    REMOVEITEM("removeitem"),

    /**
     * Displays all the items in the menu.
     */
    MENU("menu"),

    /**
     * Creates a new table.
     */
    ADDTABLE("addtable"),

    /**
     * Removes the specified table.
     */
    REMOVETABLE("removetable"),

    /**
     * Displays all the available tables.
     */
    TABLES("tables"),

    /**
     * Opens an order on a specified table.
     */
    OPENORDER("openorder"),

    /**
     * Adds menu items to the order.
     */
    ADDTOORDER("addtoorder"),

    /**
     * Removes menu items from the order.
     */
    REMOVEFROMORDER("removefromorder"),

    /**
     * Displays details about the order.
     */
    SHOWORDER("showorder"),

    /**
     * Successfully concludes the order.
     */
    CLOSEORDER("closeorder"),

    /**
     * Cancels the order and returns all of its items.
     */
    CANCELORDER("cancelorder"),

    /**
     * Displays all the orders by default.
     * Filters by status if argument given.
     */
    ORDERS("orders"),

    /**
     * Displays a profit report for a given date range.
     */
    REPORT("report"),

    /**
     * Displays n amount of items which are the most ordered in a given date range.
     */
    TOPITEMS("topitems"),

    /**
     * Displays items which quantities are under a given threshold.
     */
    LOWSTOCK("lowstock");

    /**
     * The string representation of this command.
     */
    private final String command;

    /**
     * Constructs a CommandType constant with the specified string value.
     * @param command the string representation of the command.
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Returns the CommandType constant matching the given string value.
     * @param command the string value to search for.
     * @return the matching CommandType constant.
     * @throws CommandException if the given command was not found in the enum.
     */
    public static CommandType getCommand(String command) {
        for (CommandType c : CommandType.values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }

        throw new CommandException("Unknown Command: " + command);
    }
}
