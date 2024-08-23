package main.gian.App.Infrastructure.Socket.RequestManager;

import java.util.ArrayList;

import main.gian.App.Infrastructure.Socket.ServerSocket.ConnectionInterface;

public class RequestManager implements RequestManagerInterface {

    @Override
    public String getPayload(ArrayList<Object> data) {
        String message = data.get(1).toString(); 
        return message;
    }

    public boolean closeCommunication(ArrayList<Object> data){
        String option = data.get(0).toString(); 
        return !option.equals("1");
    }

    /**
     * Message: ["2 ", senderId, message, 0]
     */
    @Override
    public ArrayList<Object> getMessageResponse(ArrayList<Object> data, String senderId) {
        ArrayList<Object> response = new ArrayList<Object>();
        String message = data.get(0).toString();

        response.add("2"); //-> New message
        response.add(senderId);
        response.add(message);
        response.add(0);

        return response;
    }

    /**
     * OneConnection: ["+", memberId, 0]
     */
    public ArrayList<Object> notifyOneConnection(String member) {
        ArrayList<Object> response = new ArrayList<Object>();
        
        response.add("+"); //-> New connection
        response.add(member);
        response.add(0);

        return response;
    }

    /**
     * ConnectionList: ["*", firstMemberId, secondMemberID, ..., 0]
     */
    @Override
    public ArrayList<Object> getMemberListResponse(ArrayList<ConnectionInterface> connections) {
        ArrayList<Object> response = new ArrayList<Object>();
        
        response.add("*");

        for (ConnectionInterface connection : connections) {
            response.add(connection.getConnectionId());
        }

        response.add(0);

        return response;
    }

    /**
     * RemoveClient: ["-", memberId, 0]
     */
    public ArrayList<Object> getRemoveClientResponse(String member){
        ArrayList<Object> response = new ArrayList<Object>();

        response.add("-");
        response.add(member);
        response.add(0);

        return response;
    };
    
    public ArrayList<Object> closeClientResponse(String member){
        ArrayList<Object> response = new ArrayList<Object>();

        response.add("1");
        response.add(0);

        return response;
    }

}
