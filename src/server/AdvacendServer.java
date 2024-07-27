package server;

import server.database.EfficentDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdvacendServer implements ServerFuctionality{
    EfficentDatabase database;

    public AdvacendServer() {
        this.database = new EfficentDatabase(2);
    }

    @Override
    public List<String> getRelativeSentences(String word) {
        System.out.println("Advanced Server");
        List<String> result=new ArrayList<>();
        word = word.replaceAll("\\s+", "");
        List<List<String>> allWord= database.getSentences(word);
        for( List<String> sentences:allWord){
            result.addAll(sentences);
        }
        return result;
    }

    @Override
    public boolean addLine(String sentence) {
        return database.addSentence(sentence, true);

    }

    @Override
    public List<String> getAllSentence() {
        return database.getAllSentence();
    }
}
