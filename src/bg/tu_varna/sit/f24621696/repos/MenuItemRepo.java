package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.RepoException;
import bg.tu_varna.sit.f24621696.interfaces.RepoInterface;
import bg.tu_varna.sit.f24621696.models.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The repository class for menu items.
 */
public class MenuItemRepo implements RepoInterface<MenuItem>, Serializable {
    /**
     * List of all menu items in the repository.
     */
    private List<MenuItem> menuItems = new ArrayList<>();

    /**
     * Adds the passed menu item into the repository.
     * @param item The menu item to be added to the repository.
     * @throws RepoException If the repository already contains the menu item.
     */
    @Override
    public void add(MenuItem item) {
        if (menuItems.contains(item)) {
            throw new RepoException("Repo already contains this item!");
        }
        menuItems.add(item);
    }

    /**
     * Removes the menu item matching the given ID.
     * @param ID the unique identifier of the menu item.
     * @throws RepoException If the matching menu item does not exist.
     */
    @Override
    public void remove(int ID) {
        for (MenuItem item : menuItems) {
            if (item.getID() == ID) {
                menuItems.remove(item);
                return;
            }
        }
        throw new RepoException("Menu item does not exist!");
    }

    /**
     * Searches for the corresponding menu item with the given ID.
     * @param ID the unique identifier of the menu item.
     * @return The menu item with the matching ID.
     * @throws RepoException If the menu item with the given ID was not found.
     */
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
            throw new RepoException("Menu item with the ID: " + ID + ", was not found!");
        }

        return item;
    }

    /**
     * Returns all currently stored menu items in the repository.
     * @return All stored menu items.
     */
    @Override
    public List<MenuItem> getItems() {
        return menuItems;
    }

    /**
     * Replaces all stored menu items with a given new list.
     * @param items The list of orders to replace the current repository items.
     */
    @Override
    public void setItems(List<MenuItem> items) {
        this.menuItems = items;
    }

    /**
     * Clears all menu items in the repository.
     */
    @Override
    public void clearItems() {
        this.menuItems.clear();
    }
}