package DataStructures;

/**
 * Created by Uzumaki Naruto on 2/13/2017.
 */
public class GraphNode<T> {
    private T nodeName;
    private int weight;

    public GraphNode(T nodeName, int weight) {
        this.nodeName = nodeName;
        this.weight = weight;
    }

    public T getNodeName() {
        return nodeName;
    }

    public void setNodeName(T nodeName) {
        this.nodeName = nodeName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.nodeName + "/" + this.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNode<?> graphNode = (GraphNode<?>) o;

        return nodeName.equals(graphNode.nodeName);
    }

    @Override
    public int hashCode() {
        return nodeName.hashCode();
    }
}
