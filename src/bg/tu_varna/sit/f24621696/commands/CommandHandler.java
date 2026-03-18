package bg.tu_varna.sit.f24621696.commands;

import bg.tu_varna.sit.f24621696.commands.item_commands.AddItemCommand;
import bg.tu_varna.sit.f24621696.commands.table_commands.AddTableCommand;
import bg.tu_varna.sit.f24621696.commands.item_commands.DisplayMenuCommands;
import bg.tu_varna.sit.f24621696.commands.item_commands.RemoveItemCommand;
import bg.tu_varna.sit.f24621696.commands.table_commands.DisplayTablesCommand;
import bg.tu_varna.sit.f24621696.commands.table_commands.RemoveTableCommand;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<String, Command> commands = new HashMap<>();
    private MenuItemRepo menuItemRepo;
    private TableRepo tableRepo;

    public CommandHandler() {
        commands.put("additem", new AddItemCommand(menuItemRepo));
        commands.put("removeitem", new RemoveItemCommand(menuItemRepo));
        commands.put("menu", new DisplayMenuCommands(menuItemRepo));
        commands.put("addtable", new AddTableCommand(tableRepo));
        commands.put("removetable", new RemoveTableCommand(tableRepo));
        commands.put("tables", new DisplayTablesCommand(tableRepo));

        //...
    }

    public void processInput(String input) {
        String[] parts = input.trim().split(" ");
        String cmd = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command command = commands.get(cmd);
        if (command != null) {
            command.execute(args);
        }
        else throw new CommandException("Unknown Command. Type 'help' for a list.");
    }
}
