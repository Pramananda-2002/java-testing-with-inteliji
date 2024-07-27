package extra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TrieNodeA {
    TrieNodeA[] children;
    boolean isEndOfWord;

    public TrieNodeA() {
        this.children = new TrieNodeA[26]; // 26 letters
        this.isEndOfWord = false;
    }
}

public class WordDictionaryA {
    private TrieNodeA root;
    private Algorithms algorithms;

    public WordDictionaryA() {
        root = new TrieNodeA();
        algorithms = new Algorithms();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNodeA node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNodeA();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Retrieve a word from the trie
    public boolean retrieve(String word) {
        TrieNodeA node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            node = node.children[index];
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord;
    }

    // Find all words within a specified edit distance from the given word
    public List<String> getWordsWithinEditDistance(String word, int maxEditDistance) {
        List<String> results = new ArrayList<>();
        findWordsWithinEditDistance(root, word, "", maxEditDistance, results);
        return results;
    }

    // Helper method to find words within edit distance
    private void findWordsWithinEditDistance(TrieNodeA node, String word, String currentWord, int maxEditDistance, List<String> results) {
        if (node == null) return;

        if (node.isEndOfWord && algorithms.LevenshteinEditDistance(currentWord, word) <= maxEditDistance) {
            results.add(currentWord);
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (node.children[c - 'a'] != null) {
                findWordsWithinEditDistance(node.children[c - 'a'], word, currentWord + c, maxEditDistance, results);
            }
        }
    }

    public static void main(String[] args) {
        WordDictionaryA wordDict = new WordDictionaryA();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/extra/input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNext()){
            wordDict.insert(scanner.nextLine());
        }
        scanner.close();

        scanner = new Scanner(System.in);
        while(true){
            String str = scanner.next();
            System.out.println(wordDict.retrieve(str));
            System.out.println(wordDict.getWordsWithinEditDistance(str, 1));
        }
    }
}



class Algorithms {
    private int matchedCost;
    private int replacedCost;
    private int removeCost;
    private int insertCost;

    Algorithms() {
        matchedCost = 0;
        replacedCost = 1;
        removeCost = 1;
        insertCost = 1;
    }

    Algorithms(int matchedCost, int replacedCost, int removeCost, int insertCost) {
        this.matchedCost = matchedCost;
        this.replacedCost = replacedCost;
        this.removeCost = removeCost;
        this.insertCost = insertCost;
    }

    public int LevenshteinEditDistance(String word1, String word2) {
        final int n = word1.length();
        final int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + matchedCost;
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + replacedCost,
                            Math.min(dp[i - 1][j] + removeCost, dp[i][j - 1] + insertCost));
            }
        }
        return dp[n][m];
    }
}
