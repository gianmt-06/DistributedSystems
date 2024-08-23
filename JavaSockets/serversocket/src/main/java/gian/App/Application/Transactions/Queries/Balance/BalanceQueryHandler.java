package gian.App.Application.Transactions.Queries.Balance;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;

public class BalanceQueryHandler {
    private final ATMRepositoryInterface atmRepository;

    public BalanceQueryHandler(ATMRepositoryInterface atmRepository) {
        this.atmRepository = atmRepository;
    }

    public float handle(BalanceQuery command) {
        return atmRepository.getBalance(command.idCard());
    }
}
