package SalesReportingSystem;

import InventoryManagement.*;

class BusinessEntity {
    String name;
    String contactInfo;
    public BusinessEntity(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Getter and Setter for entityId

    public String getName() {
        return name;
    };


    @Override
    public String toString() {
        return name +", "+ contactInfo;
    }
}

public class Transaction{
    private BusinessEntity counterParty; // Can be Supplier or Customer (3)
    private int id; 
    private Product product; //(5)
    private int quantity;
    private String transactionType;
    private String date;

    public Transaction(int id, BusinessEntity counterParty, Product product, int quantity, String transactionType, String date) {
        this.id = id;
        this.counterParty = counterParty;
        this.product = product;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.date = date;
    }

    public BusinessEntity getCounterParty() {
        return counterParty;
    }
    public int getId() {
        return id;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public String getDate() {
        return date;
    }

    public void setCounterParty(BusinessEntity counterParty) {
        this.counterParty = counterParty;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        
        return id + ", "+ counterParty.toString() + ", "+ product.toString() + ", "+ quantity + ", "+  transactionType + ", "+  date;
    }
}