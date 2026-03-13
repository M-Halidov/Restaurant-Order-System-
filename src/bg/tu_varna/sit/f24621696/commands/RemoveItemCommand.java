package bg.tu_varna.sit.f24621696.commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;

public class RemoveItemCommand implements Command {
    private MenuItemRepo menuItemRepo;

    public RemoveItemCommand(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public void execute(String[] args) {
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
    }
}
