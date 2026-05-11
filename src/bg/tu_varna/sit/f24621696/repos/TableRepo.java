package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.RepoException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The repistory class for tables.
 */
public class TableRepo implements RepoInterface<Table>, Serializable {
    /**
     * List of all tables in the repository.
     */
    private List<Table> tables = new ArrayList<>();

    /**
     * Adds the passed table into the repository.
     * @param table The table to be added to the repository
     * @throws RepoException If the repository already contains the table.
     */
    @Override
    public void add(Table table) {
        if (tables.contains(table)) {
            throw new RepoException("Table with the id: " + table.getID() + " already exists!");
        }

        tables.add(table);
    }

    /**
     * Removes the table with the matching the given ID.
     * @param ID the unique identifier of the table.
     * @throws RepoException If the matching table does not exist.
     */
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

    /**
     * Searches for the corresponding table with the given ID.
     * @param ID the unique identifier of the table.
     * @return The table with the matching ID.
     * @throws RepoException If the table with the given ID was not found.
     */
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

    /**
     * Returns all currently stored tables in the repository.
     * @return All stored tables.
     */
    @Override
    public List<Table> getItems() {
        return tables;
    }

    /**
     * Replaces all stored tables with a given new list.
     * @param items The list of tables to the current repository items.
     */
    @Override
    public void setItems(List<Table> items) {
        this.tables = items;
    }

    /**
     * Clears all tables in the repository.
     */
    @Override
    public void clearItems() {
        tables.clear();
    }
}
