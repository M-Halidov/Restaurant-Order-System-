package bg.tu_varna.sit.f24621696.commands;

import bg.tu_varna.sit.f24621696.commands.general_commands.ExitCommand;
import bg.tu_varna.sit.f24621696.commands.general_commands.HelpCommand;
import bg.tu_varna.sit.f24621696.commands.item_commands.AddItemCommand;
import bg.tu_varna.sit.f24621696.commands.order_commands.*;
import bg.tu_varna.sit.f24621696.commands.table_commands.AddTableCommand;
import bg.tu_varna.sit.f24621696.commands.item_commands.DisplayMenuCommands;
import bg.tu_varna.sit.f24621696.commands.item_commands.RemoveItemCommand;
import bg.tu_varna.sit.f24621696.commands.table_commands.DisplayTablesCommand;
import bg.tu_varna.sit.f24621696.commands.table_commands.RemoveTableCommand;
import bg.tu_varna.sit.f24621696.enums.CommandType;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<CommandType, Command> commands = new HashMap<>();
    private MenuItemRepo menuItemRepo = new MenuItemRepo();
    private TableRepo tableRepo = new TableRepo();
    private OrderRepo orderRepo = new OrderRepo();

    public CommandHandler() {
        commands.put(CommandType.HELP, new HelpCommand());
        commands.put(CommandType.EXIT, new ExitCommand());
        commands.put(CommandType.ADDITEM, new AddItemCommand(menuItemRepo));
        commands.put(CommandType.REMOVEITEM, new RemoveItemCommand(menuItemRepo));
        commands.put(CommandType.MENU, new DisplayMenuCommands(menuItemRepo));
        commands.put(CommandType.ADDTABLE, new AddTableCommand(tableRepo));
        commands.put(CommandType.REMOVETABLE, new RemoveTableCommand(tableRepo));
        commands.put(CommandType.TABLES, new DisplayTablesCommand(tableRepo));
        commands.put(CommandType.OPENORDER, new OpenOrderCommand(orderRepo, tableRepo));
        commands.put(CommandType.ADDTOORDER, new AddToOrderCommand(orderRepo, menuItemRepo));
        commands.put(CommandType.REMOVEFROMORDER, new RemoveFromOrderCommand(orderRepo, menuItemRepo));
        commands.put(CommandType.SHOWORDER, new ShowOrderCommand(orderRepo));
        commands.put(CommandType.CLOSEORDER, new CloseOrderCommand(orderRepo, tableRepo));
        commands.put(CommandType.CANCELORDER, new CancelOrderCommand(orderRepo, tableRepo));
        commands.put(CommandType.ORDERS, new DisplayOrdersCommand(orderRepo));


        //...
    }

    public String processInput(String input) {
        String[] parts = input.trim().split(" ");
        String tempCmd = parts[0].toLowerCase();
        CommandType cmd = CommandType.getCommand(tempCmd);
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command command = commands.get(cmd);
        if (command != null) {
            return command.execute(args);
        }
        else throw new CommandException("Unknown Command. Type 'help' for a list.");
    }
}
