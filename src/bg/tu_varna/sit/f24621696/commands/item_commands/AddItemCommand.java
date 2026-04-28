package bg.tu_varna.sit.f24621696.commands.item_commands;

import bg.tu_varna.sit.f24621696.enums.ItemCategory;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

/**
 * Command used for adding a new menu item to the menu.
 */
public class AddItemCommand implements Command {
    /**
     * Repository used for storing the menu items.
     */
    private MenuItemRepo menuItemRepo;

    /**
     * Constructs AddItemCommand with the specified menu item repo.
     * @param menuItemRepo The repository used for storing menu items.
     */
    public AddItemCommand(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    /**
     * Parses the arguments from the given string array and assigns them to their corresponding attributes in a menu item.
     * @param args The arguments containing name, category, price, and quantity.
     * @return A success message with the details of the created item.
     * @throws CommandException If the number of arguments is invalid or the price and quantity are not valid numbers.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 4) {
            throw new CommandException("Invalid amount of arguments!\nadditem <name> <category> <price> <quantity>");
        }

        String name = args[0];
        String category = args[1].toLowerCase();
        ItemCategory itemCategory = ItemCategory.getCategory(category);

        double price;
        int quantity;
        try {
            price = Double.parseDouble(args[2]);
            quantity = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            throw new CommandException("Price must be a decimal number and quantity should be a whole number!");
        }

        MenuItem item = new MenuItem(name, itemCategory, price, quantity);
        menuItemRepo.add(item);
        return "Successfully created item: " + item + "\n";
    }
}
