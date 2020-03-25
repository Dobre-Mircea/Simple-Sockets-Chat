package model;

public interface ChatModel extends PropertyChangeStuff
{
    public void getNewUserCount() throws Exception;
    public void getIP() throws Exception;
    public void sendMessage(String message) throws Exception;
}
