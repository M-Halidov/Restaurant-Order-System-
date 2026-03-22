package bg.tu_varna.sit.f24621696.commands.table_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

public class DisplayTablesCommand implements Command {
    private TableRepo tableRepo;

    public DisplayTablesCommand(TableRepo tableRepo) {this.tableRepo = tableRepo;}

    @Override
    public String execute(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("---Tables---");
        for (Table table : tableRepo.getList()) {
            sb.append(table).append("\n");
        }

        return sb.toString();
    }
}
