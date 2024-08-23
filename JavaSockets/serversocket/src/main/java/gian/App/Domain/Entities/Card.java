package gian.App.Domain.Entities;

public class Card {

    private int idCard;
    private boolean status;

    public Card(int idCard, boolean status) {
        this.idCard = idCard;
        this.status = status;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
