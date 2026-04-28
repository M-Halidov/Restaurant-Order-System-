package bg.tu_varna.sit.f24621696.commands.analytic_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class ProfitReportCommand implements Command {
    private OrderRepo orderRepo;

    public ProfitReportCommand(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 4) {
            throw new CommandException("Invalid amount of arguments!\nreport <from> <to>");
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder itemSb = new StringBuilder();
        DateTimeFormatter formatter = Order.formatter;
        double profit = 0;
        int totalItems = 0;

        String fromRaw = args[0] + ' ' + args[1];
        String toRaw = args[2] + ' ' + args[3];

        LocalDateTime from = null;
        LocalDateTime to = null;
        try {
            from = LocalDateTime.parse(fromRaw, formatter);
            to = LocalDateTime.parse(toRaw, formatter);
        } catch (DateTimeParseException e) {
            throw new CommandException("Wrong format for date and time please stick to: dd/MM/yyyy HH:mm");
        }

        Map<MenuItem, Integer> orderRange = orderRepo.getOrdersByDateRange(from, to);
        if (orderRange.isEmpty()) {
            sb.append("No items found from: ").append(from.format(formatter)).append(" to ").append(to.format(formatter)).append("!\n");
            return sb.toString();
        }

        for (Map.Entry<MenuItem, Integer> entry : orderRange.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            double currItemProfit = item.getPrice() * quantity;

            itemSb.append(item.getName()).append(", individual price: ").append(item.getPrice());
            itemSb.append(", amount sold: ").append(quantity).append(", profit generated from item: ").append(currItemProfit).append("\n");

            profit += currItemProfit;
            totalItems += quantity;
        }

        sb.append("--- Profit Report ---").append("\n");
        sb.append("From: ").append(from.format(formatter));
        sb.append(" To: ").append(to.format(formatter)).append("\n\n");
        sb.append("Total orders: ").append(orderRepo.countOrdersByDateRange(from, to)).append("\n");
        sb.append("Total items sold: ").append(totalItems).append("\n\n");

        sb.append(itemSb.toString());

        sb.append("\nTotal Profit: ").append(profit).append("\n");

        return sb.toString();
    }
}
