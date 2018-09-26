package util;

public interface UnionFind {
    /**
     * Unit les deux ensembles contenant les ensembles i et j.
     *
     * @param i le premier ensemble
     * @param j le deuxième ensemble
     */
    void union(int i, int j);

    /**
     * Renvoie {@code true} si les deux ensembles i et j sont dans le même ensemble, {@code false} sinon.
     * @param i le premier ensemble
     * @param j le deuxième ensemble
     * @return {@code true} si les deux ensembles sont uni, {@code false} sinon.
     */
    boolean sameSet(int i, int j);

    /**
     * Renvoie le nombre d'ensembles disjoints de cette structure.
     *
     * @return le nombre d'ensembles disjoints
     */
    int count();
}
