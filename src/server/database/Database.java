package server.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {


    final List<String> sentences;


    public List<String> getSentences() {
        return sentences;
    }



    public  Database(){
        sentences = new ArrayList<>();
        refreshList();
//        try {
//            writer= new FileWriter("src/server/database/database.txt");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    public boolean addLine(String line ){
        FileWriter writer;
        try {
            writer= new FileWriter("src/server/database/database.txt", true); /// make sure  previous data didn't deleted
            writer.write(line + System.lineSeparator());
            writer.close();
            sentences.add(line);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private void refreshList(){

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/server/database/database.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNextLine()){
            sentences.add(scanner.nextLine());
        }
        scanner.close();
    }
}
