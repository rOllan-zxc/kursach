package Client;

import Controller.Autorization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static Client singleton = new Client();
    private int port =1111;
    private Socket socket;
    public void CloseSocket() throws IOException {
        socket.close();
    }
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public ObjectOutputStream getOut(){return out;}
    public ObjectInputStream getIn(){return in;}
    public static Client getInstance()
    {
        return singleton;
    }
    private Client()  {
    }
    public void connection() throws IOException, ClassNotFoundException {
        socket=new Socket("localhost",port);
        out=new ObjectOutputStream(socket.getOutputStream());
        in =new ObjectInputStream(socket.getInputStream());
        Autorization auto= new Autorization();
        auto.StartAutorization();
    }

}
