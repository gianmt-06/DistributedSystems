package main.gian.App.Infrastructure.RequestManager;

import java.util.ArrayList;

public class RequestManager implements RequestManagerInterface {

    public void manageData(ArrayList<Object> data){
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
    }

    @Override
    public boolean loadAllMembers(ArrayList<Object> members) {

        return true;
    }

    @Override
    public boolean loadSingleMember(String member) {
        throw new UnsupportedOperationException("Unimplemented method 'loadSingleMember'");
    }

    public boolean loadMessage(String senderId, String message){
        throw new UnsupportedOperationException("Unimplemented method 'loadSingleMember'");

    }

    @Override
    public boolean sendMessage(String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public boolean setUserName(String userName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUserName'");
    }

    @Override
    public boolean disconnect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }

    @Override
    public boolean removeMember(String member) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }
    
}
