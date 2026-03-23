package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.enums.TableStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

public class CloseOrderCommand implements Command {
    private OrderRepo orderRepo;
    private TableRepo tableRepo;

    public CloseOrderCommand(OrderRepo orderRepo, TableRepo tableRepo) {
        this.orderRepo = orderRepo;
        this.tableRepo = tableRepo;
    }

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
        Table table = tableRepo.searchForID(order.getIdOfTable());
        table.setStatus(TableStatus.AVAILABLE);
        order.setStatus(OrderStatus.PAID_FOR);

        return "Order was successfully paid for!";
    }
}
