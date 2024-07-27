package server;

import server.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Server {
    private List<String> lines;
    private Database database;
    public List<String> getRelativeSentences(String word){
        word =word.replaceAll("\\s+", "");
//        System.out.println("in server "+word);
        List<String> resultSentences = new ArrayList<>();
        for(String line: lines){
            if(isMatchWord(word, line))resultSentences.add(line);
        }
//        if(resultSentences.isEmpty()){
//            System.out.println("result empty");
//        }
//        else{
//            for(String sen: resultSentences){
//                System.out.println(sen);
//            }
//        }
        return resultSentences;
    }
    public boolean addLine(String sentence){
        return database.addLine(sentence);
    }
    public Server(){
        lines = new ArrayList<>();
        database = new Database();
        lines = database.getSentences();

    }



    /// general provide sentences without algorithm


    private boolean isMatchWord(String targetWord, String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        while (stringTokenizer.hasMoreTokens()) {
            String word = stringTokenizer.nextToken();
//            System.out.println(word + "    " + targetWord + " " + word.equals(targetWord));
            if (word.equals(targetWord)) {
                return true;
            }
        }
        return false;
    }


}
