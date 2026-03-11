package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.MenuItemException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepo implements RepoInterface {
    private List<MenuItem> menuItems = new ArrayList<>();

    public void store(MenuItem item) {
        if (menuItems.contains(item)) {
            throw new MenuItemException("Repo already contains this item!");
        }
        menuItems.add(item);
    }

    public void remove() {

    }
}
