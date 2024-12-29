package models;

import java.util.ArrayList;

public class University {
    private String universityName;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();

    public void addUniversity(String universityName) {
        this.universityName = universityName;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public void sortCoursesByCredits(){
        int n = courses.size();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (courses.get(j).getCredits() > courses.get(j + 1).getCredits()) {
                  Course temp = courses.get(j);
                    courses.set(j, courses.get(j + 1));
                    courses.set(j + 1, temp);
                }
            }
        }
    }

    public void findCourseByCourseCode(String code) {
        ArrayList<Course> foundCourses = new ArrayList<>();
        for (Course course : courses) {
            if (code.equals(course.getCourseCode())) {
                foundCourses.add(course);
            }
        }

        if (foundCourses.isEmpty()) {
            System.out.println("No courses found with code: " + code);
        } else {
            System.out.println("Courses found with code " + code + ":");
            for (Course course : foundCourses) {
                System.out.println(course);
            }
        }
    }

    public void displayCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    public void displayUniversityDetails() {
        System.out.println("University Name: " + universityName);
        System.out.println("Courses Offered: ");
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.println("Professors: ");
        for (Professor professor : professors) {
            System.out.println(professor);
        }
    }
}