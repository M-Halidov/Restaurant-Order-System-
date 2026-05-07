package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

public class DisplayOrdersCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;

    /**
     * Constructs DisplayOrdersCommand with the specified order repository.
     * @param orderRepo The repository used for storing orders.
     */
    public DisplayOrdersCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * Displays all orders, or only orders matching a specified status if one is provided.
     * @param args An optional single argument in the format status=<status> to filter orders by status.
     * @return A string representation of the matching orders or a message indicating that no orders were found.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If the argument format doesn't meet specification.
     */
    @Override
    public String execute(String[] args) {
        if (args.length > 1) {
            throw new CommandException("Invalid amount of arguments!\norders [status=<status>]");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("---Orders---");

        if (args.length == 0) {
            if (orderRepo.getItems().isEmpty()) {
                sb.setLength(0);
                sb.append("No orders were found!\n");
                return sb.toString();
            }

            for (Order order : orderRepo.getItems()) {
                sb.append("\n\n").append(order);
            }
        }
        else {
            String status = args[0].toLowerCase().trim();
            int length = sb.length();
            if (!status.startsWith("status=")) {
                throw new CommandException("Invalid argument!\norders [status=<status>]");
            }
            status = status.substring("status=".length());
            OrderStatus orderStatus = OrderStatus.getStatus(status);

            for (Order order : orderRepo.getItems()) {
                if (order.getStatus() == orderStatus) {
                    sb.append("\n\n").append(order);
                }
            }

            if (sb.length() == length) {
                sb.setLength(0);
                sb.append("No orders with status: ").append(status).append(" were found!\n");
                return sb.toString();
            }
        }

        sb.append("\n");
        return sb.toString();
    }
}
