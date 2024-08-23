package gian.App.Infrastructure.Socket.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements SocketProcess {
  Socket clientSocket;
  Session session;

  public Client(Socket clientSocket) {
    this.clientSocket = clientSocket;
    this.session = null;
  }

  @Override
  public boolean bind() {
    this.session = new Session(clientSocket);
    return true;
  }

  @Override
  public List<Object> listen() {
    ArrayList<Object> dataList = new ArrayList<>();
    boolean next = true;
    Object data = null;
    int flag = 1;
    while (next) {
      data = this.session.read();
      if (data != null) {
        try {
          flag = (int) data;
        } catch (Exception e) {
          flag = 1;
        }
        try {
          next = flag != 0;
          if (next) {
            dataList.add(data);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return dataList;
  }

  @Override
  public boolean request(List<Object> data) {
    data.forEach(d -> this.session.write(d));
    return true;
  }

  @Override
  public boolean close() {
    boolean successful = this.session.close();
    this.session = null;
    return successful;
  }

}
