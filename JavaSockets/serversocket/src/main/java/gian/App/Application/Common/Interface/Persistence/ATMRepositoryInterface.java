package gian.App.Application.Common.Interface.Persistence;

import gian.App.Domain.Entities.Account;

public interface ATMRepositoryInterface {
    public Account getAccount(int idCard);
    public boolean addMoney(int idAccount, int money);
    public float getMoney(int idCard, int money);
    public float getBalance(int idCard);
    public boolean doAdvance(int idCard, int money);
}