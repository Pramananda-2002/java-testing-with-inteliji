import frontend.ServerUIHandler;
import server.Server;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ServerUIHandler serverUIHandler = new ServerUIHandler();
        serverUIHandler.run();
    }
}
