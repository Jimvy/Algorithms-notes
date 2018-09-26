package util;

import java.util.Arrays;

/**
 * Union-Find with union by sizes (union) and path compression (find). This is the one presented in the book.
 */
public class USPCUnionFind implements UnionFind {
    private int[] parents;
    private int[] sizes;
    private int count;

    /**
     * Crée un Union Find, avec n ensembles disjoints initialement.
     * L'opération union est effectuée par comparaison des rangs, après find.
     * L'opération find est effectuée avec path compression.
     *
     * @param n le nombre d'ensembles
     */
    public USPCUnionFind(int n) {
        this.count = n;
        this.parents = new int[n];
        this.sizes = new int[n];
        Arrays.setAll(parents, i->i);
        Arrays.fill(sizes, 1);
    }

    /**
     * Retourne l'indice de l'ensemble parent de l'ensemble {@code i}.
     * Cette valeur n'est plus valide après qu'une opération {@code union} a été effectuée.
     *
     * @param i l'indice du tableau dont on veut déterminer le parent.
     * @return L'indice de l'ensemble parent de cet ensemble.
     */
    private int find(int i) {
        if (parents[i] == i)
            return i;
        else
            return parents[i] = find(parents[i]);
    }

    @Override
    public boolean sameSet(int i, int j) {
        return find(i) == find(j);
    }

    @Override
    public void union(int i, int j) {
        int x = find(i), y = find(j);
        if (x == y)
            return; // don't forget me
        if (sizes[x] > sizes[y])
            parents[y] = x;
        else if (sizes[x] < sizes[y])
            parents[x] = y;
        else {
            sizes[x] += sizes[y];
            parents[y] = x;
        }
        count--; // don't forget me
    }

    @Override
    public int count() {
        return count;
    }
}
