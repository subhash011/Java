import java.util.*;

/**
 * practice
 */
public class practice {
    public static void main(String[] args) {
        Random rnd = new Random();
        rnd.setSeed(100);
        for (int i = 0; i < 10; i++) {
            System.out.print(rnd.nextInt(4) + " ");
        }
    }
}