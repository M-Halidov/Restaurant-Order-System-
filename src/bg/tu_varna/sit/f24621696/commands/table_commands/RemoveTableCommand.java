package bg.tu_varna.sit.f24621696.commands.table_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

public class RemoveTableCommand implements Command {
    private TableRepo tableRepo;

    public RemoveTableCommand(TableRepo tableRepo) {this.tableRepo = tableRepo;}

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nremovetable <number>");
        }

        int ID;
        try {
            ID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        tableRepo.remove(ID);
    }
}
