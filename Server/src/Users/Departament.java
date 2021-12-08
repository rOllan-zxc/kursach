package Users;

public class Departament {
    String NameDepartment;
    int NumberEmploye;
    double cash;

    public void setNameDepartment(String nameDepartment) {
        NameDepartment = nameDepartment;
    }

    public void setNumberEmploye(int numberEmploye) {
        NumberEmploye = numberEmploye;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getNameDepartment() {
        return NameDepartment;
    }

    public int getNumberEmploye() {
        return NumberEmploye;
    }

    public double getCash() {
        return cash;
    }
}
