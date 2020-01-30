package graphs.structures;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    public T value;
    public int weight;

    public Node(T value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.value.compareTo(o.value);
    }
}