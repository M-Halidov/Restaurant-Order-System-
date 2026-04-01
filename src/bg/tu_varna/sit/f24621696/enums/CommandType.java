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


    public static CommandType fromInput(String input) {
        for (CommandType c : CommandType.values()) {
            if (c.command.equals(input)) {
                return c;
            }
        }

        throw new CommandException("Unknown Command: " + input);
    }
}
