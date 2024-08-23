package gian.App.Infrastructure.Socket;

import java.net.ServerSocket;

import gian.App.Application.Common.Interface.Communication.CommunicationServerInterface;
import gian.App.Infrastructure.Socket.Factory.ThreadFactory;
import gian.App.Infrastructure.Socket.JavaServerSocket.JavaServerSocket;
import gian.App.Infrastructure.Socket.Server.Server;
import gian.utils.Logger.LogsManager;

public class SocketServer implements CommunicationServerInterface{
    ServerSocket serverSocket;
    ThreadFactory threadFactory;

    public SocketServer(ThreadFactory threadFactory) {
        LogsManager.log(this.getClass().getName(), "Ready Socket Server");

        System.out.println("This is your Java Server Socket");

        JavaServerSocket javaServerSocket = new JavaServerSocket(1802, 100);
        this.serverSocket = javaServerSocket.get();

        if (this.serverSocket == null) {
            System.out.println("ServerSocket is null");
            return;
        }

        this.threadFactory = threadFactory;
    }

    @Override
    public void listen() {
        Server socketProcess;
        while (true) {
            socketProcess = new Server(serverSocket);
            socketProcess.bind();
            threadFactory.getThread(socketProcess).start();
            //new SocketThread(socketProcess, Atm).start();    
        }
    }
}
