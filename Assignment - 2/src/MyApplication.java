import javafx.application.Application;
import javafx.stage.Stage;

import model.ChatModel;
import model.ChatModelManager;
import view.ViewHandler;
import ViewModel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws Exception {
    ChatModel model = new ChatModelManager();
    ViewModelFactory factory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(factory);
    view.start(primaryStage);
    view.openView("messageList");
  }
}
