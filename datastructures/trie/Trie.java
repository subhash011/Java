package datastructures.trie;

public class Trie {
    static TrieNode root = new TrieNode(52, true);

    public static void main(String[] args) {
        boolean inserted = insertNode("subhash");
        inserted = insertNode("subhash");
        System.out.println(inserted);
    }

    public static boolean insertNode(String value) {
        boolean inserted = false;
        char[] chars = value.toCharArray();
        TrieNode node = root;
        int i = 0;
        int len = chars.length;
        for (char ch : chars) {
            int index = ch - 97;
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(52, i == len - 1);
                inserted = true;
            }
            node = node.children[index];
            i++;
        }
        return inserted;
    }
}
