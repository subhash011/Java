package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ActivityWithWeights {
    private static boolean isOverlap(Integer[] a, Integer[] b) {
        if ((b[0] >= a[0] && b[0] < a[1]) || (a[0] >= b[0] && a[0] < b[1]) || (a[0] == b[0] && a[1] == b[1])) {
            return true;
        }
        return false;
    }

    private static int findOptSoln(ArrayList<Integer[]> vals, int len) {
        if (len == 0) {
            return 0;
        }
        ArrayList<Integer[]> nonoverlap = new ArrayList<>();
        ArrayList<Integer[]> arg1 = new ArrayList<>();
        for (int i = 0; i < len - 1; i++) {
            if (!isOverlap(vals.get(i), vals.get(len - 1))) {
                nonoverlap.add(vals.get(i));
            }
        }
        arg1 = (ArrayList<Integer[]>) vals.clone();
        arg1.remove(len - 1);
        int a = findOptSoln(arg1, len - 1);
        int b = vals.get(len - 1)[2] + findOptSoln(nonoverlap, nonoverlap.size());
        return Integer.max(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String str1[] = br.readLine().split(" ");
        String str2[] = br.readLine().split(" ");
        String str3[] = br.readLine().split(" ");
        HashMap<Integer, Integer> start = new HashMap<>();
        HashMap<Integer, Integer> end = new HashMap<>();
        HashMap<Integer, Integer> weights = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<>();
        ArrayList<Integer[]> vals = new ArrayList<>();
        for (int i = 0; i < str1.length; i++) {
            Integer[] interval = new Integer[3];
            interval[0] = Integer.parseInt(str1[i]);
            interval[1] = Integer.parseInt(str2[i]);
            interval[2] = Integer.parseInt(str3[i]);
            vals.add(interval);

        }
        br.close();
        int ans = findOptSoln(vals, vals.size());
        System.out.println(ans);
    }
}