package ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MessageListViewModel implements PropertyChangeListener
{

    StringProperty message;
    ChatModel model;

    public MessageListViewModel(ChatModel model)
    {
        message = new SimpleStringProperty("Start");
        this.model = model;
        this.model.addListener("newMessage", this);
    }

    public StringProperty getMessageProperty()
    {
        return this.message;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getPropertyName().equals("newMessage"))
        {
            message.setValue(event.getNewValue().toString());
        }
    }
}
