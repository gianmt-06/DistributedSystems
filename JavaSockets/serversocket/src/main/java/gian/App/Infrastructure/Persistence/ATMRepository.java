package gian.App.Infrastructure.Persistence;

import java.util.ArrayList;
import java.util.List;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;
import gian.App.Domain.Entities.Account;

public class ATMRepository implements ATMRepositoryInterface {
    private static final List<Account> accounts = new ArrayList<>();

    static {
        accounts.add(new Account(1, 1000000, 1, 1, "password123"));
        accounts.add(new Account(2, 1500000, 2, 1, "password456"));
        accounts.add(new Account(3, 500000, 3, 0, "password789"));
    }

    @Override
    public Account getAccount(int idCard) {
        for (Account account : accounts) {
            if (account.getIdCard() == idCard) return account;
        }

        throw new UnsupportedOperationException("Unimplemented method 'geAccount'");
    }

    @Override
    public boolean addMoney(int idAccount, int money) {
        Account account = getAccount(idAccount);
        float currentMoney = account.getMoney();

        if (account != null && money > 0) account.setMoney(currentMoney + money);
        return true;
    }

    @Override
    public float getMoney(int idCard, int money) {
        Account account = getAccount(idCard);
        float currentMoney = account.getMoney();

        if (account != null && currentMoney >= money) {
            account.setMoney(currentMoney - money);
            return account.getMoney();
        }

        return 0;
    }

    @Override
    public float getBalance(int idCard) {
        Account account = getAccount(idCard);
        return account != null ? account.getMoney() : 0;
    }

    @Override
    public boolean doAdvance(int idCard, int money) {
        getMoney(idCard, money);
        return true;
    }

}
