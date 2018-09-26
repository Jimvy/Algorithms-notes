package sort;

import java.util.Arrays;

/**
 * Algorithme :
 * Tant que le tableau n'est pas trié :
 * - Tant qu'on a pas parcouru au moins une fois tout le tableau :
 *   - On cherche deux sous-tableaux qui ont la particularité que leurs éléments sont triés entre eux.
 *   - On fusionne ces deux tableaux.
 * Il s'agit d'une variante du BUMergeSort qui tire profit du fait que certains sous-tableaux seront probablement triés.
 * Propriétés :
 * - Complexité : O(n log(n)) en moyenne, O(n) dans le meilleur cas (déjà trié), et bien plus rapide que mergesort
 *   classique si le tableau de départ est presque trié.
 * - Il n'est pas en-place, mais est stable.
 * @param <T> le type de données que l'on doit trier.
 */
public class NaturalMergeSort<T extends Comparable<? super T>> implements Sort<T> {
    // default constructor


    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        NaturalMergeSort.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[tab.length];
        boolean notYetSorted = true;
        // Tant que le tableau n'est pas entièrement trié
        while (notYetSorted) {
            int start1 = 0, start2, end2;
            // Tant qu'on n'a pas parcouru au moins une fois le tableau
            while (start1 < tab.length) {
                // Recherche de deux sous-tableaux triés.
                // Premier sous-tableau
                start2 = start1 + 1;
                while (start2 < tab.length && tab[start2 - 1].compareTo(tab[start2]) <= 0) {
                    start2++;
                }
                if (start2 - start1 >= tab.length) {
                    notYetSorted = false;
                    //break;
                }
                // Deuxième sous-tableau
                if (start2 < tab.length) {
                    end2 = start2 + 1;
                    while (end2 < tab.length && tab[end2 - 1].compareTo(tab[end2]) <= 0) {
                        end2++;
                    }
                } else {
                    end2 = start2; // il n'y a pas de deuxième sous-tableau.
                }
                // Fusion des deux tableaux
                int i = start1, j = start2, k = start1;
                while (i < start2 && j < end2) {
                    if (tab[i].compareTo(tab[j]) <= 0)
                        aux[k++] = tab[i++];
                    else
                        aux[k++] = tab[j++];
                }
                while (i < start2) aux[k++] = tab[i++];
                while (j < end2) aux[k++] = tab[j++];
                //System.arraycopy(aux, start1, tab, start1, end2-start1);
                start1 = end2;
            }
            // We can do the copy here, as successive merges of sub arrays don't require the other sub arrays.
            System.arraycopy(aux, 0, tab, 0, tab.length);
        }
        Arrays.fill(aux, null); // loitering
    }
}
