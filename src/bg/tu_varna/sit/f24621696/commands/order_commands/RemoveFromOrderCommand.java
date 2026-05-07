package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

/**
 * Command which adds a menu item from the order.
 */
public class RemoveFromOrderCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;
    /**
     * Repository used for storing menu items.
     */
    private MenuItemRepo menuItemRepo;

    /**
     * Constructs AddToOrderCommand with the specified arguments.
     * @param orderRepo The repository used for storing orders.
     * @param menuItemRepo The repository used for storing menu items.
     */
    public RemoveFromOrderCommand(OrderRepo orderRepo, MenuItemRepo menuItemRepo) {
        this.orderRepo = orderRepo;
        this.menuItemRepo = menuItemRepo;
    }

    /**
     * Removes an item from the specified order.
     * @param args The arguments containing the order ID and item ID.
     * @return A success message confirming the removal of a menu item from the specified order.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If the order ID or item ID are not whole numbers.
     */
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
        MenuItem menuItem = menuItemRepo.searchForID(itemID);

        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.PAID_FOR) {
            throw new CommandException("Order already concluded please open a new one!");
        }

        order.getItems().remove(menuItem);
        return "Successfully removed item from order!\n";
    }
}
