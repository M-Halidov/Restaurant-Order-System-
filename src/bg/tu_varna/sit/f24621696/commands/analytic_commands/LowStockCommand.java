package bg.tu_varna.sit.f24621696.commands.analytic_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

public class LowStockCommand implements Command {
    private MenuItemRepo menuItemRepo;

    public LowStockCommand(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nlowstock <thershold>");
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
            sb.append("No such items exist!\n");
            return sb.toString();
        }

        sb.append("\n");
        return sb.toString();
    }
}
