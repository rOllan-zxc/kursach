import Client.Client;
import javafx.application.Application;
import javafx.stage.Stage;
public class MainClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Client.getInstance().connection();


    }


    public static void main(String[] args) {
        launch(args);
    }
}