package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KnapsackDP {
    private static int knapsackDP(int[] profit, int[] weights, int weight, int n, int knap[][]) {

        for (int j = 0; j <= weight; j++) {
            for (int i = 0; i <= n; i++) {
                if (i == 0 || j == 0) {
                    knap[i][j] = 0;
                } else if (weights[i - 1] <= j) {
                    knap[i][j] = Integer.max(knap[i - 1][j - weights[i - 1]] + profit[i - 1], knap[i - 1][j]);
                } else {
                    knap[i][j] = knap[i - 1][j];
                }
            }
        }
        return knap[n][weight];
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
        int knap[][] = new int[n + 1][weight + 1];
        int ans = knapsackDP(profit, weights, weight, n, knap);
        System.out.println(ans);
    }
}