package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RodCutting {
    private static int RodCuttingDP(int[] profit, int[] lengths, int length) {
        // rods of cut-length i produces profit of p[i-1] (i != 0)
        // find the cuts which accounts to total length 'length' with max profit
        // idea : totprofit = max(profit[i] + subproblem[n-i]) i can be zero
        int pr[] = new int[length + 1];
        Arrays.fill(pr, Integer.MIN_VALUE);
        pr[0] = 0;// subproblem with zero length cut
        for (int i = 1; i <= length; i++) {// iterating over subproblems of all lengths
            int temp = Integer.MIN_VALUE;
            int j;
            for (j = 1; j <= i; j++) {// for each subproblem findin soln
                temp = Integer.max(temp, profit[j - 1] + pr[i - j]);// pr[i-j] is the soln to the subproblem of length i
            }
            pr[j - 1] = temp;
        }
        return pr[length];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("./input.txt")));
        int n = Integer.parseInt(br.readLine());
        int profit[] = new int[n];
        int lengths[] = new int[n];
        String str1[] = br.readLine().split(" ");
        String str2[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            profit[i] = Integer.parseInt(str1[i]);
            lengths[i] = Integer.parseInt(str2[i]);
        }
        int length = Integer.parseInt(br.readLine());
        br.close();
        for (int i = 0; i <= 10; i++) {
            int ans = RodCuttingDP(profit, lengths, i);
            System.out.println(ans);
        }

    }

}