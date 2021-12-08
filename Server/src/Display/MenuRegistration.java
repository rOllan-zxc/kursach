package Display;

import DateBaseHandler.DateBaseHandler;
import Users.Users;
import com.mysql.cj.xdevapi.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.sql.SQLException;

public class MenuRegistration implements Serializable {
    public void menuRegistration(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        MenuAutorization menu =new MenuAutorization();
        while(true)
        {
            String msg= (String)in.readObject();
            switch (msg)
            {
                case "Зарегистрировать":System.out.println("Зарегистрироваться");registrationUser(out,in);break;
                case "Выход":System.out.println("Выход");menu.menuAutorization(out,in);break;
            }
        }
    }
    public void registrationUser(ObjectOutputStream out ,ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        Users user=new Users();
        user.setStatus("User");
        user.setName((String)in.readObject());
        user.setSecondName((String)in.readObject());
        user.setPatronymic((String)in.readObject());
        user.setDate((String)in.readObject());
        user.setTel((String)in.readObject());
        user.setPas_info((String)in.readObject());
        user.setLogin((String)in.readObject());
        user.setPassword((String)in.readObject());
        DateBaseHandler base=new DateBaseHandler();
        base.RegistrationUser(user);
    }
}
