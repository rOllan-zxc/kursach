package Server;

import Display.MenuAutorization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server  {
    private int port =1111;
    private ServerSocket server ;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public ObjectOutputStream getOut(){return out;}
    public ObjectInputStream getIn(){return in;}
    public InetAddress getIpClient() { return socket.getInetAddress(); }
    public Server() throws IOException {server=new ServerSocket(port);}
    public void StartServer() throws IOException {
        while(true) {
            System.out.println("Ожидание подключения ...");
            socket = server.accept();
            out = new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());
            System.out.println("Клиент подключился IP = "+getIpClient());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MenuAutorization menu = new MenuAutorization();
                    try {
                        try {
                            menu.menuAutorization(out,in);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}
