package main.gian.App.Infrastructure.Socket.Services;

import java.util.ArrayList;
import java.util.List;

import main.gian.App.Infrastructure.Socket.ServerSocket.ConnectionInterface;

public class BroadcastService implements BroadcastInterface {
    ArrayList<ConnectionInterface> activeConnections;

    public BroadcastService() {
        this.activeConnections = new ArrayList<ConnectionInterface>();
    }

    /**
     * Agregar una nueva conexión a la lista de conexiones
     */
    public boolean addConnection(ConnectionInterface connection) {
        activeConnections.add(connection);
        return true;
    }

    /**
     * Remover y cerrar una conexión
     */
    public boolean removeConnection(List<Object> data, ConnectionInterface connection) {
        connection.response(data);
        connection.close();
        this.activeConnections.remove(connection);
        return true;
    }

    /** 
     * Enviar datos a los clientes 
     */
    public boolean notifyConnections(List<Object> data, Object session) {
        try {
            for (ConnectionInterface connection : activeConnections) {
                if (connection.getSession() == session) continue;
                connection.response(data);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    /**
     * Enviar las conexiones activas a un cliente
     */
    public boolean sendAllMembers(List<Object> data, ConnectionInterface newConnection) {
        newConnection.response(data);
        return true;
    }

    /**
     * Obtener la lista de conexiones activas
     */
    public ArrayList<ConnectionInterface> getConnections() {
        return this.activeConnections;
    }

    /**
     * Cerrar todas las conexiones activas 
     */
    public boolean closeConnections() {
        activeConnections.forEach(ConnectionInterface::close);
        this.activeConnections.clear();
        return true;
    }
}
