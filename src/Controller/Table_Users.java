package Controller;

import Client.Client;
import Client.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Table_Users {

    ObservableList<Users> List= FXCollections.observableArrayList();
    ObservableList<String>StringList = FXCollections.observableArrayList();
    ObservableList<Users>AdminList=FXCollections.observableArrayList();
    ObservableList<Users>UserList=FXCollections.observableArrayList();

    private void Initialize() throws IOException, ClassNotFoundException {
        int n=(int) Client.getInstance().getIn().readObject();
        for(int i=0;i<n;i++) {
            Users user = new Users();
            user.setId((Integer) Client.getInstance().getIn().readObject());
            user.setName((String) Client.getInstance().getIn().readObject());
            user.setSecondName((String) Client.getInstance().getIn().readObject());
            user.setPatronymic((String) Client.getInstance().getIn().readObject());
            user.setDate((String) Client.getInstance().getIn().readObject());
            user.setTel((String) Client.getInstance().getIn().readObject());
            user.setPas_info((String) Client.getInstance().getIn().readObject());
            user.setLogin((String) Client.getInstance().getIn().readObject());
            user.setPassword((String) Client.getInstance().getIn().readObject());
            user.setStatus((String) Client.getInstance().getIn().readObject());
            StringList.add(user.getLogin());
            List.add(user);
            if (user.getStatus().equals("User")) {
                UserList.add(user);
            }
            if (user.getStatus().equals("Admin")) {
                AdminList.add(user);
            }
        }
        ColumId.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        ColumName.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        ColumSecondName.setCellValueFactory(new PropertyValueFactory<Users,String>("id"));
        ColumPatr.setCellValueFactory(new PropertyValueFactory<Users,String>("patronymic"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Users,String>("date"));
        ColumTel.setCellValueFactory(new PropertyValueFactory<Users,String>("tel"));
        ColumPas_info.setCellValueFactory(new PropertyValueFactory<Users,String>("pas_info"));
        ColumLog.setCellValueFactory(new PropertyValueFactory<Users,String>("login"));
        ColumPass.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        ColumStatus.setCellValueFactory(new PropertyValueFactory<Users,String>("status"));
        Table.setItems(List);
    }
    private void Filtration()
    {
    if(Radio_All.isSelected())
    {
        ColumId.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        ColumName.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        ColumSecondName.setCellValueFactory(new PropertyValueFactory<Users,String>("secondName"));
        ColumPatr.setCellValueFactory(new PropertyValueFactory<Users,String>("patronymic"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Users,String>("date"));
        ColumTel.setCellValueFactory(new PropertyValueFactory<Users,String>("tel"));
        ColumPas_info.setCellValueFactory(new PropertyValueFactory<Users,String>("pas_info"));
        ColumLog.setCellValueFactory(new PropertyValueFactory<Users,String>("login"));
        ColumPass.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        ColumStatus.setCellValueFactory(new PropertyValueFactory<Users,String>("status"));
        Table.setItems(List);
    }
        if(Radio_admin.isSelected())
    {
        ColumId.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        ColumName.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        ColumSecondName.setCellValueFactory(new PropertyValueFactory<Users,String>("secondName"));
        ColumPatr.setCellValueFactory(new PropertyValueFactory<Users,String>("patronymic"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Users,String>("date"));
        ColumTel.setCellValueFactory(new PropertyValueFactory<Users,String>("tel"));
        ColumPas_info.setCellValueFactory(new PropertyValueFactory<Users,String>("pas_info"));
        ColumLog.setCellValueFactory(new PropertyValueFactory<Users,String>("login"));
        ColumPass.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        ColumStatus.setCellValueFactory(new PropertyValueFactory<Users,String>("status"));
        Table.setItems(AdminList);
    }
        if(Radio_User.isSelected())
    {

        ColumId.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        ColumName.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        ColumSecondName.setCellValueFactory(new PropertyValueFactory<Users,String>("secondName"));
        ColumPatr.setCellValueFactory(new PropertyValueFactory<Users,String>("patronymic"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Users,String>("date"));
        ColumTel.setCellValueFactory(new PropertyValueFactory<Users,String>("tel"));
        ColumPas_info.setCellValueFactory(new PropertyValueFactory<Users,String>("pas_info"));
        ColumLog.setCellValueFactory(new PropertyValueFactory<Users,String>("login"));
        ColumPass.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        ColumStatus.setCellValueFactory(new PropertyValueFactory<Users,String>("status"));
        Table.setItems(UserList);
    }
}

    private void Inchoice()
    {
        for(int i=0;i<StringList.size();i++)
        {
            Choice.getItems().add(StringList.get(i));
        }
    }

    void delAdmin()throws IOException, ClassNotFoundException {
        List = FXCollections.observableArrayList();
        UserList = FXCollections.observableArrayList();
        AdminList = FXCollections.observableArrayList();
        String msg = Choice.getValue();
        Client.getInstance().getOut().writeObject(msg);
        Client.getInstance().getOut().writeObject("User");
        Initialize();
        LabelError.setTextFill(Color.RED);
        LabelError.setText("Пользователь : " + msg + " Стал Юзером");
        LabelAdminka.setTextFill(Color.GREEN);
        LabelAdminka.setText("Успешно");
    }

    void  GetAdmin() throws IOException, ClassNotFoundException {
        List=FXCollections.observableArrayList();
        UserList=FXCollections.observableArrayList();
        AdminList=FXCollections.observableArrayList();
        String msg=Choice.getValue();
        Client.getInstance().getOut().writeObject(msg);
        Client.getInstance().getOut().writeObject("Admin");
        Initialize();
        LabelError.setTextFill(Color.GREEN);
        LabelError.setText("Пользователь : "+msg+" Стал админом");
        LabelAdminka.setTextFill(Color.GREEN);
        LabelAdminka.setText("Успешно" );
    }

    public void StartTable()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/Table_Users.fxml"));
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
    private TableColumn<Users, Integer> ColumId;

    @FXML
    private TableColumn<Users, String> ColumName;

    @FXML
    private TableColumn<Users, String> ColumSecondName;

    @FXML
    private TableColumn<Users, String> ColumPatr;

    @FXML
    private TableColumn<Users, String> ColumnDate;

    @FXML
    private TableColumn<Users, String> ColumTel;

    @FXML
    private TableColumn<Users, String> ColumPas_info;

    @FXML
    private TableColumn<Users, String> ColumLog;

    @FXML
    private TableColumn<Users, String> ColumPass;

    @FXML
    private TableColumn<Users, String> ColumStatus;

    @FXML
    private TableView<Users> Table;

    @FXML
    private ChoiceBox<String> Choice;

    @FXML
    private Button But_admin;

    @FXML
    private Button But_UnAdmin;

    @FXML
    private Label LabelAdminka;

    @FXML
    private RadioButton Radio_User;

    @FXML
    private RadioButton Radio_admin;

    @FXML
    private RadioButton Radio_All;

    @FXML
    private Label LabelError;

    @FXML
    private Button But_Update;


    ToggleGroup group = new ToggleGroup();

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Radio_All.setToggleGroup(group);
        Radio_All.setSelected(true);
        Radio_admin.setToggleGroup(group);
        Radio_User.setToggleGroup(group);
        Initialize();
        Inchoice();
        But_Update.setOnAction(actionEvent -> {
            Filtration();
        });
        But_UnAdmin.setOnAction(actionEvent -> {
            try {
                Boolean check=Choice.getSelectionModel().isEmpty();
                if(check){}
                else {
                    Client.getInstance().getOut().writeObject("Сделать Юзером");
                    delAdmin();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        But_admin.setOnAction(actionEvent -> {
            try { Boolean check=Choice.getSelectionModel().isEmpty();
                if(check){}
                else {
                    Client.getInstance().getOut().writeObject("Сделать Админом");
                    GetAdmin();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
