package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.enums.TableStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.util.Map;

/**
 * Command used for cancelling a given order.
 */
public class CancelOrderCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;

    /**
     * Constructs AddToOrderCommand with the specified order repository.
     * @param orderRepo The repository used for storing orders.
     */
    public CancelOrderCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * Parses the orderID and returns all items belonging to the order, marks the table as available and cancels the order.
     * @param args The arguments containing the orderID.
     * @return A success message informing the cancellation of the order.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If the orderID is not a whole number.
     * @throws CommandException If the order is either cancelled or paid for.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\ncancelorder <orderID>");
        }

        int orderID;
        try {
            orderID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("Order ID must be a whole number!");
        }

        Order order = orderRepo.searchForID(orderID);

        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.PAID_FOR) {
            throw new CommandException("Order already concluded please open a new one!");
        }

        // Returning added items when the order is cancelled
        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();

            item.setQuantity(item.getQuantity() + quantity);

        }

        order.getTable().setStatus(TableStatus.AVAILABLE);
        order.setStatus(OrderStatus.CANCELLED);
        order.setFinalSum(0);

        return "Order was successfully cancelled!\n";
    }
}
