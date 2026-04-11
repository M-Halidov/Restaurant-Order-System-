package bg.tu_varna.sit.f24621696.commands.analytic_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.models.MenuItem;
import bg.tu_varna.sit.f24621696.models.Order;
import bg.tu_varna.sit.f24621696.repos.OrderRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = Order.formatter;
        double profit = 0;
        int orderCount = 0;
        int totalItems = 0;

        String fromRaw = args[0] + ' ' + args[1];
        String toRaw = args[2] + ' ' + args[3];

        LocalDateTime from = LocalDateTime.parse(fromRaw, formatter);
        LocalDateTime to = LocalDateTime.parse(toRaw, formatter);


        sb.append("--- Profit Report ---").append("\n");
        sb.append("From: ").append(from);
        sb.append(" To: ").append(to).append("\n");

        for (Order order : orderRepo.getList()) {
            if(!order.getDateAndTime().isBefore(from) && !order.getDateAndTime().isAfter(to)) {
                orderCount++;
                for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
                    MenuItem item = entry.getKey();
                    int quantity = entry.getValue();

                    profit += item.getPrice() * quantity;
                    totalItems += quantity;
                }
            }
        }

        sb.append("Total orders: ").append(orderCount).append("\n");
        sb.append("Total items sold: ").append(totalItems).append("\n");

        return sb.toString();
    }
}
