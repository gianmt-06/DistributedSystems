package gian.App.Application.Transactions.Commands.GetMoney;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record GetMoneyCommand(
    @NotNull
    int idCard,

    @Max(2000000)
    int money
){};