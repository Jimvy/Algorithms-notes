package graphs;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph {
    private int v;
    private int e;
    private List<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public AdjacencyListGraph(int V) {
        this.v = V;
        this.e = 0;
        this.adj = new List[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    public AdjacencyListGraph(int V, List<Edge> edges) {
        this(V);
        for (Edge edge : edges)
            addEdge(edge.s, edge.t);
    }
    public int V() { return v;}
    public int E() { return e;}
    public void addEdge(int v, int w) {
        adj[v].add(new Edge(v, w));
        adj[w].add(new Edge(w, v));
        e++;
    }
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}
