package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

class TopoSort {

    public <T extends Comparable<T>> Stack<T> topoSort(Graph<T> graph) {
        HashMap<T, Boolean> visited = new HashMap<>();
        Stack<T> stack = new Stack<>();
        for (T gr : graph.map.keySet()) {
            visited.put(gr, false);
        }
        for (T gr : graph.map.keySet()) {
            if (!visited.get(gr)) {
                topoSort(graph, gr, visited, stack);
            }
        }
        return stack;
    }

    public <T extends Comparable<T>> void topoSort(Graph<T> graph, T gr, HashMap<T, Boolean> visited, Stack<T> stack) {
        visited.replace(gr, false, true);
        TreeSet<Node<T>> ts = graph.map.get(gr);
        Iterator<Node<T>> itr = ts.iterator();
        while (itr.hasNext()) {
            Node<T> next = itr.next();
            if (!visited.get(next.value)) {
                visited.replace(next.value, false, true);
                topoSort(graph, next.value, visited, stack);
            }
        }
        stack.push(gr);
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
        Stack<Integer> stack = new TopoSort().topoSort(gr);
        stack.stream().forEach(x -> {
            System.out.print(x + " ");
        });
    }
}