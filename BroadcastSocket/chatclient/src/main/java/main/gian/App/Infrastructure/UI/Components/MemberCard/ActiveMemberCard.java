package main.gian.App.Infrastructure.UI.Components.MemberCard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ActiveMemberCard implements Initializable{
    @FXML
    Label memberName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public void changeName(String userId){
        this.memberName.setText(userId);
    }
}
