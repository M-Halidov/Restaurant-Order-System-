package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.ItemCategory;

import java.util.Objects;

public class MenuItem {
    private static int nextID = 1;
    private int ID;
    private String name;
    private ItemCategory category;
    private double price;
    private int quantity;

    public MenuItem(String name, ItemCategory category, double price, int quantity) {
        this.ID = nextID++;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public ItemCategory getCategory() {return category;}
    public int getID() {return ID;}
    public String getName() {return name;}
    public double getPrice() {return price;}
    public int getQuantity() {return quantity;}

    public void setCategory(ItemCategory category) {this.category = category;}
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MenuItem menuItem)) return false;
        return ID == menuItem.ID && Double.compare(price, menuItem.price) == 0 && quantity == menuItem.quantity && Objects.equals(name, menuItem.name) && category == menuItem.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, category, price, quantity);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
