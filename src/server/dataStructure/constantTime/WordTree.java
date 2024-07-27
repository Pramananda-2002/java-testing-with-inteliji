package server.dataStructure.constantTime;

import java.util.ArrayList;
import java.util.List;

public class WordTree {
    private CharacterNode root;
    private final int totalSupportedChacter;

    public WordTree(int totalSupportedChacter){
        this.totalSupportedChacter= totalSupportedChacter;
        root = new CharacterNode(totalSupportedChacter);

        root.setParent(null);
        System.out.println(root.getChildren().size());
    }

    public void insert(String word ,int sentenceIndex) throws Exception {
        CharacterNode node = root;
//        System.out.println("reach here");
//        if(root.getChildren()==null){
//            System.out.println("root child is null");
//        }
//        else{
//            System.out.println("root excuted");
//        }
        for(char c: word.toCharArray()){
            int index = c-'a';
            if(!(index<26 && index>=0)){
                throw new Exception("Support only lower case a to z letter");
            }
            System.out.println(node.getChildren().size());
            if(node != null){
                System.out.println("here");
                CharacterNode characterNode= new CharacterNode(totalSupportedChacter);

//                node.getChildren().get(index) = new CharacterNode(totalSupportedChacter);
                node.initializeSentenceMap(node, index);
//                node.getChildren().set(index, characterNode);

                node = node.getChildren().get(index);
            }

        }
        node.setEndOfWord(true);
        node.addSentences(sentenceIndex);
    }

    public boolean retrieve(String word){
        CharacterNode node = root;
        for(char c:word.toCharArray()){
            int index=c-'a';
            node = node.getChildren().get(index);
            if(node == null)return false;
        }
        return node.isEndOfWord();
    }
    public CharacterNode getRetrivedNode(String word){
        CharacterNode node = root;
        System.out.println("search index");
        for(char c:word.toCharArray()){
            int index=c-'a';
            System.out.println(index);
            node = node.getChildren().get(index);
            if(node==null) System.out.println("is it NULL");
            if(node == null)return null;
        }
        return node;
    }
    public List<List<Integer>> getSentenceIndexList(String word, int maximumEditDistance ){
        System.out.println("tree");
        List<List<Integer>> indexList = new ArrayList<>();
        CharacterNode currentNode = getRetrivedNode(word);
        System.out.println("indexing");
        System.out.println(currentNode.getCharacterIndexValue());
        for(int currentEditDistance=0;currentEditDistance<=maximumEditDistance;currentEditDistance++){
            List<Integer> singleIndexList = new ArrayList<>();

            getListWithSpecifiedEditDistance(currentNode, currentEditDistance, singleIndexList);
            printIndex(singleIndexList);
            indexList.add(singleIndexList);
        }

        return indexList;
    }
    private  void getListWithSpecifiedEditDistance(CharacterNode currentNode, int editDistance, List<Integer> indexList){
        if(currentNode ==null || editDistance <0)return ;
        if(currentNode.isEndOfWord() && editDistance==0){
            System.out.println("get word");
            for(int index: currentNode.getRelatedSentences()){
                System.out.println("i am back");
                System.out.println(index);
                indexList.add(index);
                printIndex(indexList);
            }
        }
        printTree();
        if(currentNode== root){
            System.out.println("it is root");
        }
        else{
            System.out.println("it is not a root");
        }
        for(CharacterNode child: currentNode.getChildren()){
            System.out.println("it is child");

            if(child !=null){
                System.out.println("not null a child");
                System.out.println(child.getCharacterIndexValue());
                getListWithSpecifiedEditDistance(child, editDistance-1, indexList);
                System.out.println("No child found");
            }


        }
        while(currentNode.getParent()!=root){
            getListWithSpecifiedEditDistance(currentNode.getParent(), editDistance-1, indexList);
        }
    }
    public void printTree(){
        print(root);
        System.out.println();
    }
    private void print(CharacterNode node){
        if(node==null)return;
        node.printNode(node, root);

        for(CharacterNode child:node.getChildren()){
            print(child);
        }


    }
    private void printIndex(List<Integer> index){
        for(Integer i:index){
            System.out.print(i+" ");

        }
        System.out.println();

    }



    
}
