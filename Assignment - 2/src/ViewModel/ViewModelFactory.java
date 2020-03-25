package ViewModel;

import model.ChatModel;

public class ViewModelFactory {
    private MessageListViewModel messageListViewModel;
    private MainViewViewModel mainViewViewModel;

    public ViewModelFactory(ChatModel model)
    {
        messageListViewModel = new MessageListViewModel(model);
        mainViewViewModel = new MainViewViewModel(model);
    }

    public MainViewViewModel getMainViewViewModel()
    {
        return mainViewViewModel;
    }

    public MessageListViewModel getMessageListViewModel()
    {
        return messageListViewModel;
    }
}
