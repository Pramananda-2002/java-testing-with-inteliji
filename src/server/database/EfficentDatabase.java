package server.database;

import server.dataStructure.constantTime.SentenceTree;
import server.dataStructure.constantTime.WordTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EfficentDatabase implements  DatabaseFunctionality{

    SentenceTree sentenceTree;
    WordTree tree;
    private final int maxEditDistance;

    public EfficentDatabase( int maxEditDistance) {

        sentenceTree = new SentenceTree();
        tree = new WordTree(26);
        this.maxEditDistance= maxEditDistance;
        initializeDatabase();
    }

    @Override
    public List<String> getAllSentence() {
        return sentenceTree.getsentences() ;
    }
    private List<String> indexToString(List<Integer> indexList){
        List<String> result = new ArrayList<>();
        for(Integer index: indexList){
            result.add(sentenceTree.getSentence(index));
        }
        return result;

    }

    @Override
    public List<List<String>> getSentences(String word) {
        System.out.println("reach Database");
        List<List<Integer>> resultIndex = tree.getSentenceIndexList(word, maxEditDistance);
        List<List<String>> result= new ArrayList<>();
        for(List<Integer> index:resultIndex){
            result.add(indexToString(index));
        }

        return result;
    }

    @Override
    public boolean addSentence(String line, boolean isFileWrite) {

        sentenceTree.addSentence(line);

        String[] words=splitStringToWords(line);
        for(String word:words){
            if(isFileWrite){
                fileWrite(line);
            }

            try {
                tree.insert(word, sentenceTree.getLastIndex());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        tree.printTree();
        return true;

    }
    private static String[] splitStringToWords(String input) {
        input = input.trim();
        if (input.length() > 0 && !Character.isLetterOrDigit(input.charAt(input.length() - 1))) {
            input = input.substring(0, input.length() - 1);
        }
        String[] words = input.split("\\s+");
        return words;
    }
    private boolean fileWrite(String line ){
        FileWriter writer;
        try {
            writer= new FileWriter("src/server/database/database.txt", true); /// make sure  previous data didn't deleted
            writer.write(line + System.lineSeparator());
            writer.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void initializeDatabase(){

        Scanner scanner= null;
        try {
            scanner = new Scanner(new File("src/server/database/database.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);
            addSentence(line, false);

        }

        scanner.close();
    }
}
