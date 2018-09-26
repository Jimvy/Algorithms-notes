package sort;

import util.DoubleLinkedList;

import java.util.Arrays;

/*
Possibles améliorations :
- lors de la fusion, le premier tableau est dans l'ordre classique, le deuxième est dans l'ordre inverse.
  Cela permet de supprimer la condition i<mid et j<end, en une condition k<end, car on sélectionnera toujours
  la plus petite des deux extrémités, i ou j (j diminue). Exercice 2.2.10
- séparer les N éléments en M sous-tableaux, trier chacun de ces sous-tableaux, puis trier les sous-tableaux entre eux
  en se basant sur leur premier élément, et enfin effectuer les merge de blocs successifs.
- implémenter un tri indirect : renvoie un tableau d'indices de permutation, comme pour l'exercice de bilan.
- s'inspirer de la version de Java : dans cette version, ils gèrent les tris entre startIndex et endIndex.
  Lors de l'appel à la méthode privée, ils rajoutent un paramètre, l'offset, égal à -startIndex au début,
  et créent un tableau auxiliaire (passé comme argument src) de taille endIndex-startIndex, au lieu de tab.length,
  qui contient tab[low...high], et tab est passé comme étant dest.
  Dans la méthode privée, on considère que l'on doit copier depuis src[low, high] vers dest[low-off, high-off].
  Et les appels récursifs alternent le rôle de src et dest, et changent de signe la valeur de l'offset.
 */

/**
 * Algorithme :
 * De manière récursive :
 * - Diviser le tableau à trier en deux tableaux de tailles semblables.
 * - Appeler l'algorithme de tri sur chacun de ces tableaux.
 * - Fusionner des deux tableaux.
 * La fusion nécessite un tableau auxiliaire, pour éviter d'écraser les éléments du premier tableau.
 * Quelques petites optimisations apportées ici :
 * - Si la taille du tableau à trier est inférieure à une certaine taille, on passe à l'insertion sort,
 *   qui est plus efficace sur des petits tableaux que les appels récursifs.
 * - On utilise deux tableaux, Tab et Aux (déclarés dans sort), et on échange leur rôle à chaque niveau d'appels récursifs :
 *   la copie s'effectue de aux vers tab (noms des arguments de mergesort), et donc si un niveau copiait de Aux vers Tab,
 *   le suivant copiera de Tab vers Aux. Cela élimine quelques appels à arrayCopy.
 * - Si, après la récursion, les deux sous-tableaux triés sont également déjà triés l'un par rapport à l'autre,
 *   on se contente de copier, sans effectuer le merge. Cela permet de réduire la complexité à O(n) comparaisons
 *   si le tableau est déjà trié (il y a n+n/2+n/4+...=2*n=O(n) comparaisons). Il y a toujours O(n log(n)) échanges.
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
public class TDMergeSort<T extends Comparable<? super T>> implements Sort<T> {
    /**
     * Taille de tableau en dessous de laquelle on passe à insertion sort plutôt qu'à merge sort.
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
        TDMergeSort.sort(tab);
    }

    public static <T extends Comparable<? super T>> void sort(DoubleLinkedList<T> list) {
        mergesort(list);
    }
    private static <T extends Comparable<? super T>> void mergesort(DoubleLinkedList<T> list) {
        // TODO
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        Comparable[] aux = new Comparable[tab.length];
        System.arraycopy(tab, 0, aux, 0, tab.length);
        mergesort(tab, aux, 0, tab.length);
        Arrays.fill(aux, null); // loitering
    }

    /**
     * Trie le tableau aux dans le tableau tab, entre les indices start (compris) et end (non compris).
     * @param tab tableau dans lequel les éléments triés seront déplacés. A la fin de l'appel, ce tableau est trié.
     * @param aux tableau depuis lequel les éléments triés seront déplacés. A la fin de l'appel, ce tableau contiendra
     *            deux sous-tableaux triés, mais il sera inutilisable et invalide.
     * @param start indice du premier élément du tableau à trier
     * @param end indice du premier élément après le tableau à trier ; le dernier élément du tableau à trier est en {@code end-1}.
     */
    @SuppressWarnings("unchecked")
    private static void mergesort(Comparable[] tab, Comparable[] aux, int start, int end) {
        if (end-start < 2) return;
        // reduce to insertion sort for small array
        if (end - start < INSERTION_THRESHOLD) {
            InsertionSort2.sort(aux, start, end);
            System.arraycopy(aux, start, tab, start, end-start);
            return;
        }
        int mid = start + (end-start)/2;
        mergesort(aux, tab, start, mid); // optimization : we don't need to copy from one array to another if we switch the roles of tab and aux at each level.
        mergesort(aux, tab, mid, end);
        if ((mid-1 >= start && mid < end) && (aux[mid - 1].compareTo(aux[mid]) < 0)) { // the two subarrays are already sorted, nothing to do
            System.arraycopy(aux, start, tab, start, end-start); // but we still have to copy from aux (sorted) to tab (unsorted, output of this function) ;)
            return;
        }
        int i = start, j = mid, k = start;
        while (i < mid && j < end) { // merge the two halves from aux to tab.
            if (aux[i].compareTo(aux[j]) <= 0) // <= so that the algorithm is stable
                tab[k++] = aux[i++];
            else
                tab[k++] = aux[j++];
        }
        while (i < mid) tab[k++] = aux[i++]; // copy remaining elements
        while (j < end) tab[k++] = aux[j++]; // only one of the two loops will be executed anyway
    }
}
