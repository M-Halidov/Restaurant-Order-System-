package bg.tu_varna.sit.f24621696.commands.table_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

public class AddTableCommand implements Command {
    private TableRepo tableRepo;

    public AddTableCommand(TableRepo tableRepo) { this.tableRepo = tableRepo; }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new CommandException("Invalid amount of arguments!\naddtable <number> <seats>");
        }

        int ID;
        int seats;

        try {
            ID = Integer.parseInt(args[0]);
            seats = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new CommandException("Number and seat must a whole number!");
        }

        Table table = new Table(ID, seats);
        tableRepo.add(table);
    }
}
