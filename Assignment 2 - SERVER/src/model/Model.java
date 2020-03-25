package model;

public interface Model extends NamedPropertyChangeSubject
{
    public void incrementCounter();
    public int getCounter();
    public void startSendingMessages();
}
