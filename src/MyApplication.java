import controllers.intefaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController controller;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController controller) {
        this.controller = controller;
    }

    public void getUserByIdMenu(int id) {
        System.out.println("Please enter id");

        String response = controller.getUser(id);
        System.out.println(response);
    }

    public void createUserMenu(String login, String password) {

        String response = controller.createUser(login, password);
        System.out.println(response);
    }
    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to the University Management System");
        System.out.println("Select option:");
        System.out.println("1. Get all students");
        System.out.println("2. Get student by id");
        System.out.println("3. Get student by login");
        System.out.println("4. Create user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-4): ");
    }
    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: getAllUsers(); break;
                  //  case 2: getUserByIdMenu(); break;
                    case 3: createUserMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }
    public void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    public void createUserMenu() {
        System.out.println("Please enter login");
        String login = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();


        String response = controller.createUser(login, password);
        System.out.println(response);
    }
}
