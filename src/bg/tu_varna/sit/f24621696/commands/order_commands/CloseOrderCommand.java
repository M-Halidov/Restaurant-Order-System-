package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.enums.TableStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.util.Map;

/**
 * Command used for concluding the given order.
 */
public class CloseOrderCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;

    /**
     * Constructs CloseOrderCommand with the specified order repository.
     * @param orderRepo The repository used for storing orders.
     */
    public CloseOrderCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * Concludes the respective order, marks its table as available and concludes the order.
     * @param args The arguments containing the orderID.
     * @return A success message detailing the payment of the order.
     * @throws CommandException If the number of the arguments is invalid.
     * @throws CommandException If the orderID is not a whole number.
     * @throws CommandException If the order is either cancelled or paid for.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\ncloseorder <orderID>");
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

        order.getTable().setStatus(TableStatus.AVAILABLE);
        order.setStatus(OrderStatus.PAID_FOR);

        return "Order was successfully paid for!\n";
    }
}
