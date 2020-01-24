package graphs;

import java.io.*;
import java.util.*;

public class DAGShortestPath {
    public static <T extends Comparable<T>> void dagShortestPath(Graph<T> gr, Stack<T> stack, T source) {
        HashMap<T, Integer> hmap = new HashMap<>();
        Iterator<T> itr = stack.iterator();
        while (itr.hasNext()) {
            hmap.put(itr.next(), Integer.MAX_VALUE);
        }
        hmap.replace(source, 0);
        ListIterator<T> iter = stack.listIterator(stack.size());
        while (iter.hasPrevious()) {
            T next = iter.previous();
            if (hmap.get(next) != Integer.MAX_VALUE) {
                Iterator<Node<T>> itr1 = gr.map.get(next).iterator();
                while (itr1.hasNext()) {
                    Node<T> node = itr1.next();
                    if (hmap.get(node.value) > node.weight + hmap.get(next)) {
                        hmap.replace(node.value, hmap.get(node.value), node.weight + hmap.get(next));
                    }
                }
            }
        }
        for (T t : hmap.keySet()) {
            System.out.println(t + " " + hmap.get(t));
        }
    }

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
        Stack<Integer> stack = new TopoSort().topoSortUtil(gr);
        dagShortestPath(gr, stack, 1);
    }

}