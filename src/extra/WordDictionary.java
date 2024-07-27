package extra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TrieNode {
    TrieNodeA[] children;
    boolean isEndOfWord;

    public TrieNode() {
        this.children = new TrieNodeA[26]; // 26 letters
        this.isEndOfWord = false;
    }
}

public class WordDictionary {
    private TrieNodeA root;

    public WordDictionary() {
        root = new TrieNodeA();
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
        findWordsWithinEditDistance(root, word, "", 0, 0, maxEditDistance, results);
        return results;
    }

    // Helper method to find words within edit distance
    private void findWordsWithinEditDistance(TrieNodeA node, String word, String currentWord, int index, int currentEditDistance, int maxEditDistance, List<String> results) {
        if (node == null) return;

        if (currentEditDistance > maxEditDistance) return;

        if (index == word.length()) {
            if (node.isEndOfWord && currentEditDistance <= maxEditDistance) {
                results.add(currentWord);
            }
            return;
        }

        char c = word.charAt(index);

        // Try to match current character
        if (node.children[c - 'a'] != null) {
            findWordsWithinEditDistance(node.children[c - 'a'], word, currentWord + c, index + 1, currentEditDistance, maxEditDistance, results);
        }

        // Try insertion (moving to the next character without matching)
        if (currentEditDistance < maxEditDistance) {
            for (char nc = 'a'; nc <= 'z'; nc++) {
                if (nc != c) {
                    if (node.children[nc - 'a'] != null) {
                        findWordsWithinEditDistance(node.children[nc - 'a'], word, currentWord + nc, index, currentEditDistance + 1, maxEditDistance, results);
                    }
                }
            }

            // Try deletion (skipping the current character)
            findWordsWithinEditDistance(node, word, currentWord, index + 1, currentEditDistance + 1, maxEditDistance, results);
        }

        // Try replacement (replacing current character with another character)
        if (currentEditDistance < maxEditDistance) {
            for (char nc = 'a'; nc <= 'z'; nc++) {
                if (nc != c) {
                    if (node.children[nc - 'a'] != null) {
                        findWordsWithinEditDistance(node.children[nc - 'a'], word, currentWord + nc, index + 1, currentEditDistance + 1, maxEditDistance, results);
                    }
                }
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

//        wordDict.insert("hello");
//        wordDict.insert("hella");
//        wordDict.insert("hell");
//        wordDict.insert("hellos");
//        wordDict.insert("hero");
//        wordDict.insert("help");
        scanner = new Scanner(System.in);
        while(true){
            String str= scanner.next();
            System.out.println(wordDict.retrieve(str));
            System.out.println(wordDict.getWordsWithinEditDistance(str, 1));
        }

//        System.out.println(wordDict.retrieve("hello"));  // Output: true
//        System.out.println(wordDict.retrieve("world"));  // Output: false
//
//        List<String> wordsWithinEditDistance = wordDict.getWordsWithinEditDistance("hello", 2);
//        System.out.println(wordsWithinEditDistance);  // Output: [hella, hell, hellos, hero, help]
    }
}
