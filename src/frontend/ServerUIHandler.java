package frontend;

public class ServerUIHandler implements Runnable{
    private ServerUI serverUI ;
    @Override
    public void run() {
        serverUI = new ServerUI();
    }
}
