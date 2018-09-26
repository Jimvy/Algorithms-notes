package util;

public interface MaxPriorityQueue<K extends Comparable<? super K>> {
    /**
     * Insère l'élément spécifié dans la queue de priorité.
     *
     * @param key l'élément à insérer.
     */
    void insert(K key);

    /**
     * Retourne le plus grand élément de la queue de priorité. En cas d'égalité, l'élément retourné est arbitraire.
     *
     * @return le plus grand élément de la queue de priorité.
     */
    K max();

    /**
     * Retourne et retire le plus grand élément de la queue de priorité. En cas d'égalité, l'élément retourné est arbitraire.
     *
     * @return le plus grand élément de la queue de priorité.
     */
    K deleteMax();

    /**
     * Retourne le nombre d'éléments dans la queue de priorité.
     *
     * @return la taille de la queue.
     */
    int size();

    /**
     * Retourne {@code true} si la queue est vide, {@code false} sinon.
     *
     * @return {@code true} ou {@code false} selon que la queue est vide.
     */
    boolean isEmpty();
}
