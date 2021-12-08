package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePersonalInfo {

    private void Initialize() throws IOException, ClassNotFoundException {
        name.setText((String) Client.getInstance().getIn().readObject());
        second.setText((String) Client.getInstance().getIn().readObject());
        patr.setText((String) Client.getInstance().getIn().readObject());
        tel.setText((String) Client.getInstance().getIn().readObject());
        pas_info.setText((String) Client.getInstance().getIn().readObject());
        log.setText((String) Client.getInstance().getIn().readObject());
        pas.setText((String) Client.getInstance().getIn().readObject());
    }

    void Change() throws IOException {
                Client.getInstance().getOut().writeObject(name.getText());
                Client.getInstance().getOut().writeObject(second.getText());
                Client.getInstance().getOut().writeObject(patr.getText());
                Client.getInstance().getOut().writeObject(tel.getText());
                Client.getInstance().getOut().writeObject(pas_info.getText());
                Client.getInstance().getOut().writeObject(log.getText());
                Client.getInstance().getOut().writeObject(pas.getText());

    }

    public void StartChangeMenu()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/ChangePersonalInfo.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField second;

    @FXML
    private TextField patr;

    @FXML
    private TextField tel;

    @FXML
    private TextField pas_info;

    @FXML
    private TextField log;

    @FXML
    private TextField pas;

    @FXML
    private Button Change;

    @FXML
    private Label LabelError;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
       Initialize();
       Change.setOnAction(actionEvent -> {
           try {
               if(name.getText().equals("")||second.getText().equals("")||patr.getText().equals("")||tel.getText().equals("")
                       ||log.getText().equals("")||pas_info.getText().equals("")||pas.getText().equals(""))
               {
                   LabelError.setText("Не все поля заполнены !");
               }
               else {
                   Client.getInstance().getOut().writeObject("Изменить");
                   Change();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
    }
}
