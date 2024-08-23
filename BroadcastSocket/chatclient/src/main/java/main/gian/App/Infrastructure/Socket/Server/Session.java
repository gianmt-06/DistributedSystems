package main.gian.App.Infrastructure.Socket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Session {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    private final String STOP = "0";

    public Session(Socket socket) {
        try {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            this.socket = null;
            // Logs.logWARNING(this.getClass(), e.getMessage(), e);
        }
    }

    public Object read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            // Logs.logWARNING(this.getClass(), e.getMessage(), e);
            return null;
        }
    }

    public boolean write(Object data) {
        writer.println(String.valueOf(data));
        return true;
    }

    public boolean close() {
        try {
            reader.close();
            writer.close();
            this.socket.close();
            return true;
        } catch (IOException e) {
            // Logs.logWARNING(this.getClass(), e.getMessage(), e);
            return false;
        }
    }

    public Socket getSocket() {
        return socket;
    }
}