package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoCompany {

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/InfoCompany.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

    private void Initialize() throws IOException, ClassNotFoundException {
        Name.setText((String) Client.getInstance().getIn().readObject());
        cash.setText((String) Client.getInstance().getIn().readObject()+" Руб");
        dapart.setText((String) Client.getInstance().getIn().readObject());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Name;

    @FXML
    private Label cash;

    @FXML
    private Label dapart;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Initialize();
    }
}
