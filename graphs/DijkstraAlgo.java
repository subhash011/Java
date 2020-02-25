//https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/

package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;
import java.util.Arrays;
import graphs.structures.Graph;
import graphs.structures.Node;

class DijkstraShortestPath {
    public <T extends Comparable<T>> void dijkstraShortestPath(Graph<T> graph, T... args) {
        LinkedHashMap<T, Integer> hmap = new LinkedHashMap<>();
        Queue<T> qu = new LinkedList<>();
        T source = args[0];
        LinkedHashMap<T, T> parent = new LinkedHashMap<>();
        int val = 0;
        for (T key : graph.map.keySet()) {
            hmap.put(key, val);
            val++;
        }
        // find shortest cycle in a graph
        int ans = Integer.MAX_VALUE;
        TreeSet<T> ts = new TreeSet<>();
        int[] distance = new int[Graph.vcount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[hmap.get(source)] = 0;
        PriorityQueue<T> queue = new PriorityQueue<>((x, y) -> {
            return distance[hmap.get(x)] - distance[hmap.get(y)];
        });
        queue.add(source);
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            ts.add(vertex);
            qu.add(vertex);
            TreeSet<Node<T>> t = graph.map.get(vertex);
            Iterator<Node<T>> itr = t.iterator();
            while (itr.hasNext()) {
                Node<T> node = itr.next();
                if (distance[hmap.get(node.value)] > distance[hmap.get(vertex)] + node.weight
                        && distance[hmap.get(vertex)] != Integer.MAX_VALUE) {
                    if (!ts.contains(node.value)) {
                        distance[hmap.get(node.value)] = distance[hmap.get(vertex)] + node.weight;
                        if (parent.containsKey(node.value)) {
                            parent.replace(node.value, vertex);
                        } else {
                            parent.put(node.value, vertex);
                        }
                        queue.add(node.value);
                    }
                }
                if (qu.contains(node.value) && node.value.compareTo(source) == 0) {
                    ans = Integer.min(distance[hmap.get(vertex)] + node.weight, ans);
                }
            }
        }
        // System.out.println(ans);
        T dest = args.length == 2 ? args[1] : (T) Integer.valueOf(-1);
        if (dest.compareTo((T) Integer.valueOf(-1)) != 0) {
            for (T i : parent.keySet()) {
                if (i.compareTo(dest) == 0) {
                    System.out.println(distance[hmap.get(i)]);
                }
            }
        } else {
            for (T i : parent.keySet()) {
                System.out.println(i + " " + parent.get(i) + " " + distance[hmap.get(i)] + " ");
            }
        }

    }
}

class DijkstraAlgo {
    public static void main(String[] args) throws IOException {
        Graph<Integer> gr = new Graph<>(true);
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        while (true) {
            String str = br.readLine();
            if (str == null) {
                break;
            }
            String[] s = str.split(" ");
            gr.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        }
        br.close();
        for (int i = 1; i < 10; i++) {
            new DijkstraShortestPath().dijkstraShortestPath(gr, i);
        }
        // new DijkstraShortestPath().dijkstraShortestPath(gr, 2);
    }
}
// 1 2 8
// 1 3 3
// 1 6 13
// 2 3 2
// 2 4 1
// 3 4 9
// 3 5 2
// 4 5 4
// 4 8 2
// 4 7 6
// 5 1 5
// 5 6 5
// 5 9 4
// 5 4 6
// 6 9 7
// 6 7 1
// 7 8 4
// 7 5 3
// 8 9 1
// 9 7 5