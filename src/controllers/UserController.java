package controllers;

import controllers.intefaces.IUserController;
import models.User;
import repository.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public String createUser(String login, String password) {
        User user = new User(login, password);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");
    }

    public User getUser(String login) {
        User user = repo.getUser(login);

        if (user == null) {
            throw new IllegalArgumentException("User was not found!");
        }

        return user;
    }

    public String createToken(String token, String login) {
        boolean created = repo.createToken(token, login);

        return (created ? "Token was saved!" : "Token saving was failed!");
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user).append("\n");
        }

        return response.toString();
    }

    public String deleteUserByLogin(String login) {
        boolean deleted = repo.deleteUserByLogin(login);

        return (deleted ? "User was deleted!" : "User deletion failed!");
    }
    public String loginUser(String login, String password) {
        User user = repo.getUser(login);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful!";
        }
        return "Invalid login or password!";
    }

    @Override
    public String deleteUser(String login) {
        boolean deleted = repo.deleteUserByLogin(login);
        return deleted ? "User was deleted!" : "User deletion failed!";
    }

    @Override
    public String changeUserData(String login, String newValue, String field) {
        User user = repo.getUser(login);
        if (user == null) {
            return "User not found!";
        }

        if ("login".equals(field)) {
            if (repo.getUser(newValue) != null) {
                return "Login already exists!";
            }
            user.setLogin(newValue);
        } else if ("password".equals(field)) {
            user.setPassword(newValue);
        } else {
            return "Invalid field!";
        }

        boolean updated = repo.updateUser(user);
        return updated ? "User data updated successfully!" : "User data update failed!";
    }
}
