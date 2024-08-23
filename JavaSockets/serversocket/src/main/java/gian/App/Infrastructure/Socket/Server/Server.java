package gian.App.Infrastructure.Socket.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import gian.utils.Logger.LogsManager;

public class Server implements SocketProcess {
	private ServerSocket serverSocket;
	private Session session;

	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.session = null;
	}

	@Override
	public boolean bind() {
		try {
			Socket socket = this.serverSocket.accept();
			System.out.print("[IP Connection " + socket.getInetAddress().getHostAddress() + "]");
			LogsManager.log(this.getClass().getName(), "[IP Connection " + socket.getInetAddress().getHostAddress() + "]");
			this.session = new Session(socket);
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
}
