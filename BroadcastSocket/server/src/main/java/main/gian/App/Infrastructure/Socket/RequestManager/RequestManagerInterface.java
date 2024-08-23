package main.gian.App.Infrastructure.Socket.RequestManager;

import java.util.ArrayList;

import main.gian.App.Infrastructure.Socket.ServerSocket.ConnectionInterface;

public interface RequestManagerInterface {
    public String getPayload(ArrayList<Object> data);
    public ArrayList<Object> getMessageResponse(ArrayList<Object> data,  String senderId);
    public ArrayList<Object> getMemberListResponse(ArrayList<ConnectionInterface> connections);
    public ArrayList<Object> notifyOneConnection(String member);
    public ArrayList<Object> getRemoveClientResponse(String member);
    public boolean closeCommunication(ArrayList<Object> data);
    public ArrayList<Object> closeClientResponse(String member);
}