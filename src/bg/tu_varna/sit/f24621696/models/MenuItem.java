package bg.tu_varna.sit.f24621696.models;

import bg.tu_varna.sit.f24621696.enums.ItemCategory;

public class MenuItem {
    private int ID;
    private String name;
    private ItemCategory category;
    private double price;
    private int quantity;

    public MenuItem(int ID, String name, ItemCategory category, double price, int quantity) {
        this.ID = ID;
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
    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

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
