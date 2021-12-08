package Client;

public class Men {
    private String name;
    private String secondName;
    private String patronymic;

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPas_info(String pas_info) {
        this.pas_info = pas_info;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getTel() {
        return tel;
    }

    public String getPas_info() {
        return pas_info;
    }

    public String getDate() {
        return date;
    }

    private String tel;
    private String pas_info;
    private String date;

}
