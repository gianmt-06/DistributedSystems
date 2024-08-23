package gian.App.Contracts.ATM;

import java.io.Serializable;

public record GetBalanceRequest(
    int idCard,
    String password
) implements Serializable {}
