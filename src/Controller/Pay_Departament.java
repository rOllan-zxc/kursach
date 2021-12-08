package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Pay_Departament {

    void Initialize() throws IOException, ClassNotFoundException {
        int a=(int) Client.getInstance().getIn().readObject();
        for(int i=0;i<a;i++)
        {
            Choice.getItems().add((String)Client.getInstance().getIn().readObject());
        }
    }

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/Pay_Departament.fxml"));
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
    private ChoiceBox<String> Choice;

    @FXML
    private TextField Text_Sum;

    @FXML
    private Label LabelError;

    @FXML
    private Button But_pay;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
            Initialize();
            But_pay.setOnAction(actionEvent -> {
                if(Text_Sum.getText().equals(""))
                {
                    LabelError.setTextFill(Color.RED);
                    LabelError.setText("Введите сумму");
                }
                else {
                    try {
                        Client.getInstance().getOut().writeObject("Перевел Отделу");
                        Client.getInstance().getOut().writeObject(Double.parseDouble(Text_Sum.getText()));
                        Client.getInstance().getOut().writeObject(Choice.getValue());
                        String msg =(String)Client.getInstance().getIn().readObject();
                        if(msg.equals("Успешно")) {
                            LabelError.setTextFill(Color.GREEN);
                            LabelError.setText("Успешно");
                        }
                        else
                            {
                                LabelError.setTextFill(Color.RED);
                                LabelError.setText("Недостаточно средств");
                            }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}
