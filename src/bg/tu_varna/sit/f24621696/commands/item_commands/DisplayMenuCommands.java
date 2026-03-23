package bg.tu_varna.sit.f24621696.commands.item_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

public class DisplayMenuCommands implements Command {
    private MenuItemRepo menuItemRepo;

    public DisplayMenuCommands(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public String execute(String[] args) {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("---Menu---");
        for (MenuItem item : menuItemRepo.getList()) {
            sb.append("\nMenu Item " + i++ + ": ");
            sb.append(item);
        }

        return sb.toString();
    }
}
