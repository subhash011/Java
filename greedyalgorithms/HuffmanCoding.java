package greedyalgorithms;

import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class HuffmanNode {
    public Character value;
    public Integer frequency;
    public HuffmanNode lchild, rchild;

    public HuffmanNode(char value, int frequency) {
        this.frequency = frequency;
        this.value = value;
        this.lchild = null;
        this.rchild = null;
    }
}

public class HuffmanCoding {
    public static void findCodes(HuffmanNode root, HashMap<Character, String> hmap, String str) {
        if (root.lchild == null && root.rchild == null) {
            if (root.value != ' ') {
                hmap.put(root.value, str);
                return;
            }
        }
        findCodes(root.lchild, hmap, str + '0');
        findCodes(root.rchild, hmap, str + '1');
    }

    public static HashMap<Character, String> huffmanCoding(char chars[], int freq[]) {
        PriorityQueue<HuffmanNode> qu = new PriorityQueue<>((a, b) -> {
            return a.frequency - b.frequency;
        });
        for (int i = 0; i < freq.length; i++) {
            HuffmanNode nd = new HuffmanNode(chars[i], freq[i]);
            qu.add(nd);
        }
        while (qu.size() != 1) {
            HuffmanNode a = qu.poll();
            HuffmanNode b;
            if (qu.peek() != null) {
                b = qu.poll();
            } else {
                b = null;
            }
            HuffmanNode node = new HuffmanNode(' ', a.frequency + b.frequency);
            node.lchild = a;
            node.rchild = b;
            qu.add(node);
        }
        HuffmanNode root = qu.poll();
        HashMap<Character, String> hmap = new HashMap<>();
        int arr[] = new int[50];
        findCodes(root, hmap, "");
        return hmap;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String str1[] = br.readLine().split(" ");
        String str2[] = br.readLine().split(" ");
        char chars[] = new char[str1.length];
        int freq[] = new int[str1.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = str1[i].charAt(0);
            freq[i] = Integer.parseInt(str2[i]);
        }
        HashMap<Character, String> ans = huffmanCoding(chars, freq);
        for (Character ch : ans.keySet()) {
            System.out.println(ch + " : " + ans.get(ch));
        }
    }
}