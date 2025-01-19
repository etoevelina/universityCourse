import controllers.intefaces.IUserController;
import http.HttpManager;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.User;

public class MyApplication {
    private final IUserController controller;
    private final Scanner scanner = new Scanner(System.in);
    private boolean isUserMenu = false;

    public MyApplication(IUserController controller) {
        this.controller = controller;
    }

    HttpManager httpManager = new HttpManager();

    public void deleteUserByLoginMenu() {
        System.out.println("Please enter login");
        String login = scanner.next();
        String response = controller.deleteUserByLogin(login);
        System.out.println(response);
    }
//

    public void getUserByLoginMenu() {
        System.out.println("Please enter login");
        String login = scanner.next();
        User response = controller.getUser(login);
        System.out.println(response.toString());
    }

    public void createUserMenu() {
        System.out.println("Please enter login");
        String login = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        String response = controller.createUser(login, password);
        System.out.println(response);
    }

    public void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    private void enterTokenMenu(String login) {
        System.out.println("Please enter token");
        String token = scanner.next();
        String response = controller.createToken(token, login);
        System.out.println("Token entered: " + token);
    }

    public void loginUserMenu() {
        System.out.println("Please enter login");
        String login = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        String response = controller.loginUser(login, password);
        System.out.println(response);

        if (response.equals("Login successful!")) {
            User user = controller.getUser(login);
            if (user.getToken() == null || user.getToken().isEmpty()) {
                userAccountMenu(login);
            } else {
                anotherMenu(login);
            }
        }
    }


    private void deleteUserMenu(String login) {
        String response = controller.deleteUser(login);
        System.out.println(response);
    }

    private void changeUserDataMenu(String login) {
        System.out.println("What do you want to change?");
        System.out.println("1. Change login");
        System.out.println("2. Change password");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Please enter new login");
                String newLogin = scanner.next();
                String responseLogin = controller.changeUserData(login, newLogin, "login");
                System.out.println(responseLogin);
                break;
            case 2:
                System.out.println("Please enter new password");
                String newPassword = scanner.next();
                String responsePassword = controller.changeUserData(login, newPassword, "password");
                System.out.println(responsePassword);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void anotherMenu(String login) {
        while (true) {
            User user = controller.getUser(login);
            System.out.println("Welcome to the user menu " + login + "!");
            System.out.println(".・。.・゜✭・.・✫・゜・。..・。.・゜✭・.・✫・゜・。.");
            System.out.println("What do you want to do?");
            System.out.println("1. View user info");
            System.out.println("2. View all courses");
            System.out.println("3. View all deadlines");
            System.out.println("4. View marks by course id");
            System.out.println("5. Change data");
            System.out.println("6. Delete account");
            System.out.println("7. Logout");
            System.out.println();
            System.out.print("Enter option (1-6): ");

            try {
                int option = scanner.nextInt();


                switch (option) {
                    case 1: httpManager.getUserInfo(user.getToken()); break;
                    case 2: httpManager.getCourses(user.getToken()); break;
                    case 3: httpManager.getDeadlines(user.getToken()); break;
                    case 4: httpManager.getCourses(user.getToken());
                        System.out.println("Please copy and paste course id: ");
                        int id = scanner.nextInt();
                        httpManager.getGrades(user.getToken(), id);
                        break;
                    case 5: changeUserDataMenu(login); break;
                    case 6: deleteUserMenu(login); return;
                    case 7: return;
                    default: System.out.println("Invalid option. Please try again."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer: " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(".・。.・゜✭・.・✫・゜・。..・。.・゜✭・.・✫・゜・。.");
        }
    }

    private void userAccountMenu(String login) {
        while (true) {
            System.out.println("Welcome to the user menu " + login + "!");
            System.out.println(".・。.・゜✭・.・✫・゜・。..・。.・゜✭・.・✫・゜・。.");
            System.out.println("User Account Menu");
            System.out.println("1. Enter token");
            System.out.println("2. Change data");
            System.out.println("3. Delete account");
            System.out.println("4. Logout");
            System.out.println();
            System.out.print("Enter option (1-4): ");

            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: enterTokenMenu(login); break;
                    case 2: changeUserDataMenu(login); break;
                    case 3: deleteUserMenu(login); return;
                    case 4: return;
                    default: System.out.println("Invalid option. Please try again."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer: " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(".・。.・゜✭・.・✫・゜・。..・。.・゜✭・.・✫・゜・。.");
        }
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to the University Management System");
        System.out.println("Select option:");
        System.out.println("1. Login user account");
        System.out.println("2. Get all students");
        System.out.println("3. Get student by login");
        System.out.println("4. Create user");
        System.out.println("5. Delete user by login");
        System.out.println("0. Switch-off the program");
        System.out.println();
        System.out.print("Enter option (1-5): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: loginUserMenu(); break;
                    case 2: getAllUsersMenu(); break;
                    case 3: getUserByLoginMenu(); break;
                    case 4: createUserMenu(); break;
                    case 5: deleteUserByLoginMenu(); break;
                    case 0: return;
                    default: System.out.println("Invalid option. Please try again."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer: " + e.getMessage());
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(".・。.・゜✭・.・✫・゜・。..・。.・゜✭・.・✫・゜・。.");
        }
    }
}