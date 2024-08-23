package gian.App.Application.Transactions.Commands.Advance;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;
import gian.App.Domain.Entities.Account;

public class AdvanceCommandHandler {
    private final ATMRepositoryInterface atmRepository;

    public AdvanceCommandHandler(ATMRepositoryInterface atmRepository) {
        this.atmRepository = atmRepository;
    }

    public boolean handle(AdvanceCommand command) {
        Account account = atmRepository.getAccount(command.idCard());

        if(account.getType() == 1) return false;

        return atmRepository.doAdvance(command.idCard(), command.money());
    }
}
