//https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/

package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

class FloydWarshallAlgo {
    public <T extends Comparable<T>> void floydWarshallAlgo(int[][] graph) {
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                for (int k = 1; k < graph.length; k++) {
                    if (graph[j][k] > graph[j][i] + graph[i][k] && graph[j][i] != Integer.MAX_VALUE
                            && graph[i][k] != Integer.MAX_VALUE) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }
    }
}

class FloydWarshall {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        LinkedHashMap<Integer, Integer> hmap = new LinkedHashMap<>();
        String s;
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n + 1][n + 1];
        for (int[] is : graph) {
            Arrays.fill(is, Integer.MAX_VALUE);
        }
        // if this initialisation is not considered then we can find all cycle lengths
        // in the graph i.e graph[i][i] gives the shortest cycle to that vertex
        for (int i = 0; i < graph.length; i++) {
            graph[i][i] = 0;
        }
        while (true) {
            s = br.readLine();
            if (s == null) {
                break;
            }
            String str[] = s.split(" ");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);
            graph[row][col] = Integer.parseInt(str[2]);
        }
        br.close();
        new FloydWarshallAlgo().floydWarshallAlgo(graph);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}