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

        sb.append("--- Item Threshold ---").append("\n");
        for (MenuItem item : menuItemRepo.getList()) {
            if (item.getQuantity() < threshold) {
                sb.append("item: ").append(item.getName());
                sb.append(", quantity:").append(item.getQuantity()).append("\n");
            }
        }

        return sb.toString();
    }
}
