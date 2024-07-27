package server;

import java.util.List;

public class ServerProvider {
    private final ServerFuctionality server;
    public ServerProvider(){

        server = new AdvacendServer();
    }
    public boolean saveSentences(String sentence){
        return server.addLine(sentence);
    }
    public List<String> getSentences(String word){
//        System.out.println("in server Provider"+word);
        return server.getRelativeSentences(word);
    }
}
