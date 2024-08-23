package main.gian.App.Infrastructure.Socket.JavaClientSocket;

import java.io.IOException;

import java.net.Socket;
//import java.util.logging.Logger;

public class JavaClientSocket {
  private int port;
  private String host;

  public JavaClientSocket(int port, String host) {
    this.port = port;
    this.host = host;
  }

  public Socket get() {
		try {
			return new Socket(this.host, this.port);
		} catch (IOException e) { 
			//Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);    
			return null;
		}
	}
}
