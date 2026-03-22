package bg.tu_varna.sit.f24621696.repos;

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
            if (table.getId() == ID) {
                tableList.remove(table);
                System.out.println("Table removed successfully!");
                return;
            }
        }

        System.out.println("Table was not found!");
    }

    @Override
    public List<Table> getList() {
        return tableList;
    }
}
