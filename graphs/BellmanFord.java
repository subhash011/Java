package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import graphs.structures.Graph;
import graphs.structures.Node;

class BellmanFordAlgo {

    public <T extends Comparable<T>> boolean bellmanFordAlgo(Graph<T> graph, T source) {
        int v = Graph.vcount;
        int val = 0;
        LinkedHashMap<T, Integer> hmap = new LinkedHashMap<>();
        LinkedHashMap<T, T> parent = new LinkedHashMap<>();
        for (T t : graph.map.keySet()) {
            hmap.put(t, val);
            val++;
        }
        int[] distances = new int[v];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[hmap.get(source)] = 0;
        for (int i = 0; i < v - 1; i++) {
            for (T t : graph.map.keySet()) {
                TreeSet<Node<T>> ts = graph.map.get(t);
                Iterator<Node<T>> itr = ts.iterator();
                while (itr.hasNext()) {
                    Node<T> node = itr.next();
                    if (distances[hmap.get(node.value)] > distances[hmap.get(t)] + node.weight
                            && distances[hmap.get(t)] != Integer.MAX_VALUE) {
                        distances[hmap.get(node.value)] = distances[hmap.get(t)] + node.weight;
                        if (parent.containsKey(node.value)) {
                            parent.replace(node.value, t);
                        } else {
                            parent.put(node.value, t);
                        }
                    }
                }
            }
        }
        for (T t : graph.map.keySet()) {
            TreeSet<Node<T>> ts = graph.map.get(t);
            Iterator<Node<T>> itr = ts.iterator();
            while (itr.hasNext()) {
                Node<T> node = itr.next();
                if (distances[hmap.get(node.value)] > distances[hmap.get(t)] + node.weight
                        && distances[hmap.get(t)] != Integer.MAX_VALUE) {
                    return false;
                }
            }
        }
        for (T i : parent.keySet()) {
            System.out.println(i + " " + parent.get(i) + " " + distances[hmap.get(i)] + " ");
        }
        return true;
    }
}

class BellmanFord {
    public static void main(String[] args) throws IOException {
        Graph<Integer> graph = new Graph<>(true);
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
        boolean ret = new BellmanFordAlgo().bellmanFordAlgo(graph, 1);
    }
}