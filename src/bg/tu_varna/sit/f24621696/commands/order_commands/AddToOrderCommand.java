package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.awt.*;

/**
 * Command which adds a menu item to an order.
 */
public class AddToOrderCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;
    /**
     * Repository used for storing the menu items.
     */
    private MenuItemRepo menuItemRepo;

    /**
     * Constructs AddToOrderCommand with the specified arguments.
     * @param orderRepo The repository used for storing orders.
     * @param menuItemRepo The repository used for storing menu items.
     */
    public AddToOrderCommand(OrderRepo orderRepo, MenuItemRepo menuItemRepo) {
        this.orderRepo = orderRepo;
        this.menuItemRepo = menuItemRepo;
    }

    /**
     * Parses the arguments from the input and adds the respective item and its quantity to the order.
     * Updates the available item quantity and the order's final sum.
     * @param args The arguments containing orderID, itemID, quantity
     * @return A success message detailing the addition of the item and its quantity to the order.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If the orderID, itemID or quantity aren't whole numbers.
     * @throws CommandException If the order is either cancelled or paid for.
     * @throws CommandException If the requested quantity exceeds the available stock.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 3) {
            throw new CommandException("Invalid amount of arguments!\naddtoorder <orderID> <itemId> <quantity>");
        }

        int orderID;
        int itemID;
        int quantity;

        try {
            orderID = Integer.parseInt(args[0]);
            itemID = Integer.parseInt(args[1]);
            quantity = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            throw new CommandException("The arguments must be whole numbers!");
        }

        Order order = orderRepo.searchForID(orderID);
        MenuItem item = menuItemRepo.searchForID(itemID);

        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.PAID_FOR) {
            throw new CommandException("Order already concluded please open a new one!");
        }

        if (quantity > item.getQuantity()) {
            throw new CommandException("Given quantity exceeds available stock for " + item.getName() + "! Available: " + item.getQuantity());
        }
        item.setQuantity(item.getQuantity() - quantity);
        order.setFinalSum(order.getFinalSum() + item.getPrice() * quantity);
        order.getItems().put(item, order.getItems().getOrDefault(item, 0) + quantity);

        return "Successfully added " + item.getName() + " to order! Amount: " + quantity + ".\n";
    }
}
