package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.TableException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepo implements RepoInterface<Table> {
    private List<Table> tables = new ArrayList<>();

    @Override
    public void add(Table table) {
        if (tables.contains(table)) {
            throw new TableException("Table already reserved!");
        }

        tables.add(table);
    }

    @Override
    public void remove(int ID) {
        for (Table table : tables) {
            if (table.getID() == ID) {
                tables.remove(table);
                return;
            }
        }

    }

    @Override
    public Table searchForID(int ID) {
        Table table = null;
        for (Table currTable : tables) {
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
        return tables;
    }
}
