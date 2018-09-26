package graphs;

public class WeightedEdge extends Edge implements Comparable<Edge> {
    public int w;
    public WeightedEdge(int from, int to, int weight) {
        super(from, to);
        this.w = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (o instanceof WeightedEdge) {
            WeightedEdge weightedEdge = (WeightedEdge) o;
            if (this.w != (weightedEdge.w))
                return this.w - weightedEdge.w;
            else return super.compareTo(o);
        } else {
            return super.compareTo(o);
        }
    }
}
