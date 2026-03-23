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
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<String, Command> commands = new HashMap<>();
    private MenuItemRepo menuItemRepo = new MenuItemRepo();
    private TableRepo tableRepo = new TableRepo();
    private OrderRepo orderRepo = new OrderRepo();

    public CommandHandler() {
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("additem", new AddItemCommand(menuItemRepo));
        commands.put("removeitem", new RemoveItemCommand(menuItemRepo));
        commands.put("menu", new DisplayMenuCommands(menuItemRepo));
        commands.put("addtable", new AddTableCommand(tableRepo));
        commands.put("removetable", new RemoveTableCommand(tableRepo));
        commands.put("tables", new DisplayTablesCommand(tableRepo));
        commands.put("openorder", new OpenOrderCommand(orderRepo, tableRepo));
        commands.put("addtoorder", new AddToOrderCommand(orderRepo, menuItemRepo));
        commands.put("removefromorder", new RemoveFromOrderCommand(orderRepo, menuItemRepo));
        commands.put("showorder", new ShowOrderCommand(orderRepo));
        commands.put("closeorder", new CloseOrderCommand(orderRepo, tableRepo));
        commands.put("cancelorder", new CancelOrderCommand(orderRepo, tableRepo));
        commands.put("orders", new DisplayOrdersCommand(orderRepo));


        //...
    }

    public String processInput(String input) {
        String[] parts = input.trim().split(" ");
        String cmd = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        String result = "";

        Command command = commands.get(cmd);
        if (command != null) {
            result = command.execute(args);
        }
        else throw new CommandException("Unknown Command. Type 'help' for a list.");
        return result;
    }
}
