package Controller;

import Client.Client;
import Client.Departament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Table_Departament {

    ObservableList<Departament>List= FXCollections.observableArrayList();
    private void Initialize() throws IOException, ClassNotFoundException {
        int a=(int) Client.getInstance().getIn().readObject();
        for(int i=0;i<a;i++)
        {
            Departament departament=new Departament();
            departament.setNameDepartment((String)Client.getInstance().getIn().readObject());
            departament.setNumberEmploye((int)Client.getInstance().getIn().readObject());
            departament.setCash((double)Client.getInstance().getIn().readObject());
            List.add(departament);
        }
        ColumDep.setCellValueFactory(new PropertyValueFactory<Departament,String >("NameDepartment"));
        ColumNumb.setCellValueFactory(new PropertyValueFactory<Departament,Integer>("NumberEmploye"));
        ColumCash.setCellValueFactory(new PropertyValueFactory<Departament,Double>("cash"));
        Table.setItems(List);
    }

    public void Start()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/TableDepartament.fxml"));
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
    private TableView<Departament> Table;

    @FXML
    private TableColumn<Departament, String> ColumDep;

    @FXML
    private TableColumn<Departament, Integer> ColumNumb;

    @FXML
    private TableColumn<Departament, Double> ColumCash;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
    Initialize();
    }
}
