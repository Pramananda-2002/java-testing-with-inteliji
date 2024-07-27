package server.dataStructure.constantTime;

import java.util.ArrayList;
import java.util.List;

public class SentenceTree {

    List<String> sentences;
    private static SentenceTree instance = null;

    public SentenceTree() {

        sentences= new ArrayList<>();
    }
    public void addSentence(String sentence){
        sentences.add(sentence);
    }
    public int getLastIndex(){
        return sentences.size()-1;
    }
    public List<String> getsentences(){
        return sentences;
    }
    public String getSentence(int index){
        return sentences.get(index);
    }
    public static SentenceTree getInstance(){
        if(instance == null){
            instance = new SentenceTree();
        }
        return instance;
    }
}
