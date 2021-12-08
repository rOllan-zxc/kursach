package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Statistic {

    private void Initialize() throws IOException, ClassNotFoundException {
        Label_numbersEmploye.setText((String) Client.getInstance().getIn().readObject());
        LabelPAy.setText((String) Client.getInstance().getIn().readObject());
        LabelCash.setText((String) Client.getInstance().getIn().readObject());
        labelNumberDepart.setText((String) Client.getInstance().getIn().readObject());
    }

    @FXML
    private Label LabelAnswer;

    private void Write() throws IOException {
        File file=new File("Отчет.txt");
        file.createNewFile();
        PrintWriter write = new PrintWriter(file);
        write.println("Кол-во сотрудников : "+Label_numbersEmploye.getText());
        write.println("Cумма затрат : "+ LabelPAy.getText());
        write.println("Итоговая сумма на счете :  "+LabelCash.getText());
        write.println("Кол-во отделов : "+LabelCash.getText());
        write.close();
        LabelAnswer.setTextFill(Color.GREEN);
        LabelAnswer.setText("Успешно записана в файл");
    }

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/Statistic.fxml"));
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
    private Label Label_numbersEmploye;

    @FXML
    private Label LabelPAy;

    @FXML
    private Label LabelCash;

    @FXML
    private Label labelNumberDepart;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Initialize();
        int a=(int )Client.getInstance().getIn().readObject();
        if(a==1)
        {
            Write();
        }
    }
}
