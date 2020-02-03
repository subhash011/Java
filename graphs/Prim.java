////https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/

package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import graphs.structures.Graph;
import graphs.structures.Node;

class PrimAlgo {
    public <T extends Comparable<T>> void primMSTAlgo(Graph<T> graph, T source) {
        TreeSet<T> mst = new TreeSet<>();
        int val = 1;
        HashMap<T, Integer> hmap = new HashMap<>();
        HashMap<T, T> parent = new HashMap<>();
        for (T t : graph.map.keySet()) {
            hmap.put(t, val);
            val++;
        }
        int[] distance = new int[Graph.vcount + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[hmap.get(source)] = 0;
        PriorityQueue<T> queue = new PriorityQueue<>((a, b) -> {
            return distance[hmap.get(a)] - distance[hmap.get(b)];
        });
        queue.add(source);
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            mst.add(vertex);
            Iterator<Node<T>> itr = graph.map.get(vertex).iterator();
            while (itr.hasNext()) {
                Node<T> node = itr.next();
                if (distance[hmap.get(node.value)] > node.weight && !mst.contains(node.value)) {
                    distance[hmap.get(node.value)] = node.weight;
                    if (parent.containsKey(node.value)) {
                        parent.replace(node.value, vertex);
                    } else {
                        parent.put(node.value, vertex);
                    }
                    queue.add(node.value);
                }
            }
        }
        for (T t : parent.keySet()) {
            System.out.println(t + " " + parent.get(t));
        }
        int dist = 0;
        for (int i = 1; i < distance.length; i++) {
            dist += distance[i];
        }
        System.out.println(dist);
    }
}

class Prim {
    public static void main(String[] args) throws IOException {
        Graph<Integer> graph = new Graph<>(false);
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        while (true) {
            String str = br.readLine();
            if (str == null) {
                break;
            }
            String[] s = str.split(" ");
            graph.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        }
        br.close();
        new PrimAlgo().primMSTAlgo(graph, 1);
    }
}