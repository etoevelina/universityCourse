package http;

import http.interfaces.IHttpManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HttpManager implements IHttpManager {
    public HttpManager() {
    }

    public String getUserInfo(String token) {
        String baseUrl = "http://31.128.42.58:8080/get_user_info/";
        String urlString = baseUrl + token;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonObject = new JSONObject(response.toString());

                String username = jsonObject.getString("username");
                String fullname = jsonObject.getString("fullname");
                int userid = jsonObject.getInt("userid");

                System.out.println("User:\n");

                System.out.println("Username: " + username);
                System.out.println("Fullname: " + fullname);
                System.out.println("User ID: " + userid);

                return response.toString();
            } else {
                return "GET request failed. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception occurred: " + e.getMessage();
        }
    }

    public String getCourses(String token) {
        String baseUrl = "http://31.128.42.58:8080/get_courses/";
        String urlString = baseUrl + token;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                System.out.println("Courses:\n");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject course = jsonArray.getJSONObject(i);

                    int id = course.getInt("id");
                    String fullname = course.getString("fullname");
                    String[] parts = fullname.split("\\|");
                    String courseName = parts[0].trim();
                    String teacher = parts[1].trim();
                    System.out.println("ID: " + id);
                    System.out.println("Course Name: " + courseName);
                    System.out.println("Teacher: " + teacher);
                    System.out.println("➽─────────────────────────────────────────❥");
                }

                return response.toString();
            } else {
                return "GET request failed. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception occurred: " + e.getMessage();
        }
    }


    public String getDeadlines(String token) {
        String baseUrl = "http://31.128.42.58:8080/get_deadlines/";
        String urlString = baseUrl + token;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                System.out.println("Deadlines:\n");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject deadline = jsonArray.getJSONObject(i);

                    String name = deadline.getString("name");
                    String formattedtime = deadline.getString("formattedtime");
                    String coursename = deadline.getString("coursename");

                    System.out.println("Name: " + name);
                    System.out.println("Formatted Time: " + formattedtime);
                    System.out.println("Course Name: " + coursename);
                    System.out.println("➽─────────────────────────────────────────❥");

                }

                return response.toString();
            } else {
                return "GET request failed. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception occurred: " + e.getMessage();
        }
    }

    public String getGrades(String token, int courseId) {
        String baseUrl = "http://31.128.42.58:8080/get_grades/";
        String urlString = baseUrl + token;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject course = jsonArray.getJSONObject(i);
                    String fullname = course.getString("coursename");
                    int course_id = course.getInt("courseid");
//                    String[] parts = fullname.split("\\|");
//                    String courseName1 = parts[0].trim();

                    if (course_id == courseId) {
                        System.out.println("Course: " + fullname);
                       // System.out.println("Course ID: " + course.getInt("courseid"));
                        System.out.println(".・。.・゜✭・.・✫・゜・。..・。.・゜✭・.・✫・゜・。.");
                        JSONArray gradeItems = course.getJSONArray("gradeitems");

                        for (int j = 0; j < gradeItems.length(); j++) {
                            JSONObject gradeItem = gradeItems.getJSONObject(j);
                            if ((!Objects.equals(gradeItem.getString("percentageformatted"), "-") && (!Objects.equals(gradeItem.getString("percentageformatted"), "0.00 %")))) {
                                System.out.println("Item Name: " + gradeItem.getString("itemname"));
                                System.out.println("Percentage: " + gradeItem.getString("percentageformatted"));
                            }
                        }

                        return course.toString();
                    }
                }

                return "Course not found.";
            } else {
                return "GET request failed. Response Code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception occurred: " + e.getMessage();
        }
    }
}