package main.gian.App.Infrastructure.RequestManager;

import java.util.ArrayList;

public interface RequestManagerInterface {
    public boolean loadAllMembers(ArrayList<Object> members);
    public boolean loadSingleMember(String member);
    public boolean removeMember(String member);
    public boolean sendMessage(String message);
    public boolean loadMessage(String senderId, String message);
    public boolean setUserName(String userName);
    public boolean disconnect();
}
