package InventoryManagement;

import java.util.ArrayList;
import java.io.*;

public class ProductFileHandler {
    public ArrayList<Product> readFile(String filename) {
        String line;
        String[] splitLine;
        Product prdct;
        ArrayList<Product> Products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(", ");
                if (splitLine.length == 5) {
                    int pId = Integer.parseInt(splitLine[0].trim());
                    String pName = splitLine[1].trim();
                    int pQty = Integer.parseInt(splitLine[2].trim());
                    double pPrice = Double.parseDouble(splitLine[3].trim());
                    String pCategory = splitLine[4].trim();

                    prdct = new Product(pId,pName,pQty,pPrice,pCategory);

                    Products.add(prdct);
                } else {
                    // Handle invalid line format
                    System.out.println("BUG Invalid line format: " + line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Log or print the exception for debugging
            System.out.println("Error reading file: " + e.getMessage());
        }

        return Products;
    }
    public void appendFile(String product,String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            writer.write(product);
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public void overwriteFile(ArrayList<Product> p, String filename){
        String s;
        String tmp = filename.substring(0,filename.length()-3)+"temp";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmp,false))) {
            for (int i = 0; i < p.size(); i++) {
                s = p.get(i).toString();
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 

        try {
            File f = new File(filename);
            File tf = new File(tmp);
            f.delete();
            tf.renameTo(f);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}