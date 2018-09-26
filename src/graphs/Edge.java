package graphs;

public class Edge implements Comparable<Edge> {
    public int s;
    public int t;
    public Edge(int s, int t) {
        this.s = s;
        this.t = t;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.s != o.s)
            return this.s - o.s;
        else
            return this.t - o.t;
    }
}
