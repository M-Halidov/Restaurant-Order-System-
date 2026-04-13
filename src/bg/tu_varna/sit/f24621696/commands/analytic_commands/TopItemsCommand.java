package bg.tu_varna.sit.f24621696.commands.analytic_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopItemsCommand implements Command {
    private OrderRepo orderRepo;

    public TopItemsCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 5) {
            throw new CommandException("Invalid amount of arguments!\ntopitems <n> <from> <to>");
        }

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = Order.formatter;

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new CommandException("n must be a whole number!");
        }

        String fromRaw = args[1] + ' ' + args[2];
        String toRaw = args[3] + ' ' + args[4];

        LocalDateTime from = LocalDateTime.parse(fromRaw, formatter);
        LocalDateTime to = LocalDateTime.parse(toRaw, formatter);

        Map<MenuItem, Integer> topItems = new HashMap<>();
        for (Order order : orderRepo.getList()) {
            if(!order.getDateAndTime().isBefore(from) && !order.getDateAndTime().isAfter(to)) {
                for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
                    MenuItem item = entry.getKey();
                    int quantity = entry.getValue();
                    topItems.put(item, topItems.getOrDefault(item,0) + quantity);
                }
            }
        }

        List<Map.Entry<MenuItem, Integer>> sortedItems = new ArrayList<>(topItems.entrySet());
        sortedItems.sort((val1, val2)-> val2.getValue().compareTo(val1.getValue()));

        if (n > sortedItems.size()) {
            n = sortedItems.size();
        }

        sb.append("--- Top ").append(n).append(" Items---");
        for (int i = 0; i < n; i++) {
            MenuItem item = sortedItems.get(i).getKey();
            int quantitySold = sortedItems.get(i).getValue();
            sb.append("Item: ").append(item.getName());
            sb.append(" - ").append(quantitySold).append(" sold\n");
        }

        return sb.toString();
    }
}
