package main.gian.App.Infrastructure.Repostitory;

import main.gian.App.Contracts.Infrastructure.Repository.RepositoryInterface;
import main.gian.App.Infrastructure.Socket.SocketClient;

public class ChatRepository implements RepositoryInterface{
    SocketClient socket;

    public ChatRepository(SocketClient socket) {
        this.socket = socket;
    }

    @Override
    public void sendMessage(String message) {
        this.socket.write(message);
    }

    public void close(){
        this.socket.close();
    }
}