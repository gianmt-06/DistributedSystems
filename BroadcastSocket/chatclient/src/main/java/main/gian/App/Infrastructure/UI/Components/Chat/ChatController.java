package main.gian.App.Infrastructure.UI.Components.Chat;

import java.util.ArrayList;

import main.gian.App.Application.Services.SendMessageService;
import main.gian.App.Contracts.Infrastructure.Observer.ObserverInterface;


public class ChatController implements ObserverInterface {
    ChatView chatView;
    SendMessageService sendMessageService;

    public ChatController(
            ChatView chatView,
            SendMessageService sendMessageService) {
        this.chatView = chatView;
        this.sendMessageService = sendMessageService;

        this.chatView.sendMessage(this::sendMessage);
        this.chatView.disconnect(this::disconnect);
    }
    
    @Override
    public boolean update(ArrayList<Object> data) {
        String controlLabel = data.remove(0).toString();
        String member;
        
        switch (controlLabel) {
            case "1":
                disconnect();   
            break;
            case "2":
                String sender = data.get(0).toString();
                String message = data.get(1).toString();
                loadMessage(sender, message);
            break;
            case "*":
                loadAllMembers(data);
            break;
            case "+":
                member = data.get(0).toString();
                loadSingleMember(member);
                break;
            case "-":
                member = data.get(0).toString();
                removeMember(member);
                break;
                
            default:
                System.out.println("No es posible manejar la respuesta - Etiqueta de control inválida");
                break;
        }
        return true;
    }

    public boolean sendMessage(String message) {
        this.sendMessageService.sendMessage(message);
        return true;
    }

    public boolean notifyMessage(String message) {
        this.chatView.showMessage(message);
        return true;
    }

    public boolean notifyMembers(ArrayList<Object> members) {
        this.chatView.bindMembers(members);
        return true;
    }


    @Override
    public boolean loadAllMembers(ArrayList<Object> members) {
        this.chatView.bindMembers(members);
        return true;
    }

    @Override
    public boolean loadSingleMember(String member) {
        this.chatView.loadNewMember(member);
        return true;
    }

    @Override
    public boolean removeMember(String member) {
        this.chatView.removeMember(member);
        return true;
    }

    @Override
    public boolean loadMessage(String senderId, String message) {
        this.chatView.showMessage(senderId + ": " + message);
        return true;
    }

    @Override
    public boolean setUserName(String userName) {
        this.chatView.setUserName(userName);
        return true;
    }


    @Override
    public boolean disconnect() {
        System.out.println("Cerrando");
        this.sendMessageService.closeConnection();
        return true;
    }
}
