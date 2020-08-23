package datastructures.trie;

public class TrieNode {
    TrieNode[] children;
    boolean isEow;

    public TrieNode(int size, boolean isEow) {
        this.children = new TrieNode[size];
        this.isEow = isEow;
    }
}
