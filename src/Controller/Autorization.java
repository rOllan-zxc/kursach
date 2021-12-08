package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Autorization {


    private void autorization() throws IOException, ClassNotFoundException {
        if (TextLog.getText().equals("") && TextPas.getText().equals("")) {
            if (TextLog.getText().equals("")) {
                LabelErrorLog.setTextFill(Color.RED);
                LabelErrorLog.setText("Введите логин");
            }
            if (TextPas.getText().equals("")) {
                LabelErrorPas.setTextFill(Color.RED);
                LabelErrorPas.setText("Введите пароль");
            }
        } else {
            LabelErrorPas.setText("");
            LabelErrorLog.setText("");
            Client.getInstance().getOut().writeObject(TextLog.getText());
            Client.getInstance().getOut().writeObject(TextPas.getText());
            String msg = (String) Client.getInstance().getIn().readObject();
            if (msg.equals("Успешно")) {
                But_enter.getScene().getWindow().hide();
                String status = (String) Client.getInstance().getIn().readObject();
                if (status.equals("UserMenu")) {
                    UserMenu userMenu=new UserMenu();
                    userMenu.Start();
                }
                if (status.equals("AdminMenu")) {
                    AdminMenu adminMenu=new AdminMenu();
                    adminMenu.StartMenuAdmin();
                }
            }
            if (msg.equals("Не найдено")) {
                LabelError.setText("Неудалось войти");
            }
        }
    }

    public void StartAutorization() throws IOException {
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("/FXML/Autorization.fxml"));
    loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label LabelError;

    @FXML
    private TextField TextLog;

    @FXML
    private PasswordField TextPas;

    @FXML
    private Button But_enter;

    @FXML
    private Button But_registration;

    @FXML
    private Label LabelErrorLog;

    @FXML
    private Label LabelErrorPas;

    @FXML
    void initialize() {
        But_registration.setOnAction(actionEvent -> {
            try {
                But_registration.getScene().getWindow().hide();
                Client.getInstance().getOut().writeObject("Регистрация");
                Registration registration = new Registration();
                registration.StartRegistration();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        But_enter.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Авторизицая");
                autorization();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
