package graphs.structures;

import java.util.HashMap;
import java.util.TreeSet;

public class Graph<T extends Comparable<T>> {
    public static boolean isDirected;
    public static int vcount = 0;
    public HashMap<T, TreeSet<Node<T>>> map;

    public Graph(boolean isDirected) {
        Graph.isDirected = isDirected;
        map = new HashMap<>();
    }

    public void addVertex(T key) {
        map.put(key, new TreeSet<>());
        vcount++;
    }

    public void addEdge(T v1, T v2, int weight) {
        if (!map.containsKey(v1)) {
            this.addVertex(v1);
        }
        if (!map.containsKey(v2)) {
            this.addVertex(v2);
        }
        Node<T> nd1 = new Node<>(v2, weight);
        this.map.get(v1).add(nd1);
        if (!isDirected) {
            Node<T> nd2 = new Node<>(v1, weight);
            this.map.get(v2).add(nd2);
        }
    }
}