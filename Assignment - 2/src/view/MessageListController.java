package view;

import ViewModel.MainViewViewModel;
import ViewModel.MessageListViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class MessageListController
{
    @FXML private ListView messageList;

    private ViewHandler viewHandler;
    private MessageListViewModel model;
    private Region root;

    public MessageListController()
    {
    }

    public void init(ViewHandler viewHandler, MessageListViewModel model, Region root)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;

        this.model.getMessageProperty().addListener((observable, oldValue, newValue) ->
                {
                    Platform.runLater(()->
                    {
                        messageList.getItems().add(newValue.toString());
                    });
                }
                );
    }

    public void reset() {
    }

    public Region getRoot()
    {
        return this.root;
    }
}
