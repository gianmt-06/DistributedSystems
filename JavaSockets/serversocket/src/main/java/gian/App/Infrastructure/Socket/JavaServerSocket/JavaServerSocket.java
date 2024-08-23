package gian.App.Infrastructure.Socket.JavaServerSocket;

import java.io.IOException;

import java.net.ServerSocket;

import gian.utils.Logger.LogsManager;

public class JavaServerSocket {
  private int port;
  private int amountClients;

  public JavaServerSocket(int port, int amountClients) {
    this.port = port;
    this.amountClients = amountClients;
  }

  public ServerSocket get() {
		try {
			return new ServerSocket(this.port, this.amountClients);
		} catch (IOException e) { 
      LogsManager.log(this.getClass().getName(), e.getMessage(), e);
			//Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);    
			return null;
		}
	}
}
