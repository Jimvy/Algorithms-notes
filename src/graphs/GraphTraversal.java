package graphs;

import util.ListQueue;
import util.Queue;

public class GraphTraversal {
    public static void dfs(AdjacencyListGraph graph, int s) {
        boolean[] marked = new boolean[graph.V()];
        dfs(graph, s, marked);
    }
    private static void dfs(AdjacencyListGraph graph, int s, boolean[] marked) {
        marked[s] = true;
        for (Edge w : graph.adj(s))
            if (!marked[w.t])
                dfs(graph, w.t, marked);
    }
    public static int[] bfs(AdjacencyListGraph graph, int s) {
        boolean[] marked = new boolean[graph.V()];
        int[] edgeTo = new int[graph.V()];
        Queue<Integer> q = new ListQueue<>();
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (Edge w : graph.adj(v)) {
                if (!marked[w.t]) {
                    marked[w.t] = true;
                    edgeTo[w.t] = s;
                    q.enqueue(w.t);
                }
            }
        }
        return edgeTo;
    }
}
