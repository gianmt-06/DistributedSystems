package main.gian.App.Application.Services;

import main.gian.App.Infrastructure.Repostitory.ChatRepository;

public class SendMessageService {
    ChatRepository repository;

    public SendMessageService(ChatRepository repository) {
        this.repository = repository;
    }

    public void sendMessage(String message){
        this.repository.sendMessage(message);
    }

    public void closeConnection(){
        this.repository.close();
    }
}
