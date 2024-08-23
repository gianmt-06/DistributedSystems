package main.gian.App.Infrastructure.Socket.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection implements ConnectionInterface {
  Socket clientSocket;
  Session session;
  String id;
  private final String STOP = "0";


  public Connection(Socket clientSocket) {
    this.clientSocket = clientSocket;
    this.session = null;
  }

  @Override
  public boolean bind() {
    try {
      System.out.println("DO BIND");
      this.session = new Session(clientSocket);
      System.out.println("Sesion setter");
      System.out.println(session);
      return true;
      
    
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("error sesion");
      return false;
    }

  }

  @Override
  public List<Object> listen() {
    ArrayList<Object> dataList = new ArrayList<>();
    boolean next = true;
    Object data;
    int flag;

    while (next) {
        data = session.read();
        if (data != null) {
            if (String.valueOf(data).equals(STOP)) flag = -1;
            else flag = 1;

            try {
                next = flag != -1;
                if (next) {
                    dataList.add(data);
                }
            } catch (Exception e) {
                //Logs.logWARNING(this.getClass(), e.getMessage(), e);
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

  public String getId() {
      return id;
  }
}
