package bg.tu_varna.sit.f24621696.commands.table_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

/**
 * Command used for adding a new table to the repository.
 */
public class AddTableCommand implements Command {
    /**
     * Repository used for storing the tables.
     */
    private TableRepo tableRepo;

    /**
     * Constructs AddTableCommand with the specified table repository.
     * @param tableRepo the repository used for storing tables.
     */
    public AddTableCommand(TableRepo tableRepo) {
        this.tableRepo = tableRepo;
    }

    /**
     * Parses the arguments from the given string array and assigns the corresponding values to a new table object.
     * The table object gets stored in the TableRepo.
     * @param args The arguments containing ID and seats.
     * @return A success message with the details of the created table.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If ID and seats are not whole numbers.
     */
    @Override
    public String execute(String[] args) {
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
        return "Successfully added table: " + table + "!\n";
    }
}
