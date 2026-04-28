package bg.tu_varna.sit.f24621696.commands.analytic_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        LocalDateTime from = null;
        LocalDateTime to = null;
        try {
            from = LocalDateTime.parse(fromRaw, formatter);
            to = LocalDateTime.parse(toRaw, formatter);
        } catch (DateTimeParseException e) {
            throw new CommandException("Wrong format for date and time please stick to: dd/MM/yyyy HH:mm");
        }

        Map<MenuItem, Integer> topItems = orderRepo.getOrdersByDateRange(from, to);

        if (topItems.isEmpty()) {
            sb.append("No items found from: ").append(from.format(formatter)).append(" to ").append(to.format(formatter)).append("!\n");
            return sb.toString();
        }

        List<Map.Entry<MenuItem, Integer>> sortedItems = new ArrayList<>(topItems.entrySet());
        sortedItems.sort((val1, val2)-> val2.getValue().compareTo(val1.getValue()));

        if (n > sortedItems.size()) {
            n = sortedItems.size();
        }

        int totalQuantity = 0;
        for (Map.Entry<MenuItem, Integer> sortedItem : sortedItems) {
            totalQuantity += sortedItem.getValue();
        }

        sb.append("--- Top ").append(n).append(" Items---").append("\n");
        for (int i = 0; i < n; i++) {
            MenuItem item = sortedItems.get(i).getKey();
            int quantitySold = sortedItems.get(i).getValue();
            double averagePercentage = (((double)quantitySold/totalQuantity)*100);

            sb.append("Item: ").append(item.getName());
            sb.append(" - ").append(quantitySold).append(" sold");
            sb.append(", average user choice: ").append(Math.round(averagePercentage)).append("%\n");
        }

        sb.append("\n");
        return sb.toString();
    }
}
