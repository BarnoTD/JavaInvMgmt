package Authentication;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {

    final private Scanner input = new Scanner(System.in);

    public Token signin(ArrayList<User> u) {
        String username;
        String password;
        boolean isAdmin=false;
        boolean isLoggedIn=false;

        System.out.print("insert name: ");
        username = input.nextLine();

        System.out.print("insert password: ");
        password = input.nextLine();

        for(User usr : u){
            isLoggedIn = ((usr.getName().equals(username))&&(usr.getPassword().equals(password)));
            isAdmin = usr.getIsAdmin();
            if (isLoggedIn) break;
        }

        Token token = new Token(username,isLoggedIn,isAdmin);
        return token;
    }

    public String signup(ArrayList<User> u) {

        String username;
        String password;
        User user;
        ArrayList<String> usernames = new ArrayList<>();

        for (int i = 0; i < u.size(); i++) {
            usernames.add(u.get(i).getName());
        };

        do {
            System.out.print("insert desired username: ");
            username = input.nextLine();
            if (usernames.contains(username))
                System.out.println("Username taken! Try again.");
        } while (usernames.contains(username));

        System.out.print("insert desired password: ");
        password = input.nextLine();

        user = new NormalUser(username, password,false);
        
        u.add(user);
        System.out.println("STATUS: user added.");
        return user.toString();
    }
}