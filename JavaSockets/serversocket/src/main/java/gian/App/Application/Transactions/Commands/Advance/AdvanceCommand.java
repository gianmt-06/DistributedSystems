package gian.App.Application.Transactions.Commands.Advance;

public record AdvanceCommand(
    int idCard,
    int money
){};