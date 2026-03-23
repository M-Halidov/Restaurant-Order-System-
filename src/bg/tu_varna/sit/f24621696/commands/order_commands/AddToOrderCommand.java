package bg.tu_varna.sit.f24621696.commands.order_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.MenuItemRepo;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.awt.*;

public class AddToOrderCommand implements Command {
    private OrderRepo orderRepo;
    private MenuItemRepo menuItemRepo;

    public AddToOrderCommand(OrderRepo orderRepo, MenuItemRepo menuItemRepo) {
        this.orderRepo = orderRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 3) {
            throw new CommandException("Invalid amount of arguments!\naddtoorder <orderID> <itemId> <quantity>");
        }

        int orderID;
        int itemID;
        int quantity;

        try {
            orderID = Integer.parseInt(args[0]);
            itemID = Integer.parseInt(args[1]);
            quantity = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            throw new CommandException("The arguments must be whole numbers!");
        }

        Order order = orderRepo.searchForID(orderID);
        MenuItem item = menuItemRepo.searchForID(itemID);


        order.getOrderList().putIfAbsent(item.getID(), item);
        item.setQuantity(item.getQuantity() + quantity);

        return "Successfully added item: " + item + " to order!";

    }
}
