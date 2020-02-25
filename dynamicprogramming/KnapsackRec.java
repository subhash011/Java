package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KnapsackRec {
    private static int knapsackRecursive(int[] profit, int[] weights, int weight, int n) {
        if (n == 0 || weight == 0) {
            return 0;
        }
        if (weights[n - 1] > weight) {
            return knapsackRecursive(profit, weights, weight, n - 1);
        }
        return Integer.max(knapsackRecursive(profit, weights, weight, n - 1),
                profit[n - 1] + knapsackRecursive(profit, weights, weight - weights[n - 1], n - 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        int n = Integer.parseInt(br.readLine());
        int profit[] = new int[n];
        int weights[] = new int[n];
        String str1[] = br.readLine().split(" ");
        String str2[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            profit[i] = Integer.parseInt(str1[i]);
            weights[i] = Integer.parseInt(str2[i]);
        }
        int weight = Integer.parseInt(br.readLine());
        br.close();
        int ans = knapsackRecursive(profit, weights, weight, n);
        System.out.println(ans);
    }

}