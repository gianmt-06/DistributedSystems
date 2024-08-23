package gian.App.Contracts.ATM;

import java.io.Serializable;

public record GetMoneyRequest( 
    int idCard,
    String password,
    int money
) implements Serializable {}