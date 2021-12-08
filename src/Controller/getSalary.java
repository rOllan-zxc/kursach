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

public class getSalary {

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/getSalary.fxml"));
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

    void Initialize() throws IOException, ClassNotFoundException {
        int a=(int)Client.getInstance().getIn().readObject();
        for(int i=0;i<a;i++)
        {
            Choice.getItems().add((String)Client.getInstance().getIn().readObject());
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> Choice;

    @FXML
    private TextField TextSum;

    @FXML
    private Button But_go;

    @FXML
    private Label LabelAnswer;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Initialize();
        But_go.setOnAction(actionEvent -> {
            Boolean check=Choice.getSelectionModel().isEmpty();
            if(TextSum.getText().equals("")||check)
            {
                LabelAnswer.setTextFill(Color.RED);
                LabelAnswer.setText("Не все поля заполнены");
            }
            else
                {
                    LabelAnswer.setText("");
                    try {
                        Client.getInstance().getOut().writeObject("Зачислить сотуднику");
                        Client.getInstance().getOut().writeObject(Choice.getValue());
                        Client.getInstance().getOut().writeObject(TextSum.getText());
                        LabelAnswer.setTextFill(Color.GREEN);
                        LabelAnswer.setText("Успешно");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });
    }
}
