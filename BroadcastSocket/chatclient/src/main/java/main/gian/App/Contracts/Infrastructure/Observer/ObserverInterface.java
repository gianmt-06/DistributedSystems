package main.gian.App.Contracts.Infrastructure.Observer;

import java.util.ArrayList;

public interface ObserverInterface {
    public boolean update(ArrayList<Object> data);
    
    public boolean loadAllMembers(ArrayList<Object> members);
    public boolean loadSingleMember(String member);
    public boolean removeMember(String member);
    public boolean sendMessage(String message);
    public boolean loadMessage(String senderId, String message);
    public boolean setUserName(String userName);
    public boolean disconnect();
}
