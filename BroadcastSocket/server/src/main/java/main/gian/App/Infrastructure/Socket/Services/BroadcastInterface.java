package main.gian.App.Infrastructure.Socket.Services;

import java.util.ArrayList;
import java.util.List;

import main.gian.App.Infrastructure.Socket.ServerSocket.ConnectionInterface;

public interface BroadcastInterface {
    public boolean addConnection(ConnectionInterface connection);
    public boolean removeConnection(List<Object> data, ConnectionInterface connection);
    public boolean notifyConnections(List<Object> data, Object session);
    public ArrayList<ConnectionInterface> getConnections();
    public boolean closeConnections();
    public boolean sendAllMembers(List<Object> data, ConnectionInterface newConnection);
    
}