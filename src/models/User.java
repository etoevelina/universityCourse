package models;

public class User {
    private int id;
    private String login;
    private String password;
    private String token;

    public User() {
    }

    public String getToken() {
       // System.out.println(token);
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public User(int id, String login, String password, String token) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
    }


    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        this.login = login;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", login='" + login + "' }";
    }
}