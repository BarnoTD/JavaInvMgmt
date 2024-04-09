package InventoryManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0)
                    throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. Please try again:");
            }
        }

        return choice;
    }

    public int getChoice(String username) {

        int choice;

        System.out.println("================================");
        System.out.println("Welcome to Inventory Management Center, "+username);
        System.out.println("================================");
        System.out.println("1) Add Product");
        System.out.println("2) Remove Product");
        System.out.println("3) Update Product");
        System.out.println("4) Display Product Information");
        System.out.print("\nPlease select an option (or Enter -1 to return to Actities):");
        choice = getIntInput();

        return choice;
    }

    public String addProduct(ArrayList<Product> p) {

        int id;
        String name;
        int qty;
        double price;
        String category;
        Product prd;
        String product;

        System.out.print("insert Product name: ");
        name = reader.nextLine();

        System.out.print("insert Product Quantity: ");
        qty = reader.nextInt();
        reader.nextLine();

        System.out.print("insert Product Price: ");
        price = reader.nextDouble();
        reader.nextLine();

        System.out.print("insert Product Category: ");
        category = reader.nextLine();

        //AUTO INCREMENT ID
        if (p.size() > 0)
            id = p.getLast().getId() + 1;
        else
            id = 1;

        prd = new Product(id, name, qty,price, category);
        p.add(prd);
        product = prd.toString();
        System.out.println("\nSTATUS: Product added\n");

        return product;
    }

    public void updateProductStock(ArrayList<Product> p,int pId, int newQty, boolean inc){
        for ( int i=0; i<p.size(); i++){
            if (p.get(i).getId() == pId)
            {
                p.get(i).updateQty(newQty,inc);
                System.out.println("The product has been updated");
                return;
            }
        }
    }

    public String addProduct(ArrayList<Product> p, int id) {

        String name;
        int qty;
        double price;
        String category;
        Product prd;
        String product;

        System.out.print("insert Product name: ");
        name = reader.nextLine();

        System.out.print("insert Product Quantity: ");
        qty = reader.nextInt();
        reader.nextLine();

        System.out.print("insert Product Price: ");
        price = reader.nextDouble();
        reader.nextLine();

        System.out.print("insert Product Category: ");
        category = reader.nextLine();

        prd = new Product(id, name, qty,price, category);
        p.add(prd);
        product = prd.toString();
        System.out.println("\nSTATUS: Product added\n");

        return product;
    }

    public void removeProduct(ArrayList<Product> p){
        int id;

        System.out.print("insert the ID of the Product you want to remove: ");
        id = getIntInput();

        for ( int i=0; i<p.size(); i++){
            if (p.get(i).getId() == id)
            {
                p.remove(i);
                System.out.println("The product has been removed");
                return;
            }
        }
        System.out.println("ProductID not found.");
    }

    public void printProductInfo(ArrayList<Product> p){
        int id;

        System.out.print("insert the Product ID: ");
        id = getIntInput();

        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getId() == id)
            {
                System.out.println("=============================");
                String[] productInfo = p.get(i).toString().split(", ");
                System.out.println("Product ID: " + productInfo[0]);
                System.out.println("Product Name: " + productInfo[1]);
                System.out.println("Product Quantity: " + productInfo[2]);
                System.out.println("Product Price: " + productInfo[3]);
                System.out.println("Product Category: " + productInfo[4]);
                System.out.println("=============================");
                return;
            }
        }
        System.out.println("ProductID not found!");
    }
    
    public void updateProduct(ArrayList<Product> p){
        int id;

        System.out.print("insert the ID of the Product you want to update: ");
        id = getIntInput();
        String Name;
        String Quantity;
        String Price;
        String Category;

        for ( int i=0; i<p.size(); i++){
            if (p.get(i).getId() == id)
            {
                System.out.printf("modify Name (or leave empty to keep it as %s): ",p.get(i).getName());
                Name = reader.nextLine(); 
                if (Name != "") p.get(i).setName(Name);

                System.out.printf("modify Quantity (or leave empty to keep it as %d): ",p.get(i).getQty());
                Quantity = reader.nextLine();
                if (Quantity != "") p.get(i).setQty(Integer.parseInt(Quantity));

                System.out.printf("modify Price (or leave empty to keep it as %f ): ",p.get(i).getPrice());
                Price = reader.nextLine();
                if (Price != "") p.get(i).setPrice(Double.parseDouble(Price));

                System.out.printf("modify Category (or leave empty to keep it as %s): ",p.get(i).getCategory());
                Category = reader.nextLine();
                if (Category != "") p.get(i).setCategory(Category);

                System.out.println("The product has been updated");
                return;
            }
        }
        System.out.println("ProductID not found.");

    }
}

