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
        StringBuilder sb = new StringBuilder();
        sb.append("---Menu---");
        int length = sb.length();

        int i = 1;
        for (MenuItem item : menuItemRepo.getItems()) {
            sb.append("\nMenu Item ").append(i++).append(": ");
            sb.append(item);
        }

        if (sb.length() == length) {
            sb.setLength(0);
            sb.append("No menu items were found! Please add some!\n");
            return sb.toString();
        }

        sb.append("\n");

        return sb.toString();
    }
}
