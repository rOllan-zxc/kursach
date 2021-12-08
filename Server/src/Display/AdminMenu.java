package Display;

import DateBaseHandler.*;
import Users.*;
//import com.mysql.cj.xdevapi.Client;

import javax.swing.*;
import javax.swing.event.CaretListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminMenu {
    List<Company> ListCompany=new ArrayList();
    List<Users> ListUser=new ArrayList();
    List<Departament>ListDepartament=new ArrayList();
    List<History>ListHistory=new ArrayList();
    AdminMenu() throws SQLException, ClassNotFoundException {
        DateBaseHandler base= new DateBaseHandler();
        ResultSet set=base.GetInfoUsers();
        while(set.next())
        {
            Users user=new Users();
            user.setId(set.getInt(Const.USERS_ID));
            user.setName(set.getString(Const.USERS_NAME));
            user.setSecondName(set.getString(Const.USERS_SECONDNAME));
            user.setPatronymic(set.getString(Const.USERS_PATRONYMIC));
            user.setDate(set.getString(Const.USERS_DATEBIRTH));
            user.setPas_info(set.getString(Const.USERS_PAS_INFO));
            user.setTel(set.getString(Const.USERS_PHONE));
            user.setLogin(set.getString(Const.USERS_LOGIN));
            user.setPassword(set.getString(Const.USERS_PASSWORD));
            user.setStatus(set.getString(Const.USERS_STATUS));
            ListUser.add(user);
        }
        set=base.GetInfoDepartament();
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
        set=base.GetInfoHistory();
        while (set.next())
        {
            History history=new History();
            history.setName(set.getString(Const.USERS_NAME));
            history.setLog(set.getString(Const.USERS_LOGIN));
            history.setSum(set.getDouble(Const.HISTORY_SUM));
            ListHistory.add(history);
        }
    }
    private void FindUser(ObjectOutputStream out, ObjectInputStream in , Users user) throws IOException, ClassNotFoundException {
        String log=(String)in.readObject();
        String tel=(String)in.readObject();
        int check=0;
        for(int i=0;i<ListUser.size();i++)
        {
            if(ListUser.get(i).getTel().equals(tel)&&ListUser.get(i).getLogin().equals(log))
            {
                check++;
                out.writeObject("Найден");
                out.writeObject(ListUser.get(i).getName());
                out.writeObject(ListUser.get(i).getSecondName());
                out.writeObject(ListUser.get(i).getPatronymic());
                out.writeObject(ListUser.get(i).getDate());
                out.writeObject(ListUser.get(i).getTel());
                out.writeObject(ListUser.get(i).getPas_info());
                out.writeObject(ListUser.get(i).getLogin());
                out.writeObject(ListUser.get(i).getPassword());
                out.writeObject(ListUser.get(i).getStatus());
                i=ListUser.size();
            }
        }
        if(check==0)
        {
            out.writeObject("Не найдено");
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
        ListUser=new ArrayList();
        DateBaseHandler base1= new DateBaseHandler();
        ResultSet set=base1.GetInfoUsers();
        while(set.next())
        {
            Users users=new Users();
            users.setId(set.getInt(Const.USERS_ID));
            users.setName(set.getString(Const.USERS_NAME));
            users.setSecondName(set.getString(Const.USERS_SECONDNAME));
            users.setPatronymic(set.getString(Const.USERS_PATRONYMIC));
            users.setDate(set.getString(Const.USERS_DATEBIRTH));
            users.setPas_info(set.getString(Const.USERS_PAS_INFO));
            users.setTel(set.getString(Const.USERS_PHONE));
            users.setLogin(set.getString(Const.USERS_LOGIN));
            users.setPassword(set.getString(Const.USERS_PASSWORD));
            users.setStatus(set.getString(Const.USERS_STATUS));
            ListUser.add(user);
        }
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
    private void AddAndDelAdmin(ObjectOutputStream out, ObjectInputStream in) throws SQLException, IOException, ClassNotFoundException {
        String msg =(String) in.readObject();
        String status=(String)in.readObject();
        DateBaseHandler base =new DateBaseHandler();
        base.ChangeStatus(status,msg);
        ResultSet set = base.GetInfoUsers();
        ListUser= new ArrayList();
        while (set.next()) {
            Users user = new Users();
            user.setId(set.getInt(Const.USERS_ID));
            user.setName(set.getString(Const.USERS_NAME));
            user.setSecondName(set.getString(Const.USERS_SECONDNAME));
            user.setPatronymic(set.getString(Const.USERS_PATRONYMIC));
            user.setDate(set.getString(Const.USERS_DATEBIRTH));
            user.setPas_info(set.getString(Const.USERS_PAS_INFO));
            user.setTel(set.getString(Const.USERS_PHONE));
            user.setLogin(set.getString(Const.USERS_LOGIN));
            user.setPassword(set.getString(Const.USERS_PASSWORD));
            user.setStatus(set.getString(Const.USERS_STATUS));
            ListUser.add(user);
        }
        out.writeObject(ListUser.size());
        for(int i=0;i<ListUser.size();i++)
        {
            out.writeObject(ListUser.get(i).getId());
            out.writeObject(ListUser.get(i).getName());
            out.writeObject(ListUser.get(i).getSecondName());
            out.writeObject(ListUser.get(i).getPatronymic());
            out.writeObject(ListUser.get(i).getDate());
            out.writeObject(ListUser.get(i).getTel());
            out.writeObject(ListUser.get(i).getPas_info());
            out.writeObject(ListUser.get(i).getLogin());
            out.writeObject(ListUser.get(i).getPassword());
            out.writeObject(ListUser.get(i).getStatus());

        }
    }
    private void AddDepartament(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException ,SQLException{
        String name=(String)in.readObject();
        String numb=(String)in.readObject();
        int numbers=Integer.parseInt(numb);
        Departament departament=new Departament();
        departament.setNameDepartment(name);
        departament.setNumberEmploye(numbers);
        departament.setCash(0);
        ListDepartament.add(departament);
        DateBaseHandler base =new DateBaseHandler();
        base.Registration(departament);
    }
    private void PayDepartament(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject(ListDepartament.size());
        for(int i=0;i<ListDepartament.size();i++)
        {
            out .writeObject(ListDepartament.get(i).getNameDepartment());
        }
    }
    private void PayDepartament1(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        double sum=(double)in.readObject();
        String name=(String)in.readObject();
        if(ListCompany.get(0).getCahs()>=sum)
        {
            double cash=ListCompany.get(0).getCahs()-sum;
            ListCompany.get(0).setCahs(cash);
            for(int i=0;i<ListDepartament.size();i++)
            {
                if(ListDepartament.get(i).getNameDepartment().equals(name))
                {
                    double cash1=ListDepartament.get(i).getCash();
                    ListDepartament.get(i).setCash(cash1+sum);
                    DateBaseHandler base = new DateBaseHandler();
                    base.ChangeCashDepart(name,ListDepartament.get(i).getCash());
                }
            }
            out.writeObject("Успешно");
        }
        if (ListCompany.get(0).getCahs()<sum)
        {
            out.writeObject("Недостаточно");
        }
    }
    private void Replenish(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        double sum=(Double.parseDouble((String)in.readObject()));
        ListCompany.get(0).setCahs(ListCompany.get(0).getCahs()+sum);
        DateBaseHandler base =new DateBaseHandler();
        base.ChangeCashCompany(ListCompany.get(0).getCahs(),ListCompany.get(0).getNameCompany());
    }
    private void RegistrationCompany(ObjectOutputStream out, ObjectInputStream in) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(ListCompany.size());
        if(ListCompany.size()==0) {
            Company company = new Company();
            company.setNameCompany((String) in.readObject());
            company.setCahs(1000000);
            ListCompany.add(company);
            DateBaseHandler base = new DateBaseHandler();
            base.Registration(company);
        }
    }
    private void infoCompany(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject(ListCompany.get(0).getNameCompany());
        out.writeObject(Double.toString(ListCompany.get(0).getCahs()));
        out.writeObject(Integer.toString(ListDepartament.size()));
    }
    private void Statistic(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject(Integer.toString(ListUser.size()));
        out.writeObject(Double.toString(-1234.2));
        out.writeObject(Double.toString(ListCompany.get(0).getCahs()));
        out.writeObject(Integer.toString(ListDepartament.size()));
        out.writeObject(2);
    }
    private void WriteStatistic(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject(Integer.toString(ListUser.size()));
        out.writeObject(Double.toString(-1234.2));
        out.writeObject(Double.toString(ListCompany.get(0).getCahs()));
        out.writeObject(Integer.toString(ListDepartament.size()));
        out.writeObject(1);
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
    private void getSalary1(ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.writeObject(ListUser.size());
        for(int i=0;i<ListUser.size();i++)
        {
            out.writeObject(ListUser.get(i).getLogin());
        }
    }
    private void getSalary(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        History history =new History();
        history.setName((String) in.readObject());
        history.setSum(Double.parseDouble((String)in.readObject()));
        history.setLog("log");
        double cash=ListCompany.get(0).getCahs();
        cash=cash-history.getSum();
        ListCompany.get(0).setCahs(cash);
        ListHistory.add(history);
        DateBaseHandler base =new DateBaseHandler();
        base.RegistrationHistory(history);
        base.ChangeCashCompany(ListCompany.get(0).getCahs(),ListCompany.get(0).getNameCompany());
    }
    private void InfoUser(ObjectOutputStream out, ObjectInputStream in , Users user) throws IOException {
        out.writeObject(ListUser.size());
        for(int i=0;i<ListUser.size();i++)
        {
            out.writeObject(ListUser.get(i).getId());
            out.writeObject(ListUser.get(i).getName());
            out.writeObject(ListUser.get(i).getSecondName());
            out.writeObject(ListUser.get(i).getPatronymic());
            out.writeObject(ListUser.get(i).getDate());
            out.writeObject(ListUser.get(i).getTel());
            out.writeObject(ListUser.get(i).getPas_info());
            out.writeObject(ListUser.get(i).getLogin());
            out.writeObject(ListUser.get(i).getPassword());
            out.writeObject(ListUser.get(i).getStatus());
        }
    }
    public void MenuAdmin(ObjectOutputStream out, ObjectInputStream in , Users user) throws IOException, ClassNotFoundException, SQLException {
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
        while (true)
        {
            String msg=(String)in.readObject();
            switch (msg)
            {
                case"Оплата Сотруднику":getSalary1(out,in);break;
                case"Зачислить сотуднику":getSalary(out,in);break;
                case"Печать":WriteStatistic(out,in);break;
                case "Статистика":Statistic(out,in);break;
                case "Перевел Отделу":PayDepartament1(out,in);break;
                case"Информация о Компании":infoCompany(out,in);break;
                case"Пополнить":Replenish(out,in);break;
                case"Регистрация компании":RegistrationCompany(out,in);break;
                case "Перевод отделу":PayDepartament(out,in);break;
                case"Таблица отделова":InfoDepartament(out,in);break;
                case"Добавить отдел":AddDepartament(out,in);break;
                case"Сделать Админом":AddAndDelAdmin(out,in);break;
                case"Сделать Юзером":AddAndDelAdmin(out,in);break;
                case "Информация о Пользователях":InfoUser(out, in, user);break;
                case "Выход":autorization.menuAutorization(out,in);break;
                case"Найти":FindUser(out, in, user);break;
                case"Изменить":ChangePersonalInfo1(out,in,user);break;
                case"Редактировать":ChangePersonalInfo(out,in,user);break;
            }
        }
    }
}
