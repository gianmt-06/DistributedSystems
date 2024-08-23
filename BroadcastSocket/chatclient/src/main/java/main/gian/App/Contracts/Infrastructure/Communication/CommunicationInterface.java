package main.gian.App.Contracts.Infrastructure.Communication;

import java.util.ArrayList;

public interface CommunicationInterface {
    public boolean connect();
    public void write(String message);
    public ArrayList<Object> listen();
    public void close();
}
