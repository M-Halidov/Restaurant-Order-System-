package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

public class RemoveFromOrderCommand implements Command {
    private OrderRepo orderRepo;
    private MenuItemRepo menuItemRepo;

    public RemoveFromOrderCommand(OrderRepo orderRepo, MenuItemRepo menuItemRepo) {
        this.orderRepo = orderRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            throw new CommandException("Invalid amount of arguments!\nremovefromorder <orderID> <itemId>");
        }

        int orderID;
        int itemID;

        try {
            orderID = Integer.parseInt(args[0]);
            itemID = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new CommandException("The arguments must be whole numbers!");
        }

        Order order = orderRepo.searchForID(orderID);

        order.getOrderList().remove(itemID);
        return "Successfully removed item from order!";
    }
}
