package view;

import ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler extends Application
{
    private Stage primaryStage;
    private Stage secoundStage;
    private Scene currentScene;
    private Scene secoundScene;

    private MessageListController messageListController;
    private MainViewController mainViewController;
    private ViewModelFactory factory;

    public ViewHandler(ViewModelFactory factory)
    {
        this.factory = factory;
        this.currentScene = new Scene(new Region());
        this.secoundScene = new Scene(new Region());
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.secoundStage = new Stage();
        //this.currentScene = new Scene(new Region());
        this.openView("main");
    }


    public void openView(String id)
    {
        switch (id)
        {
            case "main":
                Region root = loadMain("MainView.fxml");
                currentScene.setRoot(root);
                String title = "Main Menu - Group 3";
                primaryStage.setTitle(title);
                //primaryStage.setAlwaysOnTop(true);
                primaryStage.setResizable(false);
                primaryStage.setScene(currentScene);
                primaryStage.setWidth(root.getPrefWidth());
                primaryStage.setHeight(root.getPrefHeight());
                primaryStage.show();
                break;

            case "messageList":
                Region root2 = loadMessage("MessageList.fxml");
                secoundScene.setRoot(root2);
                String title2 = "MessageList";
                secoundStage.setTitle(title2);
                secoundStage.setResizable(false);
                secoundStage.setScene(secoundScene);
                secoundStage.setWidth(root2.getPrefWidth());
                secoundStage.setHeight(root2.getPrefHeight());
                secoundStage.show();
                break;
        }

    }

    public void closePrimary()
    {
        primaryStage.close();
    }

    public void closeSecoundary()
    {
        secoundStage.close();
    }


    private Region loadMain(String fxmlFile)
    {
        Region root = null;
        if (mainViewController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, factory.getMainViewViewModel(), root);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            mainViewController.reset();
        }
        return mainViewController.getRoot();
    }


    private Region loadMessage(String fxmlFile)
    {
        Region root = null;
        if (messageListController == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                messageListController = loader.getController();
                messageListController.init(this, factory.getMessageListViewModel(), root);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            messageListController.reset();
        }
        return messageListController.getRoot();
    }



}
