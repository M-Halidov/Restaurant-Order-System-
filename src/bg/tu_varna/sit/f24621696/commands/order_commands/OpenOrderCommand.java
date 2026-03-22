package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

public class OpenOrderCommand implements Command {
    private OrderRepo orderRepo;

    public OpenOrderCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nopenorder <tableNumber>");
        }

        int tableNumber;
        try {
            tableNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("tableNumber must be a whole number!");
        }

        Order order = new Order(tableNumber);
        orderRepo.add(order);
        return "Successfully opened order!";
    }
}
