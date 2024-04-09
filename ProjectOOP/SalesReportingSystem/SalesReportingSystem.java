package SalesReportingSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import InventoryManagement.InventoryManagement;
import InventoryManagement.Product;
import InventoryManagement.ProductFileHandler;

public class SalesReportingSystem {
    final private Scanner reader = new Scanner(System.in);
    
    public int getChoice(String username) {
        int choice;

        System.out.printf("\nWelcome to Sales Reporting System, %s \n",username);
        System.out.println("================================");
        System.out.println("1) Log Transaction");
        System.out.println("2) Remove Transaction");
        System.out.println("3) Display Inventory Status");
        System.out.println("4) Display All Transactions");
        System.out.println("5) Lookup specific transaction");
        System.out.println("6) Generate Sales Report");



        System.out.print("\nPlease select an option (or Enter -1 to return to Actities):");
        choice = getIntInput();

        return choice;
    }

    public int getIntInput() {
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

    public String logTransaction(ArrayList<Transaction> t, ArrayList<Product> p){
        int id;

        String bName;
        String bContact;
        BusinessEntity counterPart;

        int pId;
        String pName;
        int pQty;
        double pPrice;
        String pCategory;
        Product prd;
        int pIndex = -1;

        String transcType;
        int qty;
        String date;

        
        Transaction transaction;
        String transcString;

        System.out.print("insert Counter party name: ");
        bName = reader.nextLine();

        System.out.print("insert counter party phone contact: ");
        bContact = reader.nextLine();

        counterPart = new BusinessEntity(bName, bContact);
        
        System.out.print("insert Product id related to this transaction: ");
        pId = reader.nextInt();
        reader.nextLine();
        pIndex = checkProduct(pId, p);
        if (pIndex != -1){
            prd = p.get(pIndex);
            System.out.println("Product ID exists in the database (AutoFill successful!)");
            System.out.print("insert Transaction Type (sale or purchase): ");
            transcType = reader.nextLine();
            boolean increase = false;
            if (transcType.toLowerCase().equals("sale")) increase = false;
            else increase = true;

            if (!increase){
                System.out.print("insert Quantity sold: ");
                qty = reader.nextInt();
                reader.nextLine();
            }
            else {
                System.out.print("insert Quantity bought: ");
                qty = reader.nextInt();
                reader.nextLine();
            }
            InventoryManagement tempIM = new InventoryManagement();
            tempIM.updateProductStock(p,pId,qty,increase);
            new ProductFileHandler().overwriteFile(p,"InventoryManagement/products.csv");

        }
        else{
            System.out.println("new ProductID detected, Adding to Product Database");

            System.out.print("insert Product name: ");
            pName = reader.nextLine();

            pQty = 0;

            
            System.out.print("insert Product Price: ");
            pPrice = reader.nextDouble();
            reader.nextLine();

            System.out.print("insert Product Category: ");
            pCategory = reader.nextLine();

            //AUTO INCREMENT ID
            if (p.size() > 0)
            id = p.getLast().getId() + 1;
            else
            id = 1;

            prd = new Product(id, pName, pQty,pPrice, pCategory);
            p.add(prd);


            transcType = "Purchase";
            boolean increase = true;
            System.out.print("insert Quantity bought: ");
            qty = reader.nextInt();
            reader.nextLine();

            p.getLast().updateQty(qty, increase);
            prd = p.getLast();
            System.out.println(prd.toString());
            new ProductFileHandler().appendFile(prd.toString(), "InventoryManagement/products.csv");
            System.out.println("\nSTATUS: Product added\n");
        }

        System.out.println("insert Transaction Date (in the format DD/MM/YYYY): ");
        date = reader.nextLine();

        //AUTO INCREMENT ID
        if (t.size() > 0)
            id = t.getLast().getId() + 1;
        else
            id = 1;
        transaction = new Transaction(id, counterPart, prd, qty, transcType, date);
        t.add(transaction);
        transcString = transaction.toString();
        System.out.println("\nSTATUS: Transaction added\n");
        return transcString;
    }
    public void removeTransaction(ArrayList<Transaction> t) {
        int id;

        System.out.print("insert the ID of the Transaction you want to remove: ");
        id = getIntInput();

        for ( int i=0; i<t.size(); i++){
            if (t.get(i).getId() == id)
            {
                t.remove(i);
                System.out.println("The transaction has been removed");
                return;
            }
        }
        System.out.println("TransactionID not found.");
    }
    public void displayAllTransactions(ArrayList<Transaction> t){
        System.out.println(" T ID | CtrPty Name | CtrPty Info |  Pr ID | Pr Name | Pr Qty | Pr Price | Pr Ctg | Qty in/out | T Type | Date | ");
        System.out.println("=============================================================================================================================");
        for (int i = 0; i < t.size(); i++) {
            {
                String[] transactionInfo = t.get(i).toString().split(", ");
                for (String element : transactionInfo) {
                    System.out.print(element + " | ");
                }
                System.out.println();
                System.out.println("=============================================================================================================================");
            }
            
        }

    }
    public void displayTransaction(ArrayList<Transaction> t){
        int id;

        System.out.print("insert the Transaction ID: ");
        id = getIntInput();

        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getId() == id)
            {
                System.out.println("=============================");
                String[] productInfo = t.get(i).toString().split(", ");
                System.out.println("Transaction ID: " + productInfo[0]);
                System.out.println("Counter Party Name: " + productInfo[1]);
                System.out.println("Counter Party Contact: " + productInfo[2]);
                System.out.println("Product ID: " + productInfo[3]);
                System.out.println("Product Name: " + productInfo[4]);
                System.out.println("Product Quantity: " + productInfo[5]);
                System.out.println("Product Price: " + productInfo[6]);
                System.out.println("Product Category: " + productInfo[7]);
                if (productInfo[9].equals("Sale")){System.out.println("Quantity Sold: " + productInfo[8]);}
                else {System.out.println("Quantity Bought: " + productInfo[8]);}
                System.out.println("Transaction Type: " + productInfo[9]);
                System.out.println("Transaction Date: " + productInfo[10]);
                System.out.println("=============================");
                return;
            }
        }
        System.out.println("ProductID not found!");
    }
    public void displayInventoryStatus(ArrayList<Product> p){
        System.out.println("Product ID | Name | Quantity | Price | Category");
        System.out.println("=============================================================================================================================");
        for (int i = 0; i < p.size(); i++) {
            {
                String[] transactionInfo = p.get(i).toString().split(", ");
                for (String element : transactionInfo) {
                    System.out.print(element + " | ");
                }
                System.out.println();
                System.out.println("=============================================================================================================================");
            }
            
        }
    }
    public int checkProduct(int id, ArrayList<Product> p){
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getId() == id)
            {
                return i;
            }
        }
        return -1;
    }
    public void generateSalesReport(ArrayList<Transaction> t, ArrayList<Product> p){
        Stats stats = new Stats();
        System.out.println("=====================================");
        System.out.println("--------------SALES REPORT-----------");
        System.out.println("=====================================");
        System.out.println();
        System.out.println("Inventory Value: $" + stats.calcInvValue(p) );
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Total Revenues: $" + stats.calcTotalRevenue(t));
        System.out.println("Total Costs: $" + stats.calcTotalCost(t));
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Net Profit: $" + stats.calcProfit(stats.calcTotalRevenue(t),stats.calcTotalCost(t)) );
        System.out.println();
        System.out.println("--------------------------------------------");


    }
}
