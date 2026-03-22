package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.MenuItemException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.Order;
import java.util.List;

public class OrderRepo implements RepoInterface<Order> {
    private List<Order> orderList;

    @Override
    public void add(Order order) {
        if (orderList.contains(order)) {
            throw new MenuItemException("Repo already contains this item!");
        }
        orderList.add(order);
    }

    @Override
    public void remove(int ID) {
        // not needed
    }

    @Override
    public Order getInstance(int ID) {
        Order order = null;
        for (Order currOrder : orderList) {
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
        return orderList;
    }
}
