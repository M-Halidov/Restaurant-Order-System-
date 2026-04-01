package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

public class DisplayOrdersCommand implements Command {
    private OrderRepo orderRepo;

    public DisplayOrdersCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 1) {
            throw new CommandException("Invalid amount of arguments!\norders [status=<status>]");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("---Orders---");

        if (args.length == 0) {
            for (Order order : orderRepo.getList()) {
                sb.append("\n").append(order);
            }
        }
        else {
            String status = args[0].toLowerCase().trim();
            if (!status.startsWith("status=")) {
                throw new CommandException("Invalid argument!\norders [status=<status>]");
            }
            status = status.substring("status=".length());
            OrderStatus orderStatus = OrderStatus.getStatus(status);

            for (Order order : orderRepo.getList()) {
                if (order.getStatus() == orderStatus) {
                    sb.append("\n").append(order);
                }
            }
        }

        return sb.toString();
    }
}
