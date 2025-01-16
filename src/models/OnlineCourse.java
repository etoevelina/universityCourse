package models;

public class OnlineCourse extends Course {

    private String platform;

    public OnlineCourse(String courseName, String courseCode, int credits, String platform) {
        super(courseName, courseCode, credits);
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + getCourseName() + '\'' +
                ", courseCode='" + getCourseCode() + '\'' +
                ", credits=" + getCredits() +
                ", platform='" + platform + '\'' +
                '}';
    }

}