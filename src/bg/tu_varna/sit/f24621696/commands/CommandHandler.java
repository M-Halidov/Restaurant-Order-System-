package bg.tu_varna.sit.f24621696.commands;

import bg.tu_varna.sit.f24621696.commands.analytic_commands.LowStockCommand;
import bg.tu_varna.sit.f24621696.commands.analytic_commands.ProfitReportCommand;
import bg.tu_varna.sit.f24621696.commands.analytic_commands.TopItemsCommand;
import bg.tu_varna.sit.f24621696.commands.file_commands.CloseFileCommand;
import bg.tu_varna.sit.f24621696.commands.file_commands.OpenFileCommand;
import bg.tu_varna.sit.f24621696.commands.file_commands.SaveAsCommand;
import bg.tu_varna.sit.f24621696.commands.file_commands.SaveCommand;
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
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Processes inputs given by the user and executing the appropriate command.
 */
public class CommandHandler {
    /**
     * The repository manager providing access to all repositories.
     */
    private RepoManager repoManager;

    /**
     * Maps each command type to its corresponding command implementation.
     */
    private Map<CommandType, Command> commands = new HashMap<>();

    /**
     * Constructs a CommandHandler and initializes all available commands.
     * @param repoManager the repository manager to be used by the commands.
     */
    public CommandHandler(RepoManager repoManager) {
        this.repoManager = repoManager;
        commands.put(CommandType.OPEN, new OpenFileCommand(repoManager));
        commands.put(CommandType.CLOSE, new CloseFileCommand(repoManager));
        commands.put(CommandType.SAVE, new SaveCommand(repoManager));
        commands.put(CommandType.SAVEAS, new SaveAsCommand(repoManager));
        commands.put(CommandType.HELP, new HelpCommand());
        commands.put(CommandType.EXIT, new ExitCommand());
        commands.put(CommandType.ADDITEM, new AddItemCommand(repoManager.getMenuItemRepo()));
        commands.put(CommandType.REMOVEITEM, new RemoveItemCommand(repoManager.getMenuItemRepo()));
        commands.put(CommandType.MENU, new DisplayMenuCommands(repoManager.getMenuItemRepo()));
        commands.put(CommandType.ADDTABLE, new AddTableCommand(repoManager.getTableRepo()));
        commands.put(CommandType.REMOVETABLE, new RemoveTableCommand(repoManager.getTableRepo()));
        commands.put(CommandType.TABLES, new DisplayTablesCommand(repoManager.getTableRepo()));
        commands.put(CommandType.OPENORDER, new OpenOrderCommand(repoManager.getOrderRepo(), repoManager.getTableRepo()));
        commands.put(CommandType.ADDTOORDER, new AddToOrderCommand(repoManager.getOrderRepo(), repoManager.getMenuItemRepo()));
        commands.put(CommandType.REMOVEFROMORDER, new RemoveFromOrderCommand(repoManager.getOrderRepo(), repoManager.getMenuItemRepo()));
        commands.put(CommandType.SHOWORDER, new ShowOrderCommand(repoManager.getOrderRepo()));
        commands.put(CommandType.CLOSEORDER, new CloseOrderCommand(repoManager.getOrderRepo()));
        commands.put(CommandType.CANCELORDER, new CancelOrderCommand(repoManager.getOrderRepo()));
        commands.put(CommandType.ORDERS, new DisplayOrdersCommand(repoManager.getOrderRepo()));
        commands.put(CommandType.REPORT, new ProfitReportCommand(repoManager.getOrderRepo()));
        commands.put(CommandType.TOPITEMS, new TopItemsCommand(repoManager.getOrderRepo()));
        commands.put(CommandType.LOWSTOCK, new LowStockCommand(repoManager.getMenuItemRepo()));
    }

    /**
     * Process the user inputted string value by splitting it into an array.
     * Uses the first index to access the command type.
     * Copies the rest to a new string array which is passed into corresponding command.
     * Returns an error message if no file is open and the command is not OPEN.
     * @param input User given string value containing the command and arguments.
     * @return The output of the command or an error message if no file is open.
     */
    public String processInput(String input) {
        String[] parts = input.trim().split(" ");
        CommandType cmd = CommandType.getCommand(parts[0].toLowerCase());
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command command = commands.get(cmd);

        if (cmd !=  CommandType.OPEN && !repoManager.isFileOpen()) {
            return "Please open a file!\n";
        }

        return command.execute(args);
    }
}
