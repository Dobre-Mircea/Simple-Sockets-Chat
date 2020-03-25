import mediator.ClientConnector;
import model.Model;
import model.ModelManager;


import java.io.IOException;

public class Main
{
    public static void main(String args[]) throws IOException {
        Model model = new ModelManager();

        // simple console view
        ClientConnector connect = new ClientConnector(model);
        Thread t = new Thread(connect);
        model.startSendingMessages();
        t.start();
    }
}