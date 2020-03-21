package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MatrixMul {
    private static int findOptSoln(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int val = findOptSoln(arr, start, i) + findOptSoln(arr, i + 1, end) + arr[start - 1] * arr[i] * arr[end];
            min = Integer.min(min, val);
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String str[] = br.readLine().split(", ");
        ArrayList<Integer> ls = new ArrayList<>();
        int arr[] = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            int val = Integer.parseInt(str[i]);
            arr[i] = val;
        }
        int ans = findOptSoln(arr, 1, arr.length - 1);
        System.out.println(ans);
    }
}