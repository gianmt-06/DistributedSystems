package gian.App.Presentation;

import java.util.ArrayList;

import gian.App.Contracts.ATMInterface;
import gian.App.Contracts.ATM.CardAdvanceRequest;
import gian.App.Contracts.ATM.ConsignmentToAccountRequest;
import gian.App.Contracts.ATM.GetBalanceRequest;
import gian.App.Contracts.ATM.GetMoneyRequest;
import gian.App.Contracts.Responses.ATMResponse;
import gian.utils.Logger.LogsManager;

public class RequestManager {
    ATMInterface atmService;

    public RequestManager(ATMInterface atmService){
        this.atmService = atmService;
    }

    public ArrayList<Object> request(String option, Object requestData) {
        LogsManager.log(this.getClass().getName(), "REQUEST", requestData);
        ATMResponse response;
        ArrayList<Object> dataResponse = new ArrayList<>();

        try {
            switch (option) {
                case "a":
                    GetMoneyRequest getMoneyRequest = (GetMoneyRequest) requestData;
                    response = atmService.getMoney(getMoneyRequest);
                    break;

                case "b":
                    CardAdvanceRequest advanceRequest = (CardAdvanceRequest) requestData;
                    response = atmService.cardAdvance(advanceRequest);
                    break;

                case "c":
                    ConsignmentToAccountRequest consignment = (ConsignmentToAccountRequest) requestData;
                    response = atmService.consignmentToAccount(consignment);
                    break;

                case "d":
                    GetBalanceRequest getBalanceRequest = (GetBalanceRequest) requestData;
                    response = atmService.getBalance(getBalanceRequest);
                    break;

                default:
                    throw new Error("Opción inválida");
            }

            dataResponse.add(response);
            dataResponse.add(0);

            LogsManager.log(this.getClass().getName(), "RESPONSE", dataResponse);
            return dataResponse;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
