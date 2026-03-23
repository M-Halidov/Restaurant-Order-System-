package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.TableStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

public class OpenOrderCommand implements Command {
    private OrderRepo orderRepo;
    private TableRepo tableRepo;

    public OpenOrderCommand(OrderRepo orderRepo, TableRepo tableRepo) {
        this.orderRepo = orderRepo;
        this.tableRepo = tableRepo;
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
        Table table = tableRepo.getInstance(tableNumber);

        if (table.getStatus() == TableStatus.OCCUPIED) {
            throw new CommandException("Table is already occupied!");
        }

        table.setStatus(TableStatus.OCCUPIED);
        orderRepo.add(order);
        return "Successfully opened order!";
    }
}
