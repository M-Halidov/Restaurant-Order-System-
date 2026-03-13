package bg.tu_varna.sit.f24621696.commands;

import bg.tu_varna.sit.f24621696.enums.ItemCategory;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

public class AddItemCommand implements Command {
    private MenuItemRepo menuItemRepo;

    public AddItemCommand(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 4) {
            throw new CommandException("Invalid amount of arguments!\nadditem <name> <category> <price> <quantity>");
        }

        String name = args[0];
        String category = args[1].toLowerCase();
        ItemCategory itemCategory = ItemCategory.OTHER;
        itemCategory = switch (category) {
            case "appetizer" -> ItemCategory.APPETIZER;
            case "main" -> ItemCategory.MAIN;
            case "desert" -> ItemCategory.DESERT;
            case "drink" -> ItemCategory.DRINK;
            default -> ItemCategory.OTHER;
        };

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
    }
}
