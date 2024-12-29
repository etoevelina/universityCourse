import models.Course;
import models.OnlineCourse;
import models.Professor;
import models.University;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Course javaCourse = new OnlineCourse("Java Programming", "CS101", 6, "YouTube");
        Course course1 = new OnlineCourse("Data Structures", "CS102", 5, "Learn");
        Course course2 = new OnlineCourse("OOP", "CS102", 4, "Learn");

        Professor professor1 = new Professor();
        Professor professor2 = new Professor();

        professor1.setData("Dr. Alice", "Computer Science", 10);
        professor2.setData("Dr. Bob", "Mathematics", 8);

        University university = new University();
        university.addUniversity("Tech University");

        university.addCourse(javaCourse);
        university.addCourse(course1);
        university.addCourse(course2);
        university.addProfessor(professor1);
        university.addProfessor(professor2);

        university.sortCoursesByCredits();

        university.displayUniversityDetails();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Display University Details");
            System.out.println("2. Display Courses after Sorting");
            System.out.println("3. Compare Two Courses by Code");
            System.out.println("4. Compare Two Courses by Name");
            System.out.println("5. Find Course by Course Code");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    do {
                        university.displayUniversityDetails();
                    } while (repeatTool(scanner, "Display University Details"));
                    break;
                case 2:
                    do {
                        university.displayCourses();
                    } while (repeatTool(scanner, "Display Courses after Sorting"));
                    break;
                case 3:
                    do {
                        System.out.println("\nComparing two courses:");
                        System.out.println("Course 1 and Course 2 are " +
                                (course1.getCourseCode().equals(course2.getCourseCode()) ? "the same" : "different"));
                    } while (repeatTool(scanner, "Compare Two Courses by Code"));
                    break;

                case 4:
                    do {
                        System.out.print("Enter first course name: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Enter second course name: ");
                        String name2 = scanner.nextLine();
                        System.out.println("\nComparing two courses by name:");
                        System.out.println("names of Course 1 and Course 2 are " + (name1.equals(name2) ? "equal" : "different"));
                    } while (repeatTool(scanner, "Compare Two Courses by Name"));
                    break;

                case 5:
                    do {
                        System.out.print("Enter course code to search: ");
                        String code = scanner.nextLine();
                        university.findCourseByCourseCode(code);
                    } while (repeatTool(scanner, "Find Course by Course Code"));
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (choice != 6);

        scanner.close();
    }

    private static boolean repeatTool(Scanner scanner, String toolName) {
        System.out.print("Do you want to return to the menu? (y/n): ");
        String returnToMenu = scanner.nextLine();
        if (!returnToMenu.equalsIgnoreCase("y")) {
            System.out.print("Do you want to use the chosen tool (" + toolName + ") again? (y/n): ");
            String useAgain = scanner.nextLine();
            if (useAgain.equalsIgnoreCase("y")) {
                return true;
            }
        }
        return false;
    }
}