package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LongestCommonSubstring {
    private static int lcs(char[] X, char[] Y, int m, int n) {
        int LCStuff[][] = new int[m + 1][n + 1];
        int result = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    LCStuff[i][j] = 0;
                else if (X[i - 1] == Y[j - 1]) {
                    LCStuff[i][j] = LCStuff[i - 1][j - 1] + 1;
                    result = Integer.max(result, LCStuff[i][j]);
                } else
                    LCStuff[i][j] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String s1 = br.readLine();
        String s2 = br.readLine();
        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();
        int ans = lcs(X, Y, s1.length(), s2.length());
        System.out.println(ans);
    }
}