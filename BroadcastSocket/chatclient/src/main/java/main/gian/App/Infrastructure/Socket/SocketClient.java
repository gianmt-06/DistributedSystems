package main.gian.App.Infrastructure.Socket;

import java.net.Socket;
import java.util.ArrayList;

import main.gian.App.Contracts.Infrastructure.Communication.CommunicationInterface;
import main.gian.App.Environment.Environment;
import main.gian.App.Infrastructure.Socket.JavaClientSocket.JavaClientSocket;
import main.gian.App.Infrastructure.Socket.Server.Connection;
import main.gian.App.Infrastructure.Socket.Server.ConnectionInterface;

public class SocketClient implements CommunicationInterface {
    private ConnectionInterface connection;

    @Override
    public boolean connect() {

        int PORT = Environment.getInstance().PORT;
        String HOST = Environment.getInstance().HOST;

        JavaClientSocket javaClientSocket = new JavaClientSocket(PORT, HOST);
        Socket clientSocket = javaClientSocket.get();

        if (clientSocket == null) {
            System.out.println("ClientSocket is null");
            return false;
        }

        this.connection = new Connection(clientSocket);
        
        try {
            if (!connection.bind()) {
                System.out.println("Client bind failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("no errar");
        

        return true;
    }

    @Override
    public void write(String message) {
        ArrayList<Object> messageToSend = new ArrayList<Object>();

        messageToSend.add(message);
        messageToSend.add(0);

        this.connection.request(messageToSend);
    }

    public ArrayList<Object> listen() {
        ArrayList<Object> newMessages = (ArrayList<Object>) this.connection.listen();
        return newMessages;
    }

    @Override
    public void close() {
        ArrayList<Object> messageToSend = new ArrayList<Object>();

        messageToSend.add(1);
        messageToSend.add(0);

        this.connection.request(messageToSend);
        this.connection.close();
    }
}
