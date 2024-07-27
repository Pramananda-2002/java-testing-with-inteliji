package server.dataStructure.constantTime;


///   I want to make own tree structure instead of  library hash map. though map will better for this I think.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterNode {
//    private final int level ;
//    private final int reservedCharNumber;
//    private final int childCount;
    private Set<Integer> relatedSentences;
    private List<CharacterNode> children;
    private CharacterNode parent;
    private boolean isEndOfWord;
    private int childSize =26;
    private int characterIndexValue;

    private String word;

    
//    public ChracterNode(int level, int reservedCharNumber) {
//        this.level = level;
//        this.reservedCharNumber = reservedCharNumber;
//        this.childCount = getLog(reservedCharNumber, level);
//
//        isEndOfWord= false;
//        relatedSentences=new HashSet<>();
//
//
//
//
//    }
    public int getCharacterIndexValue() {
        return characterIndexValue;
    }

    public String getWord() {
        return word;
    }
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;

    }

    public List<CharacterNode> getChildren() {
        return children;
    }

    public void setChildren(List<CharacterNode> children) {
        this.children = children;
    }

    public void setParent(CharacterNode parent) {
        this.parent = parent;
    }

    public Set<Integer> getRelatedSentences() {
//        System.out.println("reach here");
//        System.out.println(relatedSentences.size());
//        List<Integer> result =new ArrayList<>();
////        int [] sentencesIndex=new int[relatedSentences.size()];
////        int i=0;
//        for(Integer senIndex:relatedSentences){
//            System.out.println(senIndex);
////            sentencesIndex[i]=senIndex;
////            i++;
//            result.add(senIndex);
//        }
//        return result;
        return relatedSentences;
    }
    public void rootInitializer(){
        this.parent= null;

    }


    public void initializeSentenceMap(CharacterNode parent,int position){
        parent.getChildren().set(position, new CharacterNode(childSize));
        this.parent =parent;
        this.characterIndexValue= position;
    }
    public void addSentences(int i){
        relatedSentences.add(i);
    }

    public CharacterNode getParent() {
        return parent;
    }
    public void printNode(CharacterNode node, CharacterNode root){
        if(node.getParent()!=root){
            if(node.isEndOfWord()){
                System.out.print("["+indexToString(node.getParent())+", "+ indexToString(node) +", "+makeWord(node)+"]");
            }
            else {
                System.out.print("["+indexToString(node.getParent())+", "+ indexToString(node) +"]");
            }
            System.out.print(" ");
        }

    }
    private String indexToString(CharacterNode node){
        if(node==null)return "";
        return Character.toString('a'+ node.getCharacterIndexValue());
    }

    public CharacterNode(int childSize) {
//        this.level = 1;
//        this.reservedCharNumber = reservedCharNumber;
//        this.childCount = getLog(reservedCharNumber, level);
        isEndOfWord= false;
        relatedSentences=new HashSet<>();
        this.childSize = childSize;
        children= new ArrayList<>(childSize);
//        parent first initialized then come to child so no need to initialized parent




    }

    private int getLog(int number, int base){

        if (base <= 1 || number <= 0) {
            throw new IllegalArgumentException("Base must be greater than 1 and number must be positive.");
        }
        double logValue = Math.log(number) / Math.log(base);
        return (int) Math.ceil(logValue);


    }
    public String makeWord(CharacterNode node){
        StringBuilder word= new StringBuilder();
        CharacterNode characterNode= node;
        while(characterNode.getParent() != null){
            word.append(indexToString(characterNode));
            characterNode= characterNode.getParent();
        }
        return word.reverse().toString();

    }

}
