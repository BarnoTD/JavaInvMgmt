package SalesReportingSystem;

import java.util.ArrayList;
import InventoryManagement.Product;

interface Calculations{
    double calcTotalRevenue(ArrayList<Transaction> t);
    double calcTotalCost(ArrayList<Transaction> t);
    double calcProfit(double revenue, double expenses);
    double calcInvValue(ArrayList<Product> p);
}

public class Stats implements Calculations{
    @Override
    public double calcTotalRevenue(ArrayList<Transaction> t){
        double totalRevenue = 0;
        for (Transaction transaction : t) {
            if (transaction.getTransactionType().toLowerCase().equals("sale"))
                {
                    totalRevenue += (transaction.getQuantity() * transaction.getProduct().getPrice());
                }
        }
        return totalRevenue;
    }

    @Override
    public double calcTotalCost(ArrayList<Transaction> t){
        double totalCost = 0;
        for (Transaction transaction : t) {
            if (transaction.getTransactionType().toLowerCase().equals("purchase"))
                {
                    totalCost += (transaction.getQuantity() * transaction.getProduct().getPrice());
                }
        }
        return totalCost;
    }

    @Override
    public double calcProfit(double revenue, double expenses) {
        return revenue - expenses;
    } 

    @Override
    public double calcInvValue(ArrayList<Product> p) {
        double inventoryValue = 0;
        for (Product product : p) {
            inventoryValue += (product.getPrice() * product.getQty());
        }
        return inventoryValue;
    };


}
