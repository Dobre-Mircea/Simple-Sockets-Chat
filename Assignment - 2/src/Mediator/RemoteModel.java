package Mediator;


import model.PropertyChangeStuff;

public interface RemoteModel extends PropertyChangeStuff
{
    public void connect() throws Exception;
    public void disconnect() throws Exception;
    public void requestIP() throws Exception;
    public void requestUserNR() throws Exception;
    public void waitForMessages() throws Exception;
    public void sendMessage(String message) throws Exception;
}
