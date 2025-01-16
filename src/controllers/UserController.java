package controllers;

import controllers.intefaces.IUserController;
import models.User;
import repository.interfaces.IUserRepository;

public class UserController implements IUserController {

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String login, String password) {
        User user = new User(login, password);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");
    }

    @Override
    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }
}
