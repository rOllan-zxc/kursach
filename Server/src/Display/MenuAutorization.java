package Display;

import DateBaseHandler.DateBaseHandler;
import DateBaseHandler.*;
import Users.Users;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Users.*;


public class MenuAutorization  {

    List<Users>listUsers= new ArrayList();
    private void autorization(ObjectOutputStream out,ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        String log=(String)in.readObject();
        String pas=(String)in.readObject();
        DateBaseHandler base= new DateBaseHandler();
        ResultSet set=base.GetInfoUsers();
        Users users=new Users();
        Users admins = new Users();
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
            listUsers.add(user);
        }
        int check=0;
        Users user=new Users();
        for(int i=0;i<listUsers.size();i++)
        {
            if(listUsers.get(i).getPassword().equals(pas)&&listUsers.get(i).getLogin().equals(log))
            {
                out.writeObject("Успешно");
                if(listUsers.get(i).getStatus().equals("User"))
                {
                    check=1;
                    out.writeObject("UserMenu");
                    user=listUsers.get(i);
                }
                if(listUsers.get(i).getStatus().equals("Admin"))
                {
                    check=2;
                    admins=listUsers.get(i);
                    out.writeObject("AdminMenu");
                }

            }

        }
        if(check==0)
        {
            out.writeObject("Не найдено");
        }
        if(check==1)
        {
            UserMenu userMenu=new UserMenu();
            userMenu.MenuUser(out,in,user);
        }
        if(check==2)
        {
            AdminMenu adminMenu=new AdminMenu();
            adminMenu.MenuAdmin(out,in,admins);
        }
    }

    public void menuAutorization(ObjectOutputStream out, ObjectInputStream in ) throws IOException, ClassNotFoundException, SQLException {
        MenuRegistration reg=new MenuRegistration();

        while (true) {
            String a = (String) in.readObject();
            switch (a) {
                case "Регистрация":
                    reg.menuRegistration(out, in);
                    break;
                case "Авторизицая":
                    autorization(out, in);
                    break;
            }
        }
    }
}
