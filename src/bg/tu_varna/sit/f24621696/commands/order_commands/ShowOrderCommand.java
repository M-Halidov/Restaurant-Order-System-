package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.util.Map;

/**
 * Command which displays a user given order.
 */
public class ShowOrderCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;

    /**
     * Constructs AddToOrderCommand with the specified arguments.
     * @param orderRepo The repository used for storing orders.
     */
    public ShowOrderCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * Displays the specified order.
     * @param args The arguments containing the order ID.
     * @return A string representation of the order and its contents.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If the order ID is not a whole number.
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nshoworder <orderID>");
        }

        int orderID;
        try {
            orderID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("orderID must be a whole number!");
        }

        Order order = orderRepo.searchForID(orderID);

        StringBuilder sb = new StringBuilder();
        sb.append("---Order---").append("\n\n").append(order).append("\n");

        return sb.toString();
    }
}
