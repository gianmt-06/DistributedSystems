package gian.App.Infrastructure.Socket.Server;

import java.util.ArrayList;

import gian.App.Contracts.ATMInterface;
import gian.App.Contracts.ATM.CardAdvanceRequest;
import gian.App.Contracts.ATM.ConsignmentToAccountRequest;
import gian.App.Contracts.ATM.GetBalanceRequest;
import gian.App.Contracts.ATM.GetMoneyRequest;
import gian.App.Contracts.Responses.ATMResponse;
import gian.App.Presentation.RequestManager;
import gian.utils.Logger.LogsManager;


public class SocketThread extends Thread {
    private Server socketProcess;
    private RequestManager requestManager;

    public SocketThread(Server socketProcess, RequestManager requestManager){
        this.socketProcess = socketProcess;
        this.requestManager = requestManager;
    }

    public void run(){
        System.out.print("[Thread " + this.threadId() + "]\n");
        LogsManager.log(this.getClass().getName(), "[Thread " + this.threadId() + "]");
        ArrayList<Object> dataRequest = (ArrayList<Object>) socketProcess.listen();

        String option = dataRequest.get(0).toString();
        Object data = (Object) dataRequest.get(1);
        //Object credentials = dataRequest.get(2);

        ArrayList<Object> dataResponse = this.requestManager.request(option, data);
        socketProcess.response(dataResponse);

        socketProcess.close();
        interrupt();
    }

}
