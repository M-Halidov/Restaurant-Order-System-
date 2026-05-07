package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.RepoException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableRepo implements RepoInterface<Table>, Serializable {
    private List<Table> tables = new ArrayList<>();

    @Override
    public void add(Table table) {
        if (tables.contains(table)) {
            throw new RepoException("Table with the id: " + table.getID() + " already exists!");
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

        throw new RepoException("Table does not exist!");

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
            throw new RepoException("Table with the ID: " + ID + ", was not found!");
        }

        return table;
    }

    @Override
    public List<Table> getItems() {
        return tables;
    }

    @Override
    public void setItems(List<Table> items) {
        this.tables = items;
    }

    @Override
    public void clearItems() {
        tables.clear();
    }
}
