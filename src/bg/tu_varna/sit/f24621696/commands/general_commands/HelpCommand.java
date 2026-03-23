package bg.tu_varna.sit.f24621696.commands.general_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;

public class HelpCommand implements Command {
    @Override
    public String execute(String[] args) {
        return """
                ---All Commands---
                open <file>                                    -> Opens a file with saved data.
                close                                          -> Closes the current file.
                save                                           -> Saves changes to the current file.
                saveas <file>                                  -> Saves the data to a new file.
                help                                           -> Displays all commands.
                exit                                           -> Exits the program.
                additem <name> <category> <price> <quantity>   -> Adds a new item to the menu.
                removeitem <itemId>                            -> Removes an item from the menu.
                menu                                           -> Displays the entire menu.
                addtable <number> <seats>                      -> Adds a new table.
                removetable <number>                           -> Removes a table.
                tables                                         -> Shows a list of tables and their status.
                openorder <tableNumber>                        -> Opens a new order for a table.
                addtoorder <orderId> <itemId> <quantity>       -> Adds an item to an order.
                removefromorder <orderId> <itemId>             -> Removes an item from an order.
                showorder <orderId>                            -> Shows order details and current total.
                closeorder <orderId>                           -> Closes the order and marks it as paid.
                cancelorder <orderId>                          -> Cancels an order.
                orders [status=<status>]                       -> Shows all orders or filters by status.
                report <from> <to>                             -> Revenue report for a given period.
                topitems <n> <from> <to>                       -> Top selling items for the period.
                lowstock <threshold>                           -> Shows items with stock below the given value.
                """;
    }
}
