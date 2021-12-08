package Client;

public class Users extends Men {
    String status;
    int id;
    String login;
    String password;
    String NumbCards;

    public String getNumbCards() {
        return NumbCards;
    }

    public void setNumbCards(String numbCards) {
        NumbCards = numbCards;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
