package dynamicprogramming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//find minimum steps needed to convert s1 into s2    
//min({diff(s1,s2) + F(m-1,n-1)},{1 + F(m-1,n)},{1 + F(m,n-1)})
public class EditDistances {

    public static int findDiff(String s1, String s2, int m, int n) {
        int ans = 0;
        int i;
        if (s1.charAt(m) != s2.charAt(n)) {
            return 1;
        }
        return ans;
    }

    public static int editDistances(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return m * s1.length() + n * s2.length();
        }
        int diff = findDiff(s1, s2, m, n);
        int a = diff + editDistances(s1, s2, m - 1, n - 1);
        int b = 1 + editDistances(s1, s2, m - 1, n);
        int c = 1 + editDistances(s1, s2, m, n - 1);
        return Integer.min(a, Integer.min(b, c));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int ans = editDistances(s1, s2, s1.length() - 1, s2.length() - 1);
        System.out.println(ans);
    }
}