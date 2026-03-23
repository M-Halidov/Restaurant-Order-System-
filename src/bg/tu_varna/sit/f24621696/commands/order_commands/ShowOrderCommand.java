package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.util.Map;

public class ShowOrderCommand implements Command {
    private OrderRepo orderRepo;

    public ShowOrderCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

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
        sb.append("---Order---").append("\nOrder Status: " + order.getStatus());

        for (Map.Entry<Integer, MenuItem> item : order.getOrderList().entrySet()) {
            sb.append("\n" + item);
        }

        return sb.toString();
    }
}
