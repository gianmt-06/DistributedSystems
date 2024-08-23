package gian.App.Contracts;

import gian.App.Contracts.ATM.CardAdvanceRequest;
import gian.App.Contracts.ATM.ConsignmentToAccountRequest;
import gian.App.Contracts.ATM.GetBalanceRequest;
import gian.App.Contracts.ATM.GetMoneyRequest;
import gian.App.Contracts.Responses.ATMResponse;

public interface ATMInterface {
    ATMResponse validateAccount(int idCard, String password);
    ATMResponse getMoney(GetMoneyRequest request);
    ATMResponse cardAdvance(CardAdvanceRequest request);
    ATMResponse consignmentToAccount(ConsignmentToAccountRequest request);
    ATMResponse getBalance(GetBalanceRequest request);
}