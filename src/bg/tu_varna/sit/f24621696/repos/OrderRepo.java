package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.MenuItemException;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import java.util.List;

public class OrderRepo {
    private List<Order> orderList;

    public void add(Order order) {
        if (orderList.contains(order)) {
            throw new MenuItemException("Repo already contains this item!");
        }
        orderList.add(order);
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
