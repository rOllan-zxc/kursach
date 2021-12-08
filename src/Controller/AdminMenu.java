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

public class AdminMenu {

 private    void Initialieze() throws IOException, ClassNotFoundException {
        LabelName.setText((String) Client.getInstance().getIn().readObject());
        LabelSecondName.setText((String)Client.getInstance().getIn().readObject());
        LabelPatr.setText((String)Client.getInstance().getIn().readObject());
        LabelDate.setText((String)Client.getInstance().getIn().readObject());
        LabelPhone.setText((String)Client.getInstance().getIn().readObject());
        LabelPas_info.setText((String)Client.getInstance().getIn().readObject());
        LabelLogin.setText((String)Client.getInstance().getIn().readObject());
        LabelPassword.setText((String)Client.getInstance().getIn().readObject());
        LabelStatus.setText((String)Client.getInstance().getIn().readObject());
    }

    public void StartMenuAdmin()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/AdminMenu.fxml"));
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
    private Label LabelPassword;

    @FXML
    private Label LabelStatus;

    @FXML
    private TextField findlog;

    @FXML
    private TextField findphone;

    @FXML
    private Button But_find;

    @FXML
    private Label LabelFindLog;

    @FXML
    private Label LabelFindPhone;

    @FXML
    private Label LabelFindName;

    @FXML
    private Label LabelFindSecondName;

    @FXML
    private Label LabelFindPatr;

    @FXML
    private Label LabelFindDate;

    @FXML
    private Label LabelFindPas_info;

    @FXML
    private Label LabelFindPass;

    @FXML
    private Label LabelFindStatus;

    @FXML
    private Button But_infoDepartment;

    @FXML
    private Button But_infoUser;

    @FXML
    private Button But_statistic;

    @FXML
    private Button But_replanis;

    @FXML
    private Button But_getSalary;

    @FXML
    private Button But_infoCompany;

    @FXML
    private Button But_exit;

    @FXML
    private Button But_addDepartmen;

    @FXML
    private Button But_Doc;

    @FXML
    private Label ErrorFind;

    @FXML
    private Label LabelFindLogin;

    @FXML
    private Label LabelFindTel;

    @FXML
    private Button But_Pay_departamnet;

    @FXML
    private Button But_reg;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Initialieze();
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
                LabelPassword.setText((String)Client.getInstance().getIn().readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        But_find.setOnAction(actionEvent -> {
            if(findlog.getText().equals("")||findphone.getText().equals(""))
            {
                if (findlog.getText().equals("")) {
                    LabelFindLogin.setTextFill(Color.RED);
                    LabelFindLogin.setText("Введите логин");
                }
                if(findphone.getText().equals(""))
                {
                    LabelFindTel.setTextFill(Color.RED);
                    LabelFindTel.setText("Введите номер");
                }
            }
            else
                {
                    try {
                        Client.getInstance().getOut().writeObject("Найти");
                        Client.getInstance().getOut().writeObject(findlog.getText());
                        Client.getInstance().getOut().writeObject(findphone.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    LabelFindTel.setText("");
                        LabelFindLogin.setText("");
                    try {
                        String msg=(String)Client.getInstance().getIn().readObject();
                        if(msg.equals("Найден")) {
                            LabelFindName.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindSecondName.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindPatr.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindDate.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindPhone.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindPas_info.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindLog.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindPass.setText((String) Client.getInstance().getIn().readObject());
                            LabelFindStatus.setText((String) Client.getInstance().getIn().readObject());
                        }
                        else
                            {
                                ErrorFind.setTextFill(Color.RED);
                                ErrorFind.setText("Неудалось найти");
                            }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
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
        But_infoUser.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Информация о Пользователях");
                Table_Users table_users=new Table_Users();
                table_users.StartTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        But_addDepartmen.setOnAction(actionEvent -> {

                AddDepartament addDepartament=new AddDepartament();
                addDepartament.Start();

        });
        But_infoDepartment.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Таблица отделова");
                Table_Departament table_departament=new Table_Departament();
                table_departament.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        But_Pay_departamnet.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Перевод отделу");
                Pay_Departament pay_departament=new Pay_Departament();
                pay_departament.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        But_reg.setOnAction(actionEvent -> {

            try {
                Client.getInstance().getOut().writeObject("Регистрация компании");
                int a=(int)Client.getInstance().getIn().readObject();
                if(a==0) {
                    reg r = new reg();
                    r.StartMenu();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        But_replanis.setOnAction(actionEvent -> {
            ReplenishCompany replenishCompany=new ReplenishCompany();
            replenishCompany.Start();
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
        But_statistic.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Статистика");
                Statistic stat=new Statistic();
                stat.Start();
            } catch (IOException  e) {
                e.printStackTrace();
            }
        });
        But_Doc.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Печать");
                Statistic stat=new Statistic();
                stat.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            });
        But_getSalary.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject("Оплата Сотруднику");
                getSalary get=new getSalary();
                get.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
