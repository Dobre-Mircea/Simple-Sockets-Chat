package view;

import ViewModel.MainViewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


public class MainViewController
{
    @FXML private Label responseLabel;
    @FXML private TextField messageField;

    MainViewViewModel model;
    private Region root;
    private ViewHandler viewHandler;

    public MainViewController()
    {

    }

    public void init(ViewHandler viewHandler, MainViewViewModel model, Region root)
    {
        this.model = model;
        this.viewHandler = viewHandler;
        this.root = root;

        this.messageField.textProperty().bindBidirectional(model.getMessageFieldProperty());
        this.responseLabel.textProperty().bind(model.getResponseLabelProperty());
    }

    public Region getRoot()
    {
        return root;
    }

    public void numberButton() throws Exception {
        model.getUserNr();
    }

    public void sendButton() throws Exception {
        model.sendMessage();
    }

    public void ipButton() throws Exception {
        model.getIP();
    }

    public void reset()
    {

    }
}
