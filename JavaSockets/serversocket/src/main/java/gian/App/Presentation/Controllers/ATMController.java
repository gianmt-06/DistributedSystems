package gian.App.Presentation.Controllers;

import gian.App.Application.Authentication.Queries.Validator.ValidatorQuery;
import gian.App.Application.Authentication.Queries.Validator.ValidatorQueryHandler;
import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;

import gian.App.Application.Transactions.Commands.Advance.AdvanceCommand;
import gian.App.Application.Transactions.Commands.Advance.AdvanceCommandHandler;
import gian.App.Application.Transactions.Commands.Consignment.ConsignmentCommand;
import gian.App.Application.Transactions.Commands.Consignment.ConsignmentCommandHandler;
import gian.App.Application.Transactions.Commands.GetMoney.GetMoneyCommand;
import gian.App.Application.Transactions.Commands.GetMoney.GetMoneyCommandHandler;

import gian.App.Application.Transactions.Queries.Balance.BalanceQuery;
import gian.App.Application.Transactions.Queries.Balance.BalanceQueryHandler;

import gian.App.Contracts.ATMInterface;
import gian.App.Contracts.ATM.CardAdvanceRequest;
import gian.App.Contracts.ATM.ConsignmentToAccountRequest;
import gian.App.Contracts.ATM.GetBalanceRequest;
import gian.App.Contracts.ATM.GetMoneyRequest;
import gian.App.Contracts.Responses.ATMResponse;

import gian.App.Domain.Entities.Account;

public class ATMController implements ATMInterface {
    private final ATMRepositoryInterface atmRepository;
    private static ValidatorQueryHandler validator;

    public ATMController(ATMRepositoryInterface atmRepository) {
        this.atmRepository = atmRepository;
        validator = new ValidatorQueryHandler(atmRepository);
    }

    public ATMResponse validateAccount(int idCard, String password) {
        Account account = validator.handle(new ValidatorQuery(idCard, password));
        return account != null ? 
             new ATMResponse("201", "Cuenta validada con exito", "true")
             : new ATMResponse("404", "Error al validar cuenta", "false");
    }

    @Override
    public ATMResponse cardAdvance(CardAdvanceRequest request) {
        try {
            boolean response = new AdvanceCommandHandler(atmRepository).handle(new AdvanceCommand(request.idCard(), request.money()));
            
            if(!response) return new ATMResponse("403", "La cuenta no corresponde a una cuenta credito", "error");
            
            return new ATMResponse("201", "Avance realizado con éxito", String.format("Status: %s", response));
        } catch (Exception e) {
            return new ATMResponse("500", "Error al realizar avance", "Error de servidor");
        }
    }

    @Override
    public ATMResponse consignmentToAccount(ConsignmentToAccountRequest request) {
        //Account account = validateAccount(request.idCard(), request.password());
        try {
            boolean response = new ConsignmentCommandHandler(atmRepository).handle(new ConsignmentCommand(request.idAccount(), request.money()));          
            return new ATMResponse("201", "Consignación realizada con éxito", String.format("Status: %s", response));
        } catch (Exception e) {
            return new ATMResponse("500", "Error al realizar consignación", "Error de servidor");
        }
    }

    @Override
    public ATMResponse getBalance(GetBalanceRequest request) {
        try {
            float balance = new BalanceQueryHandler(atmRepository).handle(new BalanceQuery(request.idCard()));
            return new ATMResponse("201", "Balance realizado con éxito", String.format("Dinero en la cuenta: %s", balance));
        } catch (Exception e) {
            return new ATMResponse("500", "Error al realizar balance", "Error de servidor");
        }
    }

    @Override
    public ATMResponse getMoney(GetMoneyRequest request) {
        try {
            float money = new GetMoneyCommandHandler(atmRepository).handle(new GetMoneyCommand(request.idCard(), request.money()));
            return new ATMResponse("201", "Dinero retirado con éxito", String.format("Dinero en la cuenta %s", money));
        } catch (Exception e) {
            return new ATMResponse("500", "Error al realizar balance", "Error de servidor");
        }
    }
}