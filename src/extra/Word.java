package extra;

import server.dataStructure.constantTime.Algorithms;

public class Word {
    private String word = null;
    private Algorithms algorithms;

    Word(String word) {
        this.word = word;
        algorithms = new Algorithms();
    }

    public Integer getDisffrence(String str) {
        Integer res = 0;
        res = algorithms.LevenshteinEditDistance(word, str);
        return res;
    }
}
