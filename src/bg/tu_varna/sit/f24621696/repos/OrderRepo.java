package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.MenuItemException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepo implements RepoInterface<Order> {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order order) {
        if (orders.contains(order)) {
            throw new MenuItemException("Repo already contains this item!");
        }
        orders.add(order);
    }

    @Override
    public void remove(int ID) {
        // not needed
    }

    @Override
    public Order searchForID(int ID) {
        Order order = null;
        for (Order currOrder : orders) {
            if (currOrder.getID() == ID) {
                order = currOrder;
                break;
            }
        }
        if (order == null) {
            throw new CommandException("Order with the ID: " + ID + ", was not found!");
        }

        return order;
    }

    @Override
    public List<Order> getList() {
        return orders;
    }
}
