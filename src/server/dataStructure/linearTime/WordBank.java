package server.dataStructure.linearTime;

import java.util.ArrayList;
import java.util.List;

public class WordBank {
    private final List<WordNode> bank;
    WordBank(){
        bank= new ArrayList<>();
    }
    public void insert(String word){
        bank.add(new WordNode(word, bank.size()));
        
    }
    public List<Integer> find(String word){
        for(WordNode wordNode:bank){
            if(wordNode.getWord().equals(word)){
                return wordNode.getList();
            }
        }
        return new ArrayList<>();
    }
    
}


