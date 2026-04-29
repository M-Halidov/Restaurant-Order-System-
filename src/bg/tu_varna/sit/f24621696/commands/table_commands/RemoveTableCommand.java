package bg.tu_varna.sit.f24621696.commands.table_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

/**
 * Command used for removing tables from the repository.
 */
public class RemoveTableCommand implements Command {
    /**
     * Repository used for storing the tables.
     */
    private TableRepo tableRepo;

    /**
     * Constructs an RemoveTableCommand with the specified table repository.
     * @param tableRepo the repository used for storing tables.
     */
    public RemoveTableCommand(TableRepo tableRepo) {this.tableRepo = tableRepo;}

    /**
     * Parses the argument which is then sent to the repository to remove the table matching the ID.
     * @param args The argument ID.
     * @return A success message notifying of the removal of the specified table.
     * @throws CommandException If the number of arguments is invalid or the ID is not a whole number.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nremovetable <number>");
        }

        int ID;
        try {
            ID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("ID must be a whole number!");
        }

        tableRepo.remove(ID);
        return "Table removed successfully!\n";
    }
}
