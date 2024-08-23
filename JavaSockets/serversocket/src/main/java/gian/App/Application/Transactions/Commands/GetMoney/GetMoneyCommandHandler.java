package gian.App.Application.Transactions.Commands.GetMoney;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;

public class GetMoneyCommandHandler {
    private final ATMRepositoryInterface atmRepository;

    public GetMoneyCommandHandler(ATMRepositoryInterface atmRepository) {
        
        this.atmRepository = atmRepository;
    }

    public float handle(GetMoneyCommand command) {
        return atmRepository.getMoney(command.idCard(), command.money());
    }
}
