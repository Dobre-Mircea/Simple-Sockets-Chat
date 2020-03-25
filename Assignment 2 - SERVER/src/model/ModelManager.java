package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Scanner;

public class ModelManager implements Model
{
    private PropertyChangeSupport support;
    private int counter;
    private boolean stopCondition;
    private Scanner input;

    public ModelManager()
    {
        support = new PropertyChangeSupport(this);
        input = new Scanner(System.in);
        stopCondition = true;
    }

    public void startSendingMessages()
    {
        (new Thread()
        {
            public void run()
            {
                String message = null;
                while(stopCondition)
                {
                    try {
                        System.out.println("Type a new message to send to all clients:");
                        String aux = input.nextLine();
                        support.firePropertyChange("messageAll", " ", aux);
                    } catch (Exception e) {
                        stopCondition = false;
                    }
                }
            }

        }).start();
    }


    public void incrementCounter()
    {
        this.counter++;
    }

    public int getCounter()
    {
        return this.counter;
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener)
    {
        this.support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener)
    {
        this.support.removePropertyChangeListener(propertyName, listener);
    }
}
