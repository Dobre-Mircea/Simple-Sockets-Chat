package model;

import Mediator.ChattingClient;
import Mediator.RemoteModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel, PropertyChangeListener
{
    private PropertyChangeSupport support;
    private RemoteModel chattingCLient;

    public ChatModelManager() throws Exception
    {
        support = new PropertyChangeSupport(this);
        chattingCLient = new ChattingClient();
        chattingCLient.addListener("newMessage", this);
        chattingCLient.addListener("newIP", this);
        chattingCLient.addListener("newUserCount", this);
        chattingCLient.connect();
        chattingCLient.waitForMessages();
    }


    @Override
    public void addListener(String propertyName, PropertyChangeListener listener)
    {
        this.support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener)
    {
        this.support.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void getNewUserCount() throws Exception
    {
        chattingCLient.requestUserNR();
    }

    @Override
    public void getIP() throws Exception
    {
        chattingCLient.requestIP();
    }

    @Override
    public void sendMessage(String message) throws Exception
    {
        chattingCLient.sendMessage(message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getPropertyName().equals("newUserCount"))
            support.firePropertyChange("newUserNumber", "3", event.getNewValue());
        else  if(event.getPropertyName().equals("newIP"))
            support.firePropertyChange("newIP", "3", event.getNewValue());
        else support.firePropertyChange("newMessage", "", event.getNewValue());
    }
}
