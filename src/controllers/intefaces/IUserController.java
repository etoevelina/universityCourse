package controllers.intefaces;


import models.User;

public interface IUserController {
    String createUser(String login, String password);
    User getUser(String login);
    String getAllUsers();
    String deleteUserByLogin(String login);
    String loginUser(String login, String password);
    String deleteUser(String login);
    String changeUserData(String login, String newValue, String field); // Ensure the method signature matches
    String createToken (String token, String login);
}
