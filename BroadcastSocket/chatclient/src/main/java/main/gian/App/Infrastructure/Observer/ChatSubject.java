package main.gian.App.Infrastructure.Observer;

import java.util.ArrayList;
import java.util.List;

import main.gian.App.Contracts.Infrastructure.Observer.ObserverInterface;
import main.gian.App.Infrastructure.Socket.SocketClient;

public class ChatSubject {
    private SocketClient socket;
    private List<ObserverInterface> observers = new ArrayList<>();

    public ChatSubject(SocketClient socket) {
        this.socket = socket;
    }

    public void addObserver(ObserverInterface observer) {
        observers.add(observer);
    }

    public void notifyObservers(ArrayList<Object> message) {
        for (ObserverInterface observer : observers) {
            observer.update(message);
        }
    }

    public void listenToSocket() {
        while (true) {
            System.out.println("INICIANDO LISTEN");
            ArrayList<Object> response = this.socket.listen();
            System.out.println("DATA");
            System.out.println(response);
            notifyObservers(response);
        }
    }
}

