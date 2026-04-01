package bg.tu_varna.sit.f24621696.enums;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;

public enum ItemCategory {
    APPETIZER("appetizer"),
    MAIN("main"),
    DESERT("desert"),
    DRINK("drink"),
    OTHER("other");

    private final String itemCategory;

    ItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

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
