package bg.tu_varna.sit.f24621696.commands.item_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

/**
 * Command used for removing a menu item from the menu.
 */
public class RemoveItemCommand implements Command {
    /**
     * Repository used for storing the menu items.
     */
    private MenuItemRepo menuItemRepo;

    /**
     * Constructs RemoveItemCommand with the specified menu item repo.
     * @param menuItemRepo The repository used for storing menu items.
     */
    public RemoveItemCommand(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    /**
     * Removes the item with the ID given by the user.
     * @param args Argument containing the ID.
     * @return A success message confirming removal of the item.
     * @throws CommandException If the number of arguments is invalid or if the ID is not a whole number.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nremoveitem <itemID>");
        }

        int ID;
        try {
            ID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("ID must be a whole number!");
        }
        menuItemRepo.remove(ID);
        return "Item removed successfully!\n";
    }
}
