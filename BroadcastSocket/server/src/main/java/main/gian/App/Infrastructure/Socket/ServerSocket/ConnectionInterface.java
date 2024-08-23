package main.gian.App.Infrastructure.Socket.ServerSocket;

import java.util.List;

public interface ConnectionInterface  {
    public boolean bind();
    public List<Object> listen();
    public boolean response(List<Object> data);
    public boolean close();
    public String getConnectionId();
    public Session getSession();

}