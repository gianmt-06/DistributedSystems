package gian.App.Application.Authentication.Queries.Validator;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;
import gian.App.Domain.Entities.Account;

public class ValidatorQueryHandler {
    private final ATMRepositoryInterface atmRepository;

    public ValidatorQueryHandler(ATMRepositoryInterface atmRepository) {
        this.atmRepository = atmRepository;
    }

    public Account handle(ValidatorQuery query) {
        Account account = atmRepository.getAccount(query.idCard());
        if (account == null) {
            return null;
        }

        System.out.println(query.password());
        System.out.println(account.getPassword());
        
        if (!account.getPassword().equals(query.password())) {
            return null;
        }

        return account;
    }
}
