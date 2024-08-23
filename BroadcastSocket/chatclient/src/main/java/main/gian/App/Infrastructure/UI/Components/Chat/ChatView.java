package main.gian.App.Infrastructure.UI.Components.Chat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatView implements Initializable {

    @FXML
    private VBox messagesBox;
    @FXML
    private VBox activeChat;
    @FXML
    private Button sendButton;
    @FXML
    private TextField textField;
    @FXML 
    private Label usernameLabel;    
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }

    public void sendMessage(Function<String, Boolean> function) {
        sendButton.setOnAction(event -> {
            String message = textField.getText();
            this.textField.clear();
            function.apply(message); // Ejecutar el envio del mensaje
            showMessage("Yo: " + message);
        });
    }

    public void showMessage(String message) {
        Platform.runLater(() -> {
            try {
                this.messagesBox.getChildren().add(new Label(message));
            } catch (Exception e) {
                System.err.println(e);
            }
        });
    }

    public void bindMembers(ArrayList<Object> members) {
            for (Object member : members) {
                    // this.loader = new FXMLLoader(Main.class.getResource("fxml/ActiveMember.fxml"));
                    // HBox memberBox = this.loader.load();
                    // ActiveMemberCard controller = loader.getController();
                    //controller.changeName(member.toString());
                loadNewMember(member.toString());
            }
    }

    public void loadNewMember(String memberId){
        Platform.runLater(() -> {
            Label memberLabel = new Label(memberId.toString());
            this.activeChat.getChildren().add(memberLabel);
        });
    }

    public void removeMember(String memberId){
        Platform.runLater(() -> {
            for (Node node : this.activeChat.getChildren()) {
                if(((Label) node).getText().equals(memberId)){ 
                    this.activeChat.getChildren().remove(node);
                    break;
                }
            }
        });
    }

    public void setUserName(String myName){
        Platform.runLater(() -> {
            this.usernameLabel.setText(myName);
        });
    }

    public void disconnect(Runnable function){
        closeButton.setOnAction(event -> {
            function.run();
            Platform.exit();
        });
    }
}
