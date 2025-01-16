package controllers.intefaces;


public interface IUserController {
    String createUser(String login, String password);
    String getUser(int id);
}
