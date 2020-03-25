package mediator;


import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, PropertyChangeListener
{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Model model;
    private boolean stopCondition;

    public ClientHandler(Socket socket, Model model) throws IOException
    {
        this.model = model;
        model.addListener("messageAll", this);
        this.socket = socket;
        stopCondition = true;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run()
    {
        (new Thread()
        {
            public void run()
            {
                String message = null;
                while(stopCondition)
                {
                    try {
                        message = reader.readLine();
                        if(message.equals("MUIEPSD-requestIP"))
                            writer.println("NEW IP ADRESS " + socket.getInetAddress().getHostAddress());
                        if(message.equals("MUIEPSD-requestUserNR"))
                            writer.println("NEW USER COUNT " + model.getCounter());
                        System.out.println(socket.getInetAddress().getHostAddress() + " - " + message);
                    } catch (IOException e) {
                        if(socket.isClosed())
                        {
                            stopCondition = false;
                            System.out.println("ne-am dat dracu");
                        }
                    }
                }
            }

        }).start();





    }

    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        writer.println(event.getNewValue());
    }
}
