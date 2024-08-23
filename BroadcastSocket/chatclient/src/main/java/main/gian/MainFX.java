package main.gian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.gian.App.Application.Services.SendMessageService;
import main.gian.App.Infrastructure.Observer.ChatSubject;
import main.gian.App.Infrastructure.Repostitory.ChatRepository;
import main.gian.App.Infrastructure.Socket.SocketClient;
import main.gian.App.Infrastructure.UI.Components.Chat.ChatController;
import main.gian.App.Infrastructure.UI.Components.Chat.ChatView;

/**
 * JavaFX App
 */
public class MainFX extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Chat Client");
        FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("fxml/Register.fxml"));
        VBox loginBox = loader.load();
        
        scene = new Scene(loginBox, 600, 600);
        
        scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void launchChat(String id) throws IOException{
        FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("fxml/ChatView.fxml"));
        VBox chatContainer = loader.load();

        scene.setRoot(chatContainer);
        
        ChatView chatView = loader.getController();
        
        SocketClient socketClient = new SocketClient();

        ChatSubject requestManager = new ChatSubject(socketClient);
        ChatRepository chatRepository = new ChatRepository(socketClient); 

        SendMessageService sendMessageService = new SendMessageService(chatRepository); 
        
        ChatController chatController = new ChatController(chatView, sendMessageService);
        
        requestManager.addObserver(chatController);
        
        Thread thread = new Thread(() -> {
            socketClient.connect();
            socketClient.write(id);
            chatController.setUserName(id);
            requestManager.listenToSocket();
        });

        thread.setDaemon(true);
        thread.start();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch();
    }

}