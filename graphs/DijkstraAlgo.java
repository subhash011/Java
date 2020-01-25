package graphs;

import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.stream.*;
import java.math.*;
import java.io.*;

class DijkstraShortestPath {
    public <T extends Comparable<T>> void dijkstraShortestPath(Graph<T> graph, T... args) {
        LinkedHashMap<T, Integer> hmap = new LinkedHashMap<>();
        T source = args[0];
        LinkedHashMap<T, T> parent = new LinkedHashMap<>();
        int val = 0;
        for (T key : graph.map.keySet()) {
            hmap.put(key, val);
            val++;
        }
        TreeSet<Node<T>> ts = graph.map.get(source);
        int[] distance = new int[Graph.vcount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[hmap.get(source)] = 0;
        PriorityQueue<T> queue = new PriorityQueue<>((x, y) -> {
            return distance[hmap.get(x)] - distance[hmap.get(y)];
        });
        queue.add(source);
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            TreeSet<Node<T>> t = graph.map.get(vertex);
            Iterator<Node<T>> itr = t.iterator();
            while (itr.hasNext()) {
                Node<T> node = itr.next();
                if (distance[hmap.get(node.value)] > distance[hmap.get(vertex)] + node.weight
                        && distance[hmap.get(vertex)] != Integer.MAX_VALUE) {
                    distance[hmap.get(node.value)] = distance[hmap.get(vertex)] + node.weight;
                    if (parent.containsKey(node.value)) {
                        parent.replace(node.value, vertex);
                    } else {
                        parent.put(node.value, vertex);
                    }
                    queue.add(node.value);
                }
            }
        }
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
        new DijkstraShortestPath().dijkstraShortestPath(gr, 2);
    }
}
