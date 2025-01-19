import controllers.UserController;
import controllers.intefaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repository.UserRepository;
import repository.interfaces.IUserRepository;
import http.HttpManager;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres");
        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);
        MyApplication app = new MyApplication(controller);
        app.start();

    }
}

