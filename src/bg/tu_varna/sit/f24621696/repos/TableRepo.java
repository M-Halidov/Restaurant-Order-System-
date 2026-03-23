package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.TableException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepo implements RepoInterface<Table> {
    private List<Table> tableList = new ArrayList<>();

    @Override
    public void add(Table table) {
        if (tableList.contains(table)) {
            throw new TableException("Table already reserved!");
        }

        tableList.add(table);
    }

    @Override
    public void remove(int ID) {
        for (Table table : tableList) {
            if (table.getID() == ID) {
                tableList.remove(table);
                return;
            }
        }

    }

    @Override
    public Table searchForID(int ID) {
        Table table = null;
        for (Table currTable : tableList) {
            if (currTable.getID() == ID) {
                table = currTable;
                break;
            }
        }
        if (table == null) {
            throw new CommandException("Order with the ID: " + ID + ", was not found!");
        }

        return table;
    }

    @Override
    public List<Table> getList() {
        return tableList;
    }
}
