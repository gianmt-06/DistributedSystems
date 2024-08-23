package gian.App.Contracts.ATM;

import java.io.Serializable;

public record ConsignmentToAccountRequest(
    int idCard,
    String password,
    int idAccount,
    int money
) implements Serializable {}
