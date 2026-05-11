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

/**
 * The repository class for orders.
 */
public class OrderRepo implements RepoInterface<Order>, Serializable {
    /**
     * List of all orders in the repository.
     */
    private List<Order> orders = new ArrayList<>();

    /**
     * Adds the passed order into to the list
     * @param order The order to be added to the repository.
     * @throws RepoException If the repository already contains the order
     */
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

    /**
     * Searches for the corresponding order with the given ID.
     * @param ID the unique identifier of the order.
     * @return The order with the matching the given ID.
     * @throws RepoException If the order with the given ID was not found.
     */
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

    /**
     * Searches through the all the orders excluding cancelled or open ones compiling a HashMap of unique items and amount sold
     * for a given period of time
     * @param from The starting date and time.
     * @param to The ending date and time.
     * @return A HashMap of all unique items as the key and amount sold as the value for a given period of time.
     */
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

    /**
     * Counts all orders in a given time period excluding cancelled or on open orders.
     * @param from The starting date and time.
     * @param to The ending date and time
     * @return The amount of orders in the given period.
     */
    public int countOrdersByDateRange(LocalDateTime from, LocalDateTime to) {
        int count = 0;

        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.OPEN) continue;
            else if (!order.getDateAndTime().isBefore(from) && !order.getDateAndTime().isAfter(to)) count++;
        }

        return count;
    }

    /**
     * Returns all orders currently stored in the repository.
     * @return All stored orders.
     */
    @Override
    public List<Order> getItems() {
        return orders;
    }

    /**
     * Replaces all stored orders with a given new list.
     * @param items The list of orders to replace the current repository items.
     */
    @Override
    public void setItems(List<Order> items) {
        this.orders = items;
    }

    /**
     * Clears all orders in the repository.
     */
    @Override
    public void clearItems() {
        orders.clear();
    }
}
