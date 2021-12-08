package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenu {

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/UserMenu.fxml"));
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
        LabelName.setText((String) Client.getInstance().getIn().readObject());
        LabelSecondName.setText((String)Client.getInstance().getIn().readObject());
        LabelPatr.setText((String)Client.getInstance().getIn().readObject());
        LabelDate.setText((String)Client.getInstance().getIn().readObject());
        LabelPhone.setText((String)Client.getInstance().getIn().readObject());
        LabelPas_info.setText((String)Client.getInstance().getIn().readObject());
        LabelLogin.setText((String)Client.getInstance().getIn().readObject());
        LabelPass.setText((String)Client.getInstance().getIn().readObject());
        LabelStatus.setText((String)Client.getInstance().getIn().readObject());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button But_change;

    @FXML
    private Label LabelName;

    @FXML
    private Label LabelSecondName;

    @FXML
    private Label LabelPatr;

    @FXML
    private Label LabelDate;

    @FXML
    private Label LabelPhone;

    @FXML
    private Label LabelPas_info;

    @FXML
    private Label LabelLogin;

    @FXML
    private Label LabelPass;

    @FXML
    private Label LabelStatus;

    @FXML
    private Button But_exit;

    @FXML
    private Button But_infoCompany;

    @FXML
    private Button But_infoDepart;

    @FXML
    private Button But_GetSalery;

    @FXML
    private Button History_salary;

    @FXML
    private Button But_writeHistory;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Initialize();
        But_change.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Редактировать");
                ChangePersonalInfo changePersonalInfo=new ChangePersonalInfo();
                changePersonalInfo.StartChangeMenu();
                LabelName.setText((String) Client.getInstance().getIn().readObject());
                LabelSecondName.setText((String)Client.getInstance().getIn().readObject());
                LabelPatr.setText((String)Client.getInstance().getIn().readObject());
                LabelPhone.setText((String)Client.getInstance().getIn().readObject());
                LabelPas_info.setText((String)Client.getInstance().getIn().readObject());
                LabelLogin.setText((String)Client.getInstance().getIn().readObject());
                LabelPass.setText((String)Client.getInstance().getIn().readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        But_exit.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Выход");
                But_exit.getScene().getWindow().hide();
                Autorization autorization=new Autorization();
                autorization.StartAutorization();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        But_infoDepart.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Таблица отделов");
                Table_Departament table_departament=new Table_Departament();
                table_departament.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        But_infoCompany.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Информация о Компании");
                InfoCompany infoCompany=new InfoCompany();
                infoCompany.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
