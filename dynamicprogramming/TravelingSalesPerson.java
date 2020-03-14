//https://www.geeksforgeeks.org/travelling-salesman-problem-set-1/
//for code with bit masking
//https://codingblocks.com/resources/travelling-salesman/

package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//recursive approach of order n!
//base case : C({1,j},j) = dist(1,j) , C({1,1},1) = Integer.MAX_VALUE
//C(S,i) = min over all j {C(S/{i},j) +  dist(i,j)}

public class TravelingSalesPerson {
    static int matrix[][];
    static int n;
    static int ALL_DONE;

    public static int travelingSalesPerson(int mask, int pos) {
        if (mask == ALL_DONE)
            return matrix[pos][0];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                ans = Integer.min(travelingSalesPerson((mask | (1 << i)), i) + matrix[pos][i], ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        n = Integer.parseInt(br.readLine());
        ALL_DONE = (1 << n) - 1;
        matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            String str[] = br.readLine().split(" ");
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Integer.parseInt(str[j]);
            }
        }
        int ans = travelingSalesPerson(1, 0);
        System.out.println(ans);
    }
}