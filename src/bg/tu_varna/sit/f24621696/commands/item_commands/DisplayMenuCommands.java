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
    public void execute(String[] args) {
        int i = 1;
        for (MenuItem item : menuItemRepo.getMenuItems()) {
            System.out.println("Menu Item " + i++);
            System.out.println(item + "\n");
        }
    }
}
