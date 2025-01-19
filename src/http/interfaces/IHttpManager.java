package http.interfaces;

public interface IHttpManager {
    String getUserInfo(String token);
    String getCourses(String token);
    String getDeadlines(String token);
    String getGrades(String token, int courseId);
}
