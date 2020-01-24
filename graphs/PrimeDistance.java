package graphs;

import java.io.*;
import java.util.*;

class GetPrimes {
    public List<Integer> getPrimes() {
        boolean arr[] = new boolean[10000];
        Arrays.fill(arr, 2, arr.length, true);
        for (int i = 2; i * i < 10000; i++) {
            if (arr[i]) {
                for (int j = i * i; j < 10000; j += i) {
                    arr[j] = false;
                }
            }
        }
        List<Integer> ls = new ArrayList<>();
        for (int i = 1000; i < arr.length; i++) {
            if (arr[i]) {
                ls.add(i);
            }
        }
        return ls;
    }
}

class CreateGraph {

    public <T extends Comparable<T>> void createGraph(Graph<T> graph, Integer src, Integer dest) {
        List<Integer> list = new GetPrimes().getPrimes();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (compare(list.get(i), list.get(j))) {
                    graph.addEdge((T) list.get(i), (T) list.get(j), 1);
                }
            }
        }
    }

    private boolean compare(Integer src, Integer dest) {
        String s1 = Integer.toString(src);
        String s2 = Integer.toString(dest);
        int count = 0;
        if (!(s1.charAt(0) == s2.charAt(0))) {
            count++;
        }
        if (!(s1.charAt(1) == s2.charAt(1))) {
            count++;
        }
        if (!(s1.charAt(2) == s2.charAt(2))) {
            count++;
        }
        if (!(s1.charAt(3) == s2.charAt(3))) {
            count++;
        }
        if (count == 1) {
            return true;
        }
        return false;
    }

}

class PrimeDistance {
    public static void main(String[] args) throws IOException {
        Graph<Integer> graph = new Graph<>(false);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        Integer src = Integer.parseInt(str[0]);
        Integer dest = Integer.parseInt(str[1]);
        if (src.compareTo(dest) == 0) {
            System.out.println(0);
        } else {
            new CreateGraph().createGraph(graph, src, dest);
            new DijkstraShortestPath().dijkstraShortestPath(graph, src, dest);
        }

    }
}