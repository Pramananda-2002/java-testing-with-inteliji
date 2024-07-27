package server.dataStructure.linearTime;

import java.util.ArrayList;
import java.util.List;

public class SentanceBank {
    private List<String> bank;

    SentanceBank(){
        bank= new ArrayList<>();
    }
    
    public void insert(String sentence){
        bank.add(sentence);
        
    }
    public String find(int index) {
        if (index >= bank.size() || index < 0) {
            return "";
        }
        return bank.get(index);
    }
}
