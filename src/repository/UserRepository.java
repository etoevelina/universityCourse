package repository;

import data.interfaces.IDB;
import models.User;
import repository.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private final IDB db; // Dependency Injection

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO students(student_login, student_password) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("SQL error in createUser: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT student_id, student_login FROM students";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("student_id"),
                        rs.getString("student_login")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL error in getAllUsers: " + e.getMessage());
        }

        return users;
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT student_id, student_login, student_password FROM students WHERE student_id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("student_id"),
                            rs.getString("student_login"),
                            rs.getString("student_password")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error in getUser: " + e.getMessage());
        }

        return null;
    }
}
