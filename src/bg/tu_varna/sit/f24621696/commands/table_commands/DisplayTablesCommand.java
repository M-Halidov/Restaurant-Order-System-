package bg.tu_varna.sit.f24621696.commands.table_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

/**
 * Command used for displaying all tables in the repository.
 */
public class DisplayTablesCommand implements Command {
    /**
     * Repository used for storing the tables.
     */
    private TableRepo tableRepo;

    /**
     * Constructs DisplayTablesCommand with the specified table repository.
     * @param tableRepo the repository used for storing tables.
     */
    public DisplayTablesCommand(TableRepo tableRepo) {
        this.tableRepo = tableRepo;
    }

    /**
     * Using StringBuilder it creates a comprehensive output detailing all the tables in the repository.
     * @param args Not used.
     * @return A string representation of all tables in the repository.
     */
    @Override
    public String execute(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("---Tables---");
        int length = sb.length();

        for (Table table : tableRepo.getItems()) {
            sb.append("\n").append(table);
        }

        if (sb.length() == length) {
            sb.setLength(0);
            sb.append("No tables were found! Please add some!\n");
            return sb.toString();
        }

        sb.append("\n");
        return sb.toString();
    }
}
