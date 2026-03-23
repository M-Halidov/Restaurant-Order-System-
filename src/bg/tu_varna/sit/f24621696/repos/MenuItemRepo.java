package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.MenuItemException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepo implements RepoInterface<MenuItem> {
    private List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public void add(MenuItem item) {
        if (menuItems.contains(item)) {
            throw new MenuItemException("Repo already contains this item!");
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
            throw new CommandException("Order with the ID: " + ID + ", was not found!");
        }

        return item;
    }

    @Override
    public List<MenuItem> getList() {
        return menuItems;
    }
}
