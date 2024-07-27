package frontend;

import server.ServerProvider;

import java.util.List;

public class UIProvider {
    private static UIProvider instance;
    private final ServerProvider serverProvider;
    private UIProvider(){
        serverProvider = new ServerProvider();
    }
    public static synchronized UIProvider getInstance(){
        if(instance == null){
            instance = new UIProvider();
        }
        return instance;
    }
    public boolean addSentence(String sentence){
        return serverProvider.saveSentences(sentence);
    }
    public String getSentences(String word){
        StringBuilder result = new StringBuilder();
        List<String> lines = serverProvider.getSentences(word);
        if(lines.isEmpty()){
            System.out.println("No string found");
            return "No Best Match found ..... :( windows style ";
        }
        for(String line: lines){

            result.append(line).append('\n');
        }

        return result.toString();

    }


}
