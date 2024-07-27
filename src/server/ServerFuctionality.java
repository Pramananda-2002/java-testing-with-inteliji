package server;

import java.util.ArrayList;
import java.util.List;

public interface ServerFuctionality {
    public List<String> getRelativeSentences(String word);
    public boolean addLine(String sentence);
    public List<String> getAllSentence();
}
