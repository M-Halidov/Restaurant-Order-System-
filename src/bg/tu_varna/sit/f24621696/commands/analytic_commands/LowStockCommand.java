package bg.tu_varna.sit.f24621696.commands.analytic_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

/**
 * Command used for displaying all items under a certain threshold.
 */
public class LowStockCommand implements Command {
    /**
     * Repository used for storing the menu items.
     */
    private MenuItemRepo menuItemRepo;

    /**
     * Constructs AddToOrderCommand with the specified menu item repo.
     * @param menuItemRepo The repository used for storing menu items.
     */
    public LowStockCommand(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    /**
     * Goes through all the menu items and displays only those with a threshold lower than specified.
     * @param args Arguments containing the threshold.
     * @return All items with a quantity under the given threshold.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If threshold is not a whole number.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nlowstock <threshold>");
        }

        StringBuilder sb = new StringBuilder();

        int threshold;
        try {
            threshold = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("Threshold must be a whole number!");
        }

        sb.append("--- Items below ").append(threshold).append(" quantity ---\n");
        int length = sb.length();
        for (MenuItem item : menuItemRepo.getItems()) {
            if (item.getQuantity() < threshold) {
                sb.append("item: ").append(item.getName());
                sb.append(", quantity:").append(item.getQuantity()).append("\n");
            }
        }

        if (sb.length() == length) {
            sb.setLength(0);
            sb.append("No such menu items were found!\n");
            return sb.toString();
        }

        sb.append("\n");
        return sb.toString();
    }
}
