import Authentication.*;
import InventoryManagement.*;
import SalesReportingSystem.SalesReportHandler;
import SalesReportingSystem.SalesReportingSystem;
import SalesReportingSystem.Transaction;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static String username;

    public static void main(String[] args) {
        String save;
        boolean isLoggedIn = false;
        boolean isAdmin = false;
        int choice = 0;

        Scanner input = new Scanner(System.in);
 
        UserManagement um = new UserManagement();
        UserHandler ufh = new UserHandler();


        InventoryManagement im = new InventoryManagement();
        ProductFileHandler pfh = new ProductFileHandler();

        SalesReportingSystem srs = new SalesReportingSystem();
        SalesReportHandler srh = new SalesReportHandler();
        
        ArrayList<User> users = ufh.readFile("Authentication/users.csv");

        while ((choice != 1 && choice !=2)&& choice != -1) {
            System.out.print("Welcome!\n1) Log in\n2) Sign Up\n\nSelect a choice (or enter -1 to quit): ");
            choice = input.nextInt();
            input.nextLine();
    
            switch (choice) {
                    case 1:
                        System.out.println();
                        users = ufh.readFile("Authentication/users.csv");
                        do {

                        //Token Object: includes username, login check and admin check
                        Token token = um.signin(users);
                        isLoggedIn = token.getLogin();
                        isAdmin = token.getAdmin();
                        username = token.getUsername();
                        if(!isLoggedIn)System.out.println("wrong username/password! Try Again");

                        } while (!isLoggedIn);
                        break;
                    case 2:
                        System.out.println();
                        save = um.signup(users);
                        ufh.register(save, "Authentication/users.csv");
                        choice = 0;
                        break;
                    case -1:
                        break;
                    default:
                        System.out.println("INVALID INPUT! Try again");
                        System.out.println("==============================");
                        break;
                }
        }

        if(isLoggedIn) 
            {
                ArrayList<Product> Products = pfh.readFile("InventoryManagement/products.csv");
                ArrayList<Transaction> transactions = srh.readFile("SalesReportingSystem/transactions.csv");
                int aChoice;
                do 
                    {
                    aChoice = 0;
                    aChoice = Activities.pick(username);

                    if(aChoice == 1)
                        {
                            choice = im.getChoice(username);
                    
                            while (choice != -1)
                            {
                            Products = pfh.readFile("InventoryManagement/products.csv");

                            //Cases that contain if(isAdmin)else statements mean they're only accessible to Admins (ie: hr, admin, etc...)
                            switch (choice) 
                                {
                                case 1:
                                    if (isAdmin){
                                        save = im.addProduct(Products);
                                        pfh.appendFile(save,"InventoryManagement/products.csv");
                                    }
                                    else System.out.println("Access Denied: OPERATION RESERVED FOR ADMINS");    
                                    break;
                                case 2:
                                    if (isAdmin){
                                        im.removeProduct(Products);
                                        pfh.overwriteFile(Products,"InventoryManagement/products.csv");
                                    }
                                    else System.out.println("Access Denied: OPERATION RESERVED FOR ADMINS");
                                    break;
                                case 3:
                                    if (isAdmin) {
                                        im.updateProduct(Products);
                                        pfh.overwriteFile(Products,"InventoryManagement/products.csv");
                                    } else System.out.println("Access Denied: OPERATION RESERVED FOR ADMINS");
                                    break;
                                case 4:
                                    im.printProductInfo(Products);
                                    break;
                                
                            
                                default:
                                    System.out.println("INVALID OPTION! please try again\n");
                                    break;
                                }
                            choice = im.getChoice(username);
                            }
                        }
                        else if(aChoice == 2 )
                        {
                            System.out.println("================================");
                            choice = srs.getChoice(username);
                            while (choice != -1) {
                                Products = pfh.readFile("InventoryManagement/products.csv");
                                transactions = srh.readFile("SalesReportingSystem/transactions.csv");
                                switch (choice) {
                                    case 1:
                                        if (isAdmin) {
                                            save = srs.logTransaction(transactions, Products);
                                            srh.appendFile(save,"SalesReportingSystem/transactions.csv" );
                                        } else System.out.println("Access Denied: OPERATION RESERVED FOR ADMINS");
                                        break;
                                    case 2:
                                        if (isAdmin) {
                                            srs.removeTransaction(transactions);
                                            srh.overwriteFile(transactions, "SalesReportingSystem/transactions.csv");
                                        } else System.out.println("Access Denied: OPERATION RESERVED FOR ADMINS");
                                        break;
                                    case 3:
                                        srs.displayInventoryStatus(Products);
                                        break;
                                    case 4:
                                        srs.displayAllTransactions(transactions);
                                        break;
                                    case 5:
                                        srs.displayTransaction(transactions);
                                        break;
                                    case 6:
                                        srs.generateSalesReport(transactions, Products);
                                    case -1:
                                        break;
                                
                                    default:
                                        System.out.println("INVALID INPUT! Try again");
                                        System.out.println("==============================");
                                        break;
                                }
                                choice = srs.getChoice(username);
                            }
                            
                        }
                        else if(aChoice == -1)continue;
                        else System.out.println("Invalid output try again");
                    } while (aChoice != -1);

            } 

       
        

        
        System.out.println("=====================================================================");
        System.out.println("Goodbye!");
        input.close();

    }
}
