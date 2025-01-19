package repository.interfaces;
import models.User;
import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUser(String login);
    List <User> getAllUsers();
    boolean deleteUserByLogin(String login);
   boolean updateUser(User user);
   boolean deleteUser(String login);
   boolean createToken(String token, String login);
}
