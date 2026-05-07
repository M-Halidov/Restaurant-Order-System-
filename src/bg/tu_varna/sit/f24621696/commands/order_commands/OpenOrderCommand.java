package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.enums.TableStatus;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.models.Table;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;
import bg.tu_varna.sit.f24621696.repos.TableRepo;

/**
 * Command that opens a new order
 */
public class OpenOrderCommand implements Command {
    /**
     * Repository used for storing the orders.
     */
    private OrderRepo orderRepo;
    /**
     * Repository used for storing the tables.
     */
    private TableRepo tableRepo;

    /**
     * Constructs AddToOrderCommand with the specified arguments.
     * @param orderRepo The repository used for storing orders.
     * @param tableRepo The repository used for storing tables.
     */
    public OpenOrderCommand(OrderRepo orderRepo, TableRepo tableRepo) {
        this.orderRepo = orderRepo;
        this.tableRepo = tableRepo;
    }

    /**
     * Opens a new order and marks the respective table as occupied.
     * @param args The arguments containing the table number.
     * @return A success message confirming the order was successfully opened.
     * @throws CommandException If the number of arguments is invalid.
     * @throws CommandException If the tableNumber is not a whole number.
     * @throws CommandException If the table is already occupied.
     */
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

        Table table = tableRepo.searchForID(tableNumber);
        if (table.getStatus() == TableStatus.OCCUPIED) {
            throw new CommandException("Table is already occupied!");
        }

        Order order = new Order(table);

        table.setStatus(TableStatus.OCCUPIED);
        orderRepo.add(order);
        return "Successfully opened order!\n";
    }
}
