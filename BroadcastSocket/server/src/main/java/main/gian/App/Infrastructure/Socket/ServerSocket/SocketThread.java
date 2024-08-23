package main.gian.App.Infrastructure.Socket.ServerSocket;

import java.util.ArrayList;

import main.gian.App.Infrastructure.Socket.RequestManager.RequestManagerInterface;
import main.gian.App.Infrastructure.Socket.Services.BroadcastInterface;

public class SocketThread extends Thread {
    private Connection connection;
    private BroadcastInterface broadcastService;
    private RequestManagerInterface requestManager;
    protected final String BYE = "Bye";    

    public SocketThread(
        Connection connection, 
        BroadcastInterface broadcastService,
        RequestManagerInterface requestManager    
    ){
        this.connection = connection;
        this.broadcastService = broadcastService;
        this.requestManager = requestManager;
        this.setDaemon(true);
    }

    public void run(){
        printThreadData();

        boolean continueCommunication = true;
        ArrayList<Object> dataRequest;
        ArrayList<Object> dataResponse = new ArrayList<Object>();
        
        while (continueCommunication) {
            dataRequest = (ArrayList<Object>) connection.listen();
            
            continueCommunication = this.requestManager.closeCommunication(dataRequest);
            if (!continueCommunication) continue;
           
            dataResponse = this.requestManager.getMessageResponse(dataRequest, this.connection.getConnectionId());
            this.broadcastService.notifyConnections(dataResponse, this.connection.getSession());
        }

        dataResponse = this.requestManager.getRemoveClientResponse(this.connection.getConnectionId());
        this.broadcastService.notifyConnections(dataResponse, this.connection.getSession());

        dataResponse = this.requestManager.closeClientResponse(this.connection.getConnectionId());
        this.broadcastService.removeConnection(dataResponse, this.connection);

        interrupt();
    }
    
    public void printThreadData(){
        System.out.print("[Thread " + this.threadId() + "]\n");
    }
}