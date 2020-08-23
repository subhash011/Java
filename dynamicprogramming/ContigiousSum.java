package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ContigiousSum {
    private static int csum(int arr[], ArrayList<Integer> ls, int sum, int ind) {
        int max_till = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int st = 0;
        int max_now = 0;
        for (int i = 0; i < arr.length; i++) {
            max_now += arr[i];
            if (max_till < max_now) {
                max_till = max_now;
                start = st;
                end = i;
            } else if (max_now < 0) {
                max_now = 0;
                st = i + 1;
            }
        }
        return max_till;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String[] s1 = br.readLine().split(", ");
        int[] arr = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            arr[i] = Integer.parseInt(s1[i]);
        }
        ArrayList<Integer> ls = new ArrayList<>();
        int ans = csum(arr, ls, 0, 0);
        System.out.println(ans);
    }
}