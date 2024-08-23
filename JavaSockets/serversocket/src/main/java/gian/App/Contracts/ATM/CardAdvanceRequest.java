package gian.App.Contracts.ATM;

import java.io.Serializable;

public record CardAdvanceRequest(
    int idCard,
    String password,
    int money
) implements Serializable {}