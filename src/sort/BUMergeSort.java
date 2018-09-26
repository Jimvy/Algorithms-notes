package sort;

import java.util.Arrays;

/*
TODO
- natural merge sort : l'idée est de considérer comme déjà fusionnés des blocs qui sont déjà triés. Par exemple,
  [3 4 2 1 7 5 8 9 0 6] serait découpé implicitement en [[3 4] [2] [1 7] [5 8 9] [0 6]], et chaque groupe serait alors fusionner dans une bottom-up fashion.
  => [2 3 4 1 5 7 8 9 0 6] => [[2 3 4] [1 5 7 8 9] [0 6]] => [[1 2 3 4 5 7 8 9] [0 6]] => [0 1 2 3 4 5 6 7 8 9]. C'est l'exercice 2.2.16
 */

/**
 * Algorithme :
 * Pour chaque taille de tableau s, s commençant à 1 et doublant à chaque itération :
 * - Fusionner chaque paire de sous-tableaux de taille s, en utilisant la procédure de fusion habituelle.
 * Petites particularités :
 * - Il faut utiliser un tableau auxiliaire, comme d'habitude.
 * - Il est possible de passer à l'insertion sort pour de petites tailles de tableau. Pour implémenter ça, l'idéal est
 *   de commencer avec une valeur de s égale au seuil, et de préalablement effectuer les tris avant l'itération sur s.
 * - Le nombre d'itérations est bien de ceiling(log_2(n)).
 * - Si, après les itérations précédentes, les deux sous-tableaux triés sont également déjà triés l'un par rapport à l'autre,
 *   on saute la fusion. Cela permet de réduire la complexité à O(n) si le tableau est déjà trié.
 *   (il y a n+n/2+n/4+...=2*n=O(n) comparaisons). Et contrairement au TDMergeSort, il n'y a pas de copies.
 * Propriétés :
 * - La complexité est en Theta(n log(n)) dans tous les cas : à chaque niveau, l'ensemble du tableau est passé en revu
 *   lors de la fusion, et il y a log_2(n) niveaux. Il prend également O(n + log(n)) en espace.
 * - Le gros problème de cet algorithme se situe au niveau des copies entre les tableaux, qui sont inévitables,
 *   même quand le tableau est déjà trié. Il y a ainsi environ O(n log(n)) échanges d'éléments, ce qui peut être bien ou pas.
 * - L'algorithme est stable.
 * - L'algorithme n'est <b>pas</b> en place. Il demande un tableau auxiliaire de la taille du tableau d'origine.
 *   Il est possible de rendre merge sort en place, mais cela requiert de grosses modifications et de grosses astuces.
 *   Il est aussi possible de réduire l'espace requis dans le tableau auxiliaire à la taille du plus
 * @param <T> le type de données que l'on doit trier.
 */
public class BUMergeSort<T extends Comparable<? super T>> implements Sort<T> {
    /**
     * Taille de tableau en dessous de laquelle on passe à l'insertion sort plutôt qu'à merge sort.
     * S'il est inférieur ou égal à 0, il est ignoré.
     */
    private static final int INSERTION_THRESHOLD = 10;

    // default constructor

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        BUMergeSort.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        //Comparable[] aux = new Comparable[tab.length];
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[tab.length]; // succeeds to cast ; Java, certainly.
        //T[] aux = (T[]) new Object[tab.length]; // fails to cast
        if (INSERTION_THRESHOLD > 0) {
            for (int t = 0; t < tab.length; t += INSERTION_THRESHOLD)
                InsertionSort2.sort(tab, t,
                        ((t + INSERTION_THRESHOLD < tab.length) ? (t + INSERTION_THRESHOLD) : (tab.length)));
        }
        for (int s = Math.max(1, INSERTION_THRESHOLD); s < tab.length; s *= 2) {
            // for each subarray size s : log(n) times
            // assume sub arrays of size s or less are already sorted
            for (int t = 0; t < tab.length - s; t += 2*s) {
                // for each pair of subarray : O(n)
                int i = t, j = t + s, k = t, mid = t+s, end = (t+2*s < tab.length ? t+2*s : tab.length);
                // all indices are valid indices, within the sub arrays
                if (tab[mid-1].compareTo(tab[mid]) <= 0)
                    continue; // the two sub arrays are already sorted
                while (i < mid && j < end) {
                    if (tab[i].compareTo(tab[j]) <= 0)
                        aux[k++] = tab[i++];
                    else
                        aux[k++] = tab[j++];
                }
                while (i < mid) aux[k++] = tab[i++];
                while (j < end) aux[k++] = tab[j++];
                System.arraycopy(aux, t, tab, t, end-t);
            }
        }
        Arrays.fill(aux, null); // loitering
    }
}
