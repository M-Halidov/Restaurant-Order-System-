package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.enums.OrderStatus;
import bg.tu_varna.sit.f24621696.exceptions.RepoException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepo implements RepoInterface<Order>, Serializable {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order order) {
        if (orders.contains(order)) {
            throw new RepoException("Repo already contains: " + order);
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
            throw new RepoException("Order with the ID: " + ID + ", was not found!");
        }

        return order;
    }

    public Map<MenuItem, Integer> getOrdersByDateRange(LocalDateTime from, LocalDateTime to) {
        HashMap<MenuItem, Integer> orderRange = new HashMap<>();
        for (Order order : orders) {

            if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.OPEN) continue;

            if (!order.getDateAndTime().isBefore(from) && !order.getDateAndTime().isAfter(to)) {
                for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
                    MenuItem item = entry.getKey();
                    int quantity = entry.getValue();
                    orderRange.put(item, orderRange.getOrDefault(item, 0) + quantity);
                }
            }
        }

        return orderRange;
    }

    public int countOrdersByDateRange(LocalDateTime from, LocalDateTime to) {
        int count = 0;

        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.OPEN) continue;
            else if (!order.getDateAndTime().isBefore(from) && !order.getDateAndTime().isAfter(to)) count++;
        }

        return count;
    }

    @Override
    public List<Order> getItems() {
        return orders;
    }

    @Override
    public void setItems(List<Order> items) {
        this.orders = items;
    }
}
