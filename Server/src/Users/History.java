package Users;

public class History {
    String Name ;
    double sum;
    String log;

    public void setName(String name) {
        Name = name;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getName() {
        return Name;
    }

    public double getSum() {
        return sum;
    }

    public String getLog() {
        return log;
    }
}
