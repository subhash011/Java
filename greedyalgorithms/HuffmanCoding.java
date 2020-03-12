package greedyalgorithms;

import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HuffmanCoding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String str1[] = br.readLine().split(" ");
        String str2[] = br.readLine().split(" ");
        int chars[] = new int[str1.length];
        int freq[] = new int[str1.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Integer.parseInt(str1[i]);
            freq[i] = Integer.parseInt(str1[i]);
        }

    }
}