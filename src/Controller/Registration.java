package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Registration {

    public void StartRegistration()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/Registration.fxml"));
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

    public void registrationUser() throws IOException {
        Client.getInstance().getOut().writeObject(TextName.getText());
        Client.getInstance().getOut().writeObject(TextSecondName.getText());
        Client.getInstance().getOut().writeObject(TextPatr.getText());
        Client.getInstance().getOut().writeObject(TextDate.getValue().toString());
        Client.getInstance().getOut().writeObject(TextPhone.getText());
        Client.getInstance().getOut().writeObject(TextPas_info.getText());
        Client.getInstance().getOut().writeObject(TextLog.getText());
        Client.getInstance().getOut().writeObject(TextPas.getText());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextName;

    @FXML
    private TextField TextSecondName;

    @FXML
    private TextField TextPatr;

    @FXML
    private TextField TextPhone;

    @FXML
    private TextField TextPas_info;

    @FXML
    private DatePicker TextDate;

    @FXML
    private TextField TextLog;

    @FXML
    private TextField TextPas;

    @FXML
    private Button ButRegistration;

    @FXML
    private Button But_exit;

    @FXML
    private Label LabelAnswer;

    @FXML
    void initialize() {
        But_exit.setOnAction(actionEvent -> {
            But_exit.getScene().getWindow().hide();
            try {
                String ex="Выход";
                Client.getInstance().getOut().writeObject(ex);
                LabelAnswer.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Autorization.fxml"));
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
        });
        ButRegistration.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Зарегистрировать");
                registrationUser();
                LabelAnswer.setText("Регистрация прошла успешно");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
