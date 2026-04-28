package bg.tu_varna.sit.f24621696.enums;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;

import java.io.Serializable;

/**
 * Represents the food category of the item
 */
public enum ItemCategory implements Serializable {
    /**
     * Indicates that the food item is an appetizer.
     */
    APPETIZER("appetizer"),

    /**
     * Indicates that the food item is a main.
     */
    MAIN("main"),

    /**
     * Indicates that the food item is a desert.
     */
    DESERT("desert"),

    /**
     * Indicates that the food item is a drink.
     */
    DRINK("drink"),

    /**
     * Indicates that the food item isn't specified.
     */
    OTHER("other");

    /**
     * The string representation of this category.
     */
    private final String itemCategory;

    /**
     * Constructs an ItemCategory with the specified string value.
     * @param itemCategory the string representation of the category.
     */
    ItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * Returns the ItemCategory constant matching the given string value.
     * @param itemCategory the string value to search for.
     * @return the matching ItemCategory constant.
     * @throws CommandException if the given category was not found in the enum.
     */
    public static ItemCategory getCategory(String itemCategory) {
        for (ItemCategory category : ItemCategory.values()) {
            if (category.itemCategory.equals(itemCategory)) {
                return category;
            }
        }

        throw new CommandException("Unknown item category: " + itemCategory +
                "\nValid: appetizer, main, desert, drink, other");
    }
}
