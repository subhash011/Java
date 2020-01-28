package graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import graphs.unionfind.UnionFind;

class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {
    T weight;
    T src;
    T dest;

    public Edge(T weight, T src, T dest) {
        this.weight = weight;
        this.dest = dest;
        this.src = src;
    }

    public int compareTo(Edge<T> a) {
        return this.weight.compareTo(a.weight);
    }

    @Override
    public boolean equals(Object obj) {
        Edge<T> obj1 = (Edge<T>) obj;
        return this.src.compareTo(obj1.src) == 0 && this.dest.compareTo(obj1.dest) == 0;
    }

}

class KruskalAlgo {

    public <T extends Comparable<T>> void kruskalAlgo(Graph<T> graph, PriorityQueue<Edge<T>> queue,
            List<UnionFind<T>> ls) {
        List<Edge<T>> list = new ArrayList<>();
        for (int i = 0; i < Graph.vcount - 1; i++) {
            Edge<T> edge = queue.poll();
            UnionFind<T> x = ls.get((Integer) edge.src - 1).findSet();
            UnionFind<T> y = ls.get((Integer) edge.dest - 1).findSet();
            if (x.value != y.value) {
                list.add(edge);
            }
        }
        list.stream().forEach(x -> {
            System.out.println("src : " + x.src + " " + "dest : " + x.dest + " " + "weight : " + x.weight);
        });
    }

    public <T extends Comparable<T>> void kruskalAlgo(Graph<T> graph, List<UnionFind<T>> ls) {
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>();
        for (T t : graph.map.keySet()) {
            Iterator<Node<T>> itr = graph.map.get(t).iterator();
            while (itr.hasNext()) {
                Node<T> node = itr.next();
                Edge<T> edge;
                if (node.value.compareTo(t) > 0) {
                    edge = new Edge(node.weight, t, node.value);
                } else {
                    edge = new Edge(node.weight, node.value, t);
                }
                if (!queue.contains(edge)) {
                    queue.add(edge);
                }
            }
        }
        kruskalAlgo(graph, queue, ls);
    }

}

class Kruskal {
    public static void main(String[] args) throws IOException {
        Graph<Integer> graph = new Graph<>(false);
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        List<UnionFind<Integer>> ls = new ArrayList<>();
        while (true) {
            String str = br.readLine();
            if (str == null) {
                break;
            }
            String[] s = str.split(" ");
            graph.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        }
        br.close();
        for (Integer i : graph.map.keySet()) {
            UnionFind<Integer> toAdd = new UnionFind<>(i).makeSet();
            ls.add(toAdd);
        }
        new KruskalAlgo().kruskalAlgo(graph, ls);
    }
}