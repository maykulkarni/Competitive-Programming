package MyImplementations;

import java.util.*;

/**
 * Created by Mayur Kulkarni 15/6/16.
 */

class TrieNode {
    char c;
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {

    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Mayur");
        trie.insert("Mayu");
        trie.insert("Mayzz");
        System.out.println(trie.autocomplete("Ma"));
    }

    public void insert(String word) {
        Map<Character, TrieNode> children = root.children;
        TrieNode walk;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (children.containsKey(currChar)) {
                walk = children.get(currChar);
            } else {
                walk = new TrieNode(currChar);
                children.put(currChar, walk);
            }
            children = walk.children;
            if (i == word.length() - 1) {
                walk.isLeaf = true;
            }
        }
    }

    public boolean search(String word) {
        Map<Character, TrieNode> children = root.children;
        TrieNode walk = null;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (children.containsKey(currChar)) {
                walk = children.get(currChar);
                children = walk.children;
            } else {
                return false;
            }
        }
        if (walk.isLeaf)
            return true;
        else
            return false;
    }

    public boolean isPrefix(String word) {
        Map<Character, TrieNode> children = root.children;
        TrieNode walk = null;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (children.containsKey(currChar)) {
                walk = children.get(currChar);
                children = walk.children;
            } else {
                return false;
            }
        }
        return true;
    }

    public TrieNode getNode(String word) {
        List<String> list = new ArrayList<String>();
        Map<Character, TrieNode> children = root.children;
        TrieNode currNode = null;
        for (int i = 0; i < word.length(); i++) {
            if (children.containsKey(word.charAt(i))) {
                currNode = children.get(word.charAt(i));
                children = currNode.children;
            } else {
                System.out.println("Word not present in Trie!");
            }
        }
        return currNode;
    }

    public List<String> autocomplete(String word) {
        Stack<AutoComplete> stack = new Stack<AutoComplete>();
        List<String> answers = new ArrayList<String>();
        stack.push(new AutoComplete(getNode(word), word));
        TrieNode currNode;
        while (!stack.isEmpty()) {
            AutoComplete currAC = stack.pop();
            String currWord = currAC.word;
            currNode = currAC.tnode;
            if (currNode.isLeaf)
                answers.add(currWord);
            for (Map.Entry entry : currNode.children.entrySet()) {
                stack.push(new AutoComplete((TrieNode) entry.getValue(), currWord + entry.getKey()));
            }
        }
        return answers;
    }

    private class AutoComplete {
        TrieNode tnode;
        String word;

        AutoComplete(TrieNode tnode, String word) {
            this.tnode = tnode;
            this.word = word;
        }
    }
}













