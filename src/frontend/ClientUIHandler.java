package frontend;

public class ClientUIHandler implements  Runnable {
    private ClientUI clientUI;

    ClientUIHandler(){


    }

    @Override
    public void run() {
        clientUI= new ClientUI();
    }
}
