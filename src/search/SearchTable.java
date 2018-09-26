package search;

public interface SearchTable<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    boolean contains(K key);

    void put(K key, V value);

    /**
     * Retire la clé de la table uniquement si celle-ci est associée à la valeur spécifiée,
     * et renvoie {@code true} si la clé a bien été supprimée.
     * Si la clé n'est pas associée à la valeur, ou si la clé n'existe pas, renvoie {@code false}.
     *
     * @param key la clé à supprimer
     * @param value la valeur à laquelle cette clé doit être associée
     * @return {@code true} si la paire (clé, valeur) existait bien et a été supprimée, {@code false} sinon.
     */
    boolean delete(K key, V value);

    /**
     * Retire la clé de la table et retourne la valeur à laquelle elle était associée.
     *
     * @param key la clé que l'on souhaite retirer de la table
     * @return la valeur associée à la clé, ou {@code null} s'il n'y en avait aucune.
     */
    V remove(K key);

    Iterable<K> keys();
}
