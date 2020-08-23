package greedyalgorithms;

import graphs.structures.Graph;
import graphs.structures.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class WaterConnection {

    private static void dfs(Graph<Integer> graph, Integer source, boolean[] visited, ArrayList<Integer> ls, int[] min) {
        visited[source] = true;
        ls.add(source);
        TreeSet<Node<Integer>> ts = graph.map.get(source);
        if (!ts.isEmpty()) {
            Iterator<Node<Integer>> itr = ts.iterator();
            while (itr.hasNext()) {
                Node<Integer> nd = itr.next();
                if (!visited[nd.value]) {
                    visited[nd.value] = true;
                    min[0] = Integer.min(nd.weight, min[0]);
                    dfs(graph, nd.value, visited, ls, min);
                }
            }
        }
    }

    private static void findSoln(Graph<Integer> graph, boolean[] hasparent) {
        boolean visited[] = new boolean[Graph.vcount + 1];
        int c = 0;
        ArrayList<Integer> ls = new ArrayList<>();
        int[] min = {Integer.MAX_VALUE};
        for (int i = 1; i <= graph.vcount; i++) {
            if (!visited[i] && !hasparent[i]) {
                dfs(graph, i, visited, ls, min);
                if (ls.get(0) != ls.get(ls.size() - 1)) System.out.println(ls.get(0) + " " + ls.get(ls.size() - 1) + " " + min[0]);
                ls.clear();
                min[0] = Integer.MAX_VALUE;
                c++;
            }
        }
        System.out.println(c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("src/input.txt")));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int p = Integer.parseInt(str[1]);
        boolean[] hasparent = new boolean[n + 1];
        Graph<Integer> graph = new Graph<>(true);
        for (int i = 0; i < p; i++) {
            str = br.readLine().split(" ");
            graph.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
            hasparent[Integer.parseInt(str[1])] = true;
        }
        findSoln(graph, hasparent);
    }
}