package InventoryManagement;

public class Product {
    private int id;
    private String name;
    private int qty;
    private double price;
    private String category;

    public Product(String name, int qty, double price, String category){
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.category = category;
    }

    public Product(int id, String name, int qty, double price, String category){
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public void updateQty(int qty, boolean increase) {
        if (increase)
        {this.qty += qty;}
        else { this.qty -= qty;};
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return (id + ", " + name +", "  + qty +", "+ price +", "+ category );
    }
}