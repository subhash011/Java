package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LongestCommonSubsequence {
    private static int lcs(String s1, String s2, int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcs(s1, s2, i - 1, j - 1);
        }
        return Integer.max(lcs(s1, s2, i - 1, j), lcs(s1, s2, i, j - 1));

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int ans = lcs(s1, s2, s1.length() - 1, s2.length() - 1);
        System.out.println(ans);
    }

}