package server.dataStructure.linearTime;

import java.util.ArrayList;
import java.util.List;

public class WordNode {
    private final String word;
    private int index;
    private List<Integer> sentenceList;

    WordNode(String word, int index){
        this.word = word;
        this.index = index;
        sentenceList= new ArrayList<>();
    }
    WordNode(String word){
        this.word = word;
        this.index = -1;
        sentenceList= new ArrayList<>();
    }
    WordNode(){
        this.word = "";
        this.index = -1;
        sentenceList= new ArrayList<>();
    }

    public List<Integer> getList(){
        return sentenceList;
    }
    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index= index;
    }
    public String getWord(){
        return word;
    }
}
