package main.gian;

import main.gian.App.Infrastructure.Socket.SocketServer;
import main.gian.App.Infrastructure.Socket.RequestManager.RequestManager;
import main.gian.App.Infrastructure.Socket.Services.BroadcastService;

public class Main {
    public static void main(String[] args) {
        BroadcastService broadcastService = new BroadcastService();
        RequestManager requestManager = new RequestManager();
        SocketServer socketServer = new SocketServer(broadcastService, requestManager);
        socketServer.listen();
    }
}