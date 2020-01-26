package graphs;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T value;
    int weight;

    public Node(T value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.value.compareTo(o.value);
    }
}