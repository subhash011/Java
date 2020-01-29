package graphs.unionfind;

public class UnionFind<T extends Comparable<T>> {
    static int size;
    public T value;
    public UnionFind<T> parent;

    public UnionFind(T value) {
        this.value = value;
        this.parent = null;
    }

    public UnionFind<T> makeSet() {
        parent = new UnionFind<>(this.value);
        this.parent = this;
        UnionFind.size++;
        return this;
    }

    public UnionFind<T> findSet() {
        if (this.parent.value != this.value) {
            this.parent = this.parent.findSet();
        }
        return this.parent;
    }

    public void unionMake(UnionFind<T> obj) {
        UnionFind<T> x = this.findSet();
        UnionFind<T> y = obj.findSet();
        y.parent = x;
        UnionFind.size--;
    }

}