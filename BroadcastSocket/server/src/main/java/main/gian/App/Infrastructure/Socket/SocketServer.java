package main.gian.App.Infrastructure.Socket;

import java.net.ServerSocket;
import java.util.ArrayList;

import main.gian.App.Environment.Environment;
import main.gian.App.Infrastructure.Socket.JavaServerSocket.JavaServerSocket;
import main.gian.App.Infrastructure.Socket.RequestManager.RequestManagerInterface;
import main.gian.App.Infrastructure.Socket.ServerSocket.Connection;
import main.gian.App.Infrastructure.Socket.ServerSocket.ConnectionInterface;
import main.gian.App.Infrastructure.Socket.ServerSocket.SocketThread;
import main.gian.App.Infrastructure.Socket.Services.BroadcastInterface;

public class SocketServer {
    private ServerSocket serverSocket;

    private BroadcastInterface broadcastService;
    private RequestManagerInterface requestManager;

    public SocketServer(
        BroadcastInterface broadcastService,
        RequestManagerInterface requestManager
    ){
        System.out.println("This is your Java Server Socket");

        int PORT = Environment.getInstance().PORT;
        int AMOUNT_CLIENTS = Environment.getInstance().AMOUNT_CLIENTS;

        JavaServerSocket javaServerSocket = new JavaServerSocket(PORT, AMOUNT_CLIENTS);
        this.serverSocket = javaServerSocket.get();

        if (this.serverSocket == null) {
            System.out.println("ServerSocket is null");
            return;
        }

        this.broadcastService = broadcastService;
        this.requestManager = requestManager;
    }

    public void listen() {
        ArrayList<ConnectionInterface> connections;
        ArrayList<Object> response;
        ArrayList<Object> id;

        while (true) {
            Connection connection = new Connection(serverSocket);
            connection.bind();
            this.broadcastService.addConnection(connection);
            
            id = (ArrayList<Object>) connection.listen();
            connection.setConnectionId(id.get(0).toString());

            connections = this.broadcastService.getConnections();
            response = this.requestManager.getMemberListResponse(connections);

            this.broadcastService.sendAllMembers(response, connection);
            
            response = this.requestManager.notifyOneConnection(connection.getConnectionId());
            this.broadcastService.notifyConnections(response, connection.getSession());

            new SocketThread(connection, broadcastService, requestManager).start();

            System.out.print("\nRegistrando cliente uid: " + connection.getConnectionId());
        }
    }
}
