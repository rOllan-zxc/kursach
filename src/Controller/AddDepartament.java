package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDepartament {

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/AddDepartament.fxml"));
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
    private TextField Text_Name;

    @FXML
    private TextField Text_Numb;

    @FXML
    private Button But_add;

    @FXML
    private Label LabelError;

    @FXML
    void initialize() {
       But_add.setOnAction(actionEvent -> {
           try {
               if(Text_Name.getText().equals("")||Text_Numb.getText().equals(""))
               {
                   LabelError.setTextFill(Color.RED);
                   LabelError.setText("Введите все поля");
               }
               else {
                   Client.getInstance().getOut().writeObject("Добавить отдел");
                   Client.getInstance().getOut().writeObject(Text_Name.getText());
                   Client.getInstance().getOut().writeObject(Text_Numb.getText());
                   LabelError.setTextFill(Color.GREEN);
                   LabelError.setText("Успешно добавлено");
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
    }
}
