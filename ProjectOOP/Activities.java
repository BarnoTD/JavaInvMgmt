import java.util.InputMismatchException;
import java.util.Scanner;

public class Activities {

    final private static Scanner reader = new Scanner(System.in);

    public static int pick(String username) {
        System.out.println("\n=====================================================================\n");
        System.out.printf("Hello %s, What do you want to do? \n1) Manage Inventory \n2) Manage Sales Reports\n", username);
        int choice = getIntInput();
        return choice;
    }

    public static int getIntInput() {
        int choice = 0;
        System.out.print("Select an activity (or type -1 to quit): ");
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
}
