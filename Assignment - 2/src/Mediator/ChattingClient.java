package Mediator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChattingClient implements RemoteModel
{
    private PropertyChangeSupport support;

    private String HOST = "localhost";
    private int PORT = 9876;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean stopCondition;

    public ChattingClient(String host, int port)
    {
        this.HOST = host;
        this.PORT = port;
        stopCondition = true;
        support = new PropertyChangeSupport(this);
    }
    public ChattingClient()
    {
        support = new PropertyChangeSupport(this);
        stopCondition = true;
    }


    @Override
    public void connect() throws Exception
    {
        socket = new Socket(HOST, PORT);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }



    @Override
    public void disconnect() throws Exception
    {
        reader.close();
        writer.close();
        socket.close();
    }

    @Override
    public void requestIP() throws Exception
    {
        writer.println("MUIEPSD-requestIP");
    }

    @Override
    public void requestUserNR() throws Exception
    {
        writer.println("MUIEPSD-requestUserNR");
    }

    @Override
    public void waitForMessages() throws Exception
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
                        if(message.startsWith("NEW USER COUNT "))
                            support.firePropertyChange("newUserCount", "", message);
                        else if(message.startsWith("NEW IP ADRESS "))
                            support.firePropertyChange("newIP", "", message);
                        else support.firePropertyChange("newMessage", "", message);
                    } catch (IOException e) {
                        if(socket.isClosed())
                        {
                            System.out.println("NU PRIMESC BA");
                            stopCondition = false;
                        }
                        }
                }
            }

        }).start();
    }

    @Override
    public void sendMessage(String message) throws Exception
    {
        writer.println(message);
    }


    @Override
    public void addListener(String propertyName, PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(propertyName, listener);
    }
}
