import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class practice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int arr[] = new int[n];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        int max = 0;
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = arr[i] + max;
            if (a[i] > max) {
                max = a[i];
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}