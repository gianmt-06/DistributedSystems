package main.gian.App.Infrastructure.UI.Components.Register;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.gian.MainFX;

public class RegisterController implements Initializable {

    @FXML private TextField nameField;
    @FXML private Button nextButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nextButton.setOnAction(event -> {
            try {
                String name = this.nameField.getText();
                MainFX.launchChat(name);
            } catch (IOException e) {
                System.out.println("Error get name");
                e.printStackTrace();
            }
        });
    }    
}
