package bg.tu_varna.sit.f24621696.commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<String, Command> commands = new HashMap<>();

    public CommandHandler() {
        commands.put("additem", new AddItemCommand());
        //...
    }

    public void processInput(String input) {
        String[] parts = input.trim().split(" ", 2);
        String cmd = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command command = commands.get(cmd);
        if (command != null) {
            command.execute(args);
        }
        else throw new CommandException("Unknown Command. Type 'help' for a list.");
    }
}
