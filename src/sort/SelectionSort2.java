package sort;

/**
 * Algorithme :
 * On compte à partir de 0 les positions
 * Pour i=0...n-1 :
 * - L'algorithme maintient l'invariant que les éléments 0...i-1 sont déjà à leur bonne place.
 * - Trouver le (i+1)e plus petit élément du tableau :
 *   il s'agit nécessairement du plus petit élément parmi les éléments d'indice [i, n-1]. Il se trouve en j.
 * - Echanger cet élément avec l'élément en i. Les (i+1) premiers éléments du tableau sont ainsi triés, à leur position finale.
 * La différence avec le selection sort classique est la mise en cache de la valeur minimale du tableau.
 * Propriétés :
 * - Complexité en Theta(n^2) dans tous les cas : n(n-1)/2 accès au tableau (pour comparaison avec le maximum actuel
 *   si on ne met pas en cache ce dernier ; de même, les 2*n échanges en dehors du 2e for ne sont pas considérés),
 *   et exactement n-1 échanges (si on n'utilise pas le if) ou 0 <= n-1 échanges (si on utilise le if) ;
 *   les pires cas pour les échanges sont [2 3 4 1] et [4 1 2 3], par exemple.
 * - Le nombre d'échanges est au plus linéaire dans le pire cas ; c'est le seul algo du cours qui a cette propriété.
 * - Tous les tableaux résultent dans la même complexité : selection sort est incapable de tirer profit des spécificités d'un tableau.
 * - L'algorithme est en-place.
 * - L'algorithme est non stable : par exemple, [2_1 3 2_2 1] devient [1 3 2_2 2_1].
 *   Il est possible de rendre l'algorithme stable de deux manières :
 *   - Pour des tableaux, plutôt que d'échanger deux éléments, on décale l'ensemble des éléments situés entre les deux,
 *     insérant ainsi l'élément minimum au début du tableau. Cela reste en O(n^2), mais le facteur devient plus grand,
 *     et cela transforme le selection sort en une version transformée de insertion sort : il en hérite les bonnes propriétés.
 *   - Si on trie une liste chainée, on peut facilement insérer l'élément minimum plutôt que l'échanger avec un autre,
 *     déplaçant ainsi de fait tous les éléments de la liste de manière efficace.
 * @param <T> le type de données que l'on doit trier.
 */
public class SelectionSort2<T extends Comparable<? super T>> implements Sort<T> {
    // default constructor

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        SelectionSort2.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        for (int i = 0; i < tab.length; i++) {
            int k = i, j = i + 1;
            for (T curMax = tab[k]; j < tab.length; j++) // find (i+1) smallest
                if (tab[j].compareTo(curMax) < 0) {
                    k = j;
                    curMax = tab[k];
                }
            if (k != i) { // otherwise it shouldn't be needed to exchange the two equal elements
                T temp = tab[i];
                tab[i] = tab[k];
                tab[k] = temp;
            }
        }
    }
}
