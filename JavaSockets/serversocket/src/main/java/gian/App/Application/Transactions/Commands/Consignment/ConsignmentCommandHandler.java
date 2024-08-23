package gian.App.Application.Transactions.Commands.Consignment;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;

public class ConsignmentCommandHandler {
    private final ATMRepositoryInterface atmRepository;

    public ConsignmentCommandHandler(ATMRepositoryInterface atmRepository) {
        this.atmRepository = atmRepository;
    }

    public boolean handle(ConsignmentCommand command) {
        return atmRepository.addMoney(command.idCard(), command.money());
    }
}

