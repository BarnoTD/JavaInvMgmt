package Authentication;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserHandler{
    public ArrayList<User> readFile(String filename) {
        String line;
        String[] splitLine;
        User usr;
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(", ");
                if (splitLine.length == 3) {
                    // ADDED NormalUser and Admin is the code: child classes of Parent <User> 
                    // which controls access to add/update/remove features in Main.java
                    
                    boolean isAdmin = Boolean.parseBoolean(splitLine[2]);
                    if (isAdmin) usr = new Admin(splitLine[0].trim(), splitLine[1].trim(),true);
                    else usr = new NormalUser(splitLine[0].trim(), splitLine[1].trim(),false);
                    
                    users.add(usr);
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

        return users;
    }

    public void register(String usr,String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            writer.write(usr);
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
}