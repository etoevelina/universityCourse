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
        if (getUser(user.getLogin()) != null) {
            System.out.println("User with this login already exists.");
            return false;
        }
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
    public User getUser(String login) {
        String sql = "SELECT student_id, student_login, student_password, token FROM students WHERE student_login=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

        //    st.setInt(1, id);
            st.setString(1,login);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("student_id"),
                            rs.getString("student_login"),
                            rs.getString("student_password"),
                            rs.getString("token")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error in getUser: " + e.getMessage());
        }

        return null;
    }
    @Override
    public boolean deleteUserByLogin(String login) {
        String sql = "DELETE FROM students WHERE student_login=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, login);
            int affectedRows = st.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL error in deleteUserByLogin: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(String login) {
        return deleteUserByLogin(login);
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE students SET student_login=?, student_password=? WHERE student_id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getId());

            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL error in updateUser: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean createToken(String token, String login) {
        String sql = "UPDATE students SET token=? WHERE student_login=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, token);
            st.setString(2, login);

            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL error in createToken: " + e.getMessage());
        }
        return false;
    }
}
