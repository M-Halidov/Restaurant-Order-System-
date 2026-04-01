package bg.tu_varna.sit.f24621696.enums;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;

public enum CommandType {
    HELP("help"),
    EXIT("exit"),
    ADDITEM("additem"),
    REMOVEITEM("removeitem"),
    MENU("menu"),
    ADDTABLE("addtable"),
    REMOVETABLE("removetable"),
    TABLES("tables"),
    OPENORDER("openorder"),
    ADDTOORDER("addtoorder"),
    REMOVEFROMORDER("removefromorder"),
    SHOWORDER("showorder"),
    CLOSEORDER("closeorder"),
    CANCELORDER("cancelorder"),
    ORDERS("orders");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }


    public static CommandType getCommand(String command) {
        for (CommandType c : CommandType.values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }

        throw new CommandException("Unknown Command: " + command);
    }
}
