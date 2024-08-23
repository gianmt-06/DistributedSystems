package gian;
import java.io.IOException;
import java.io.InputStream;
// import java.util.logging.FileHandler;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import java.util.logging.SimpleFormatter;
import java.util.Properties;

import gian.App.Application.Common.Interface.Persistence.ATMRepositoryInterface;
import gian.App.Contracts.ATMInterface;
import gian.App.Infrastructure.Persistence.ATMRepository;
import gian.App.Infrastructure.Socket.SocketServer;
import gian.App.Infrastructure.Socket.Factory.ThreadFactory;
import gian.App.Presentation.RequestManager;
import gian.App.Presentation.Controllers.ATMController;

public class ServerMain {
    // private static final Logger logger = Logger.getLogger(ServerMain.class.getName());
    
    public static void main(String[] args) throws SecurityException, IOException {
        // logger.setLevel(Level.FINE);

        // LogsManager.log(ServerMain.class.getName(), "Probando Logs", 123);
        
        // FileHandler fileHandler = new FileHandler("serversocket/Logs/app2.log", true);

        // fileHandler.setLevel(Level.FINE);
        // fileHandler.setFormatter(new SimpleFormatter());

        // logger.addHandler(fileHandler);

        // logger.setUseParentHandlers(false);
        
        // logger.info("Iniciando app...");


        // PropertiesReader reader = new PropertiesReader("config.properties"); 
        // String property = reader.getProperty("server.port");

        // System.out.println("Your prop: " + property);


        ATMRepositoryInterface atmRepository = new ATMRepository();
        ATMInterface atm = new ATMController(atmRepository);
        RequestManager requestManager = new RequestManager(atm);
        
        ThreadFactory threadFactory = new ThreadFactory(requestManager);
        SocketServer socketServer = new SocketServer(threadFactory);

        socketServer.listen();
    }

}
