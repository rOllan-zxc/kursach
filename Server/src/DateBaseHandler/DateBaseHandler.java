package DateBaseHandler;
import Users.*;
import Users.Users;

//import javax.sql.ConnectionPoolDataSource;
import java.sql.*;

import static com.mysql.cj.conf.PropertyKey.cacheDefaultTimeZone;

public class DateBaseHandler extends Configs{
    Connection dbconnection;
    public Connection getDbconnection()throws ClassNotFoundException,SQLException
    {
        String ConectionString ="jdbc:mysql://"+dbHost+":"+
                dbport+"/"+dbName+"?serverTimezone=Europe/Moscow";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(ConectionString);
        dbconnection=DriverManager.getConnection(ConectionString, dbUsers,dbPass);
        return dbconnection;
    }
    public void RegistrationUser(Users user) throws SQLException, ClassNotFoundException {
        String insert ="INSERT INTO "+Const.USERS_TABLE+"("+
                Const.USERS_NAME+","+Const.USERS_SECONDNAME+","+Const.USERS_PATRONYMIC +","+
                Const.USERS_DATEBIRTH+","+Const.USERS_PAS_INFO+","+Const.USERS_PHONE+","+Const.USERS_LOGIN+","+
                Const.USERS_PASSWORD+","+Const.USERS_STATUS+")"
                +"VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement state =getDbconnection().prepareStatement(insert);
        String name=user.getName();
        state.setString(1,name);
        state.setString(2,user.getSecondName());
        state.setString(3,user.getPatronymic());
        state.setString(4,user.getDate());
        state.setString(5,user.getPas_info());
        state.setString(6,user.getTel());
        state.setString(7,user.getLogin());
        state.setString(8,user.getPassword());
        state.setString(9,user.getStatus());
        state.executeUpdate();
    }
    public void Registration(Departament dep) throws SQLException, ClassNotFoundException {
        String insert ="INSERT INTO "+Const.DEPARTAMENT_TABLE+"("+
                Const.DEPARTAMENT_NAME+","+Const.DEPARTAMENT_NUMBERS+","+Const.DEPARTAMENT_CASH +")"
                +"VALUES(?,?,?)";
        PreparedStatement state =getDbconnection().prepareStatement(insert);
        state.setString(1,dep.getNameDepartment());
        state.setInt(2,dep.getNumberEmploye());
        state.setDouble(3,dep.getCash());
        state.executeUpdate();
    }
    public void Registration(Company comp) throws SQLException, ClassNotFoundException {
        String insert ="INSERT INTO "+Const.COMPANY_TABLE+"("+
                Const.COMPANY_NAME+","+Const.COMPANY_CASH+")"
                +"VALUES(?,?)";
        PreparedStatement state =getDbconnection().prepareStatement(insert);
        state.setString(1,comp.getNameCompany());
        state.setDouble(2,comp.getCahs());
        state.executeUpdate();
    }
    public void RegistrationHistory(History history) throws SQLException, ClassNotFoundException {
        String insert ="INSERT INTO "+Const.HISTORY_TABLE+"("+
                Const.USERS_NAME+","+Const.USERS_LOGIN+","+Const.HISTORY_SUM+")"
                +"VALUES(?,?,?)";
        PreparedStatement state =getDbconnection().prepareStatement(insert);
        state.setString(1,history.getName());
        state.setString(2,history.getLog());
        state.setDouble(3,history.getSum());
        state.executeUpdate();
    }
    public ResultSet GetInfoCompany() throws SQLException, ClassNotFoundException {
        Connection connection = getDbconnection();
        String query = "select * from "+Const.COMPANY_TABLE ;
        Statement statement =connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        return set;
    }
    public ResultSet GetInfoHistory() throws SQLException, ClassNotFoundException {
        Connection connection = getDbconnection();
        String query = "select * from "+Const.HISTORY_TABLE ;
        Statement statement =connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        return set;
    }
    public ResultSet GetInfoUsers() throws SQLException, ClassNotFoundException {
        Connection connection = getDbconnection();
        String query = "select * from "+Const.USERS_TABLE ;
        Statement statement =connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        return set;
    }
    public ResultSet GetInfoDepartament() throws SQLException, ClassNotFoundException {
        Connection connection = getDbconnection();
        String query = "select * from "+Const.DEPARTAMENT_TABLE ;
        Statement statement =connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        return set;
    }
    public void ChangeName(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_NAME+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangeCashDepart(String name ,double cash) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.DEPARTAMENT_TABLE+" SET "+Const.DEPARTAMENT_CASH+"=?"+" WHERE "
                +Const.DEPARTAMENT_NAME+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setDouble(1,cash);
        statement.setString(2,name);
        statement.executeUpdate();
    }
    public void ChangeCashCompany(Double name ,String ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.COMPANY_TABLE+" SET "+Const.COMPANY_CASH+"=?"+" WHERE "
                +Const.COMPANY_NAME+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setDouble(1,name);
        statement.setString(2,ID);
        statement.executeUpdate();
    }
    public void ChangeSecondName(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_SECONDNAME+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangePatr(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_PATRONYMIC+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangePhone(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_PHONE+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangeLogin(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_LOGIN+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangePassword(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_PASSWORD+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangePas_info(String name ,int ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_PAS_INFO+"=?"+" WHERE "
                +Const.USERS_ID+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2,ID);
        statement.executeUpdate();
    }
    public void ChangeStatus(String name ,String ID) throws SQLException, ClassNotFoundException {
        String query="UPDATE "+Const.USERS_TABLE+" SET "+Const.USERS_STATUS+"=?"+" WHERE "
                +Const.USERS_LOGIN+"=?";
        PreparedStatement statement=getDbconnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setString(2,ID);
        statement.executeUpdate();
    }
}
