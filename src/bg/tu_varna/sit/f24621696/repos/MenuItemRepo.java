package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.RepoException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepo implements RepoInterface<MenuItem>, Serializable {
    private List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public void add(MenuItem item) {
        if (menuItems.contains(item)) {
            throw new RepoException("Repo already contains this item!");
        }
        menuItems.add(item);
    }

    @Override
    public void remove(int ID) {
        for (MenuItem item : menuItems) {
            if (item.getID() == ID) {
                menuItems.remove(item);
                return;
            }
        }

    }

    @Override
    public MenuItem searchForID(int ID) {
        MenuItem item = null;
        for (MenuItem currItem : menuItems) {
            if (currItem.getID() == ID) {
                item = currItem;
                break;
            }
        }
        if (item == null) {
            throw new RepoException("Order with the ID: " + ID + ", was not found!");
        }

        return item;
    }

    @Override
    public List<MenuItem> getItems() {
        return menuItems;
    }

    @Override
    public void setItems(List<MenuItem> items) {
        this.menuItems = items;
    }
}
