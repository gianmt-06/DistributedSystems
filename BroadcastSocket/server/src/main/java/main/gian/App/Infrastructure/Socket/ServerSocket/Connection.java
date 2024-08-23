package main.gian.App.Infrastructure.Socket.ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection implements ConnectionInterface {
	private ServerSocket serverSocket;
	private Session session;
	private String connectionId;
	private final String STOP = "0";

	public Connection(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.session = null;
	}

	@Override
	public boolean bind() {
		try {
			Socket socket = this.serverSocket.accept();
			System.out.print("[IP Connection " + socket.getInetAddress().getHostAddress() + "]");
			this.session = new Session(socket);
			//this.connectionId = this.session.read().toString();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
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
	public boolean response(List<Object> data) {
		data.forEach(d -> this.session.write(d));
		return true;
	}

	@Override
	public boolean close() {
		boolean successful = this.session.close();
		this.session = null;
		return successful;
	}

	@Override
	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	@Override
	public Session getSession() {
		return this.session;
	}

}