import models.Course;
import models.OnlineCourse;
import models.Professor;
import models.University;

public class Main {
    public static void main(String[] args) {
        Course javaCourse = new OnlineCourse("Java Programming", "CS101", 4, "Coursera");
        Course course1 = new Course();
        Course course2 = new Course();

        course1.setData("CS102", "Data Structures", 4);
        course2.setData("CS102", "Data Structures", 4);

        Professor professor1 = new Professor();
        Professor professor2 = new Professor();

        professor1.setData("Dr. Alice", "Computer Science", 10);
        professor2.setData("Dr. Bob", "Mathematics", 8);

        University university = new University();
        university.addUniversity("Tech University");

        university.addCourse(course1);
        university.addCourse(course2);
        university.addProfessor(professor1);
        university.addProfessor(professor2);



        university.displayUniversityDetails();
        //Assigment 1
        System.out.println("\nComparing two courses:");
        System.out.println("Course 1 and Course 2 are " +
                (course1.getCourseCode().equals(course2.getCourseCode()) ? "the same" : "different"));

        //Assigment 2 переопределенная функция сравнения
        System.out.println("\nComparing two courses by name:");
        System.out.println("names of Course 1 and Course 2 are " + (course1.equals(course2) ? "equal" : "different"));
        System.out.println("\n");
        printCourseDetails(javaCourse);
    }
    public static void printCourseDetails(Course course) {
        System.out.println(course);
    }
}