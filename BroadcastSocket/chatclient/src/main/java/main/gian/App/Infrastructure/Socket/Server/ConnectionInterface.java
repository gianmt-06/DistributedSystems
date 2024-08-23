package main.gian.App.Infrastructure.Socket.Server;

import java.util.List;

public interface ConnectionInterface {
  public boolean bind();
  public List<Object> listen();
  public boolean request(List<Object> data);
  public boolean close();
  public String getId();
}