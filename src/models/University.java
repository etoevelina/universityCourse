package models;

import java.util.ArrayList;

public class University {
    private String universityName;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Professor> professors =  new ArrayList<>();

    public void addUniversity(String universityName) {
        this.universityName = universityName;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }


    public void displayUniversityDetails() {
        System.out.println("University Name: " + universityName);
        System.out.println("Courses Offered: " + courses );
//        for (Course course : courses) {
//            course.toString();
//        }
        System.out.println("Professors: " + professors);
//        for (Professor professor : professors) {
//            professor;
//        }
    }
}