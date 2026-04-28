package bg.tu_varna.sit.f24621696.commands.item_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

/**
 * Command used for displaying all menu items in the menu.
 */
public class DisplayMenuCommands implements Command {
    /**
     * Repository used for storing the menu items.
     */
    private MenuItemRepo menuItemRepo;

    /**
     * Constructs DisplayMenuCommands with the specified menu item repo.
     * @param menuItemRepo The repository used for storing menu items.
     */
    public DisplayMenuCommands(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    /**
     * Builds a string representation of all the items in the menu to be displayed.
     * @param args Not used.
     * @return A string listing all menu items, or a message if no items are found
     */
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
