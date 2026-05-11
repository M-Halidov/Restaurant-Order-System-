package bg.tu_varna.sit.f24621696.interfaces;

import java.util.List;

/**
 * Generic repository interface defining standard operations for managing collections of objects.
 * @param <T> the type of object managed by this repository.
 */
public interface RepoInterface<T> {
    /**
     * Adds the specified item to the repository.
     * @param t the item to add.
     */
    void add(T t);

    /**
     * Removes the item with the specified ID from the repository.
     * @param ID the unique identifier of the item to remove.
     */
    void remove(int ID);

    /**
     * Replaces all items stored in the repository with the specified list.
     * @param items the new list of items.
     */
    void setItems(List<T> items);

    /**
     * Searches for and returns the item with the specified ID.
     * @param ID the unique identifier of this item.
     * @return the item matching the given ID.
     */
    T searchForID(int ID);

    /**
     * Returns all items in the repository.
     * @return a list of all items.
     */
    List<T> getItems();

    /**
     * Clears all items stored in the repository.
     */
    void clearItems();
}
