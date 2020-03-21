//wrong for some inputs

package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MatrixChainMultiplication {
    private static int findOptSoln(ArrayList<Integer> ls, int len) {
        if (len == 3) {
            return ls.get(0) * ls.get(1) * ls.get(2);
        }
        if (len == 2) {
            return 0;
        }
        ArrayList<Integer> ls1 = new ArrayList<>();
        ArrayList<Integer> ls2 = new ArrayList<>();
        ls1 = (ArrayList<Integer>) ls.clone();
        ls2 = (ArrayList<Integer>) ls.clone();
        int type1 = ls.get(0) * ls.get(2) * (ls.get(1) + ls.get(3));
        int type2 = ls.get(3) * ls.get(1) * (ls.get(0) + ls.get(2));
        ls1.remove(1);
        ls1.remove(1);
        ls2.remove(1);
        ls2.remove(1);
        int a = type1 + findOptSoln(ls1, len - 2);
        int b = type2 + findOptSoln(ls2, len - 2);
        return Integer.min(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String str[] = br.readLine().split(", ");
        ArrayList<Integer> ls = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            int val = Integer.parseInt(str[i]);
            ls.add(val);
        }
        int ans = findOptSoln(ls, ls.size());
        System.out.println(ans);
    }
}