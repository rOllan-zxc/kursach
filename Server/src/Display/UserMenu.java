package Display;

import DateBaseHandler.*;
import Users.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMenu {
    List<Company> ListCompany=new ArrayList();
    List<Departament>ListDepartament=new ArrayList();
    public UserMenu() throws SQLException, ClassNotFoundException {
        DateBaseHandler base =new DateBaseHandler();
        ResultSet set=base.GetInfoDepartament();
        while(set.next())
        {
            Departament departament=new Departament();
            departament.setNameDepartment(set.getString(Const.DEPARTAMENT_NAME));
            departament.setNumberEmploye(set.getInt(Const.DEPARTAMENT_NUMBERS));
            departament.setCash(set.getDouble(Const.DEPARTAMENT_CASH));
            ListDepartament.add(departament);
        }
        set=base.GetInfoCompany();
        while (set.next())
        {
            Company company=new Company();
            company.setNameCompany(set.getString(Const.COMPANY_NAME));
            company.setCahs(set.getDouble(Const.COMPANY_CASH));
            ListCompany.add(company);

        }
    }
    private void InfoDepartament(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject( ListDepartament.size());
        for(int i=0;i<ListDepartament.size();i++)
        {
            out.writeObject(ListDepartament.get(i).getNameDepartment());
            out.writeObject(ListDepartament.get(i).getNumberEmploye());
            out.writeObject(ListDepartament.get(i).getCash());
        }
    }
    private void ChangePersonalInfo1(ObjectOutputStream out, ObjectInputStream in , Users user) throws IOException, ClassNotFoundException, SQLException {
        DateBaseHandler base=new DateBaseHandler();
        user.setName((String)in.readObject());
        user.setSecondName((String)in.readObject());
        user.setPatronymic((String)in.readObject());
        user.setTel((String)in.readObject());
        user.setPas_info((String)in.readObject());
        user.setLogin((String)in.readObject());
        user.setPassword((String)in.readObject());
        base.ChangeName(user.getName(),user.getId());
        base.ChangeSecondName(user.getSecondName(),user.getId());
        base.ChangePatr(user.getPatronymic(),user.getId());
        base.ChangePhone(user.getTel(),user.getId());
        base.ChangePas_info(user.getPas_info(),user.getId());
        base.ChangeLogin(user.getLogin(),user.getId());
        base.ChangePassword(user.getPassword(),user.getId());
        out.writeObject(user.getName());
        out.writeObject(user.getSecondName());
        out.writeObject(user.getPatronymic());
        out.writeObject(user.getTel());
        out.writeObject(user.getPas_info());
        out.writeObject(user.getLogin());
        out.writeObject(user.getPassword());

    }
    private void infoCompany(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject(ListCompany.get(0).getNameCompany());
        out.writeObject(Double.toString(ListCompany.get(0).getCahs()));
        out.writeObject(Integer.toString(ListDepartament.size()));
    }
    private void ChangePersonalInfo(ObjectOutputStream out, ObjectInputStream in , Users user) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(user.getName());
        out.writeObject(user.getSecondName());
        out.writeObject(user.getPatronymic());
        out.writeObject(user.getTel());
        out.writeObject(user.getPas_info());
        out.writeObject(user.getLogin());
        out.writeObject(user.getPassword());

    }
    public void MenuUser(ObjectOutputStream out, ObjectInputStream in , Users user) throws IOException, ClassNotFoundException, SQLException {
        out.writeObject(user.getName());
        out.writeObject(user.getSecondName());
        out.writeObject(user.getPatronymic());
        out.writeObject(user.getDate());
        out.writeObject(user.getTel());
        out.writeObject(user.getPas_info());
        out.writeObject(user.getLogin());
        out.writeObject(user.getPassword());
        out.writeObject(user.getStatus());
        MenuAutorization autorization=new MenuAutorization();
        while (true){
            String msg=(String)in.readObject();
            switch (msg)
            {
                case"Информация о Компании":infoCompany(out,in);break;
                case"Таблица отделова":InfoDepartament(out,in);break;
                case "Выход":autorization.menuAutorization(out,in);break;
                case"Изменить":ChangePersonalInfo1(out,in,user);break;
                case"Редактировать":ChangePersonalInfo(out,in,user);break;
            }
        }
    }
}
