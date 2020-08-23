import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {
    private static BufferedReader br;

    private static void mapToArray(int[][] arr, int q) throws IOException {
        String[] str;
        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
    }

    private static HashMap<String, Integer> mapToArray(int[] arr) throws IOException {
        String[] str;
        HashMap<String, Integer> map = new HashMap<>();
        str = br.readLine().split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            max = Integer.max(max, arr[i]);
            min = Integer.min(min, arr[i]);
        }
        map.put("max", max);
        map.put("min", min);
        return map;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String str[];
        int n, m, k, q, t;
        int[] arr;

        br.close();
    }
}
