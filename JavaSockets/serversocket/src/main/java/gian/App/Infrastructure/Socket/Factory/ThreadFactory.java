package gian.App.Infrastructure.Socket.Factory;

import gian.App.Infrastructure.Socket.Server.Server;
import gian.App.Infrastructure.Socket.Server.SocketThread;
import gian.App.Presentation.RequestManager;

public class ThreadFactory {
    RequestManager requestManager;

    public ThreadFactory(RequestManager requestManager){
        this.requestManager = requestManager;
    }
    
    public Thread getThread(Server server){
        return new SocketThread(server, requestManager);
    }
}
