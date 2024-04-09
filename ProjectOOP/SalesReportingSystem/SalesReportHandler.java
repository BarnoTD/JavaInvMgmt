package SalesReportingSystem;

import java.util.ArrayList;

import InventoryManagement.Product;

import java.io.*;

public class SalesReportHandler {
    public void overwriteFile(ArrayList<Transaction> transactions, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Transaction transaction : transactions) {
                String row = String.format("%d, %s, %s, %d, %s, %s",
                        transaction.getId(),
                        transaction.getCounterParty().toString(),
                        transaction.getProduct().toString(),
                        transaction.getQuantity(),
                        transaction.getTransactionType(),
                        transaction.getDate());
                writer.write(row);
                writer.newLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void appendFile(String transaction, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(transaction);
            writer.newLine();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public ArrayList<Transaction> readFile(String filename) {
        String line;
        String[] splitLine;
        Transaction transaction;
        ArrayList<Transaction> Transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(", ");
                if (splitLine.length == 11) {
                    int id = Integer.parseInt(splitLine[0]);
                    BusinessEntity counterParty = new BusinessEntity
                    (
                        splitLine[1],
                        splitLine[2]
                    ); 
                    Product product = new Product
                    (
                        Integer.parseInt(splitLine[3]),
                        splitLine[4],
                        Integer.parseInt(splitLine[5]),
                        Double.parseDouble(splitLine[6]),
                        splitLine[7]
                    );
                    int quantity = Integer.parseInt(splitLine[8]);
                    String transactionType = splitLine[9];
                    String date = splitLine[10];
                    transaction = new Transaction(id, counterParty, product, quantity, transactionType, date);
                    Transactions.add(transaction);
                } else {
                    // Handle invalid line format
                    System.out.println("Invalid line format: " + line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Log or print the exception for debugging
            System.out.println("Error reading file: " + e.getMessage());
        }

        return Transactions;
    }
}