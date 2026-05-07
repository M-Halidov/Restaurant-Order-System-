package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.ItemCategory;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class which represents a menu item which customers can order.
 * Each item has a unique ID, name, category, price and available quantity.
 */
public class MenuItem implements Serializable {
    /**
     * Auto-incrementing counter used to assign a unique ID to each new item.
     */
    private static int nextID = 1;

    /**
     * The unique identifier for this item.
     */
    private int ID;

    /**
     * Name of the item.
     */
    private String name;

    /**
     * The category of the item which is expressed using enum ItemCategory.
     */
    private ItemCategory category;

    /**
     * The individual price of the item.
     */
    private double price;

    /**
     * The available amount of items.
     */
    private int quantity;

    /**
     * Creates a new MenuItem with the specified details and assigns it a unique ID.
     * @param name The name of the item.
     * @param category Category of the item.
     * @param price Individual Price of the item.
     * @param quantity Available quantity.
     */
    public MenuItem(String name, ItemCategory category, double price, int quantity) {
        this.ID = nextID++;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the item.
     * @return the item category.
     */
    public ItemCategory getCategory() {
        return category;
    }

    /**
     * Returns the ID of the item.
     * @return the item ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the name of the item.
     * @return the item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the item.
     * @return the item price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the item.
     * @return the item quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the category of the item.
     * @param category the new category of the item.
     */
    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    /**
     * Sets the name of the item.
     * @param name the new name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the item.
     * @param price the new price of the item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity the new quantity of the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Checks equality between two MenuItem objects based on price, name and category.
     * @param o   the reference object with which to compare.
     * @return true if both objects have the same price, name and category.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MenuItem menuItem)) return false;
        return Double.compare(price, menuItem.price) == 0 && Objects.equals(name, menuItem.name) && category == menuItem.category;
    }

    /**
     * Computes a hashcode based on name, category and price.
     * @return the hash code of this item.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }

    /**
     * Builds the string representation of this item.
     * @return the string representation of this item.
     */
    @Override
    public String toString() {
        return "MenuItem ID " + ID + ", product name: " + name + ", " + category + ", price: "  + price + ", quantity: " + quantity;
    }
}
