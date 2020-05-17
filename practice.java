import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Test {
    public String name;
    public Integer age;

    public Test(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

public class practice {
    public static void main(String[] args) throws IOException {
        Integer arr[] = { 1, 2, 3, 4 };
        Test[] test = new Test[3];
        test[0] = new Test("subhash", 1);
        test[1] = new Test("Sarvesh", 2);
        test[2] = new Test("Suresh", 3);
        Arrays.stream(test).forEach(x -> {
            System.out.println(x.name);
        });
    }
}
