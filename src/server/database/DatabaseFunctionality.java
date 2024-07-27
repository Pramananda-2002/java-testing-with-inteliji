package server.database;

import java.util.List;

public interface DatabaseFunctionality {
    List<String> getAllSentence();
    List<List<String>> getSentences(String word);
    boolean addSentence(String line, boolean isFileWrite);

}
