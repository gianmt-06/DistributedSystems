package gian.App.Domain.Entities;

public class Account {
    private int idAccount;
    private float money;
    private int idCard;
    private String type;
    private String password;

    public Account(int newIdAccount, float newMoney, int newIdCard, String newType, String newPassword) {
        this.idAccount = newIdAccount;
        this.money = newMoney;
        this.idCard = newIdCard;
        this.type = newType;
        this.password = newPassword;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
