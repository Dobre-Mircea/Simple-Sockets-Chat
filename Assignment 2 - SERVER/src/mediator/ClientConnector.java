package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnector implements Runnable
{
    final int PORT = 9876;
    boolean running;
    ServerSocket welcomeSocket;
    Model model;

    public ClientConnector(Model model) throws IOException
    {
        this.model = model;
        running = false;
    }

    @Override
    public void run()
    {
        running = true;
        try {
            welcomeSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            Socket socket = null;
            try {
                System.out.println("Waiting for client");
                socket = welcomeSocket.accept();
                System.out.println("Client connected");
                model.incrementCounter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ClientHandler c = null;
            try {
                c = new ClientHandler(socket, model);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread t = new Thread(c);
            t.setDaemon(true);
            t.start();
        }

    }
}
