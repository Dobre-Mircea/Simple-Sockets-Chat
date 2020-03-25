package ViewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewViewModel implements PropertyChangeListener
{
    private StringProperty messageField;
    private StringProperty responseLabel;
    private ChatModel model;

    public MainViewViewModel(ChatModel model)
    {
        this.model = model;
        model.addListener("newIP", this);
        model.addListener("newUserNumber", this);
        messageField = new SimpleStringProperty("");
        responseLabel = new SimpleStringProperty("Nothing so far");
    }

    public StringProperty getMessageFieldProperty()
    {
        return this.messageField;
    }

    public StringProperty getResponseLabelProperty()
    {
        return this.responseLabel;
    }

    public void getIP() throws Exception
    {
        model.getIP();
    }

    public void getUserNr() throws Exception
    {
        model.getNewUserCount();
    }

    public void sendMessage() throws Exception {
        model.sendMessage(messageField.getValue().toString());
        messageField.setValue("");
    }


    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        Platform.runLater(()->
        {
            responseLabel.setValue(event.getNewValue().toString());
        });
    }
}
