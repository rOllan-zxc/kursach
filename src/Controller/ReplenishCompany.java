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

public class ReplenishCompany {

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/ReplenishCompany.fxml"));
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
    private TextField Text_Sum;

    @FXML
    private Button But_replenish;

    @FXML
    private Label LabelReplenish;

    @FXML
    void initialize() {
        But_replenish.setOnAction(actionEvent -> {
            if(Text_Sum.getText().equals(""))
            {
                LabelReplenish.setTextFill(Color.RED);
                LabelReplenish.setText("Введите сумму");
            }
            else
                {
                    try {
                        Client.getInstance().getOut().writeObject("Пополнить");
                        Client.getInstance().getOut().writeObject(Text_Sum.getText());
                        LabelReplenish.setTextFill(Color.GREEN);
                        LabelReplenish.setText("Успешно");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });
    }
}
