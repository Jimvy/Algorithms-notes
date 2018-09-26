package sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class UnsortedPairs {
    public static <T extends Comparable<? super T>> int unsortedPairs(T[] tab) {
        Comparable[] newtab = new Comparable[tab.length];
        System.arraycopy(tab, 0, newtab, 0, tab.length);
        Comparable[] aux = new Comparable[tab.length];
        int r =  mergesort(newtab, aux, 0, tab.length);
        Arrays.fill(newtab, null); // loitering
        Arrays.fill(aux, null); // loitering
        return r;
    }

    /**
     * Effectue mergesort sur le tableau passé en argument.
     * @param tab le tableau dans lequel devra se situer le tableau trié
     * @param aux un tableau auxiliaire
     * @param start l'indice du début à partir duquel trier
     * @param end l'indice après l'indice de fin : end-1 est le dernier indice où trier
     * @param <T> le type de la variable : inutilisé
     * @return le nombre partiel de paires de nombres qui ne sont pas dans le bon ordre dans l'intervalle [start, end[
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> int mergesort(Comparable[] tab, Comparable[] aux, int start, int end) {
        if ((end - start) <= 1) // tableau avec 0 ou 1 éléments : il est déjà trié
            return 0;
        int mid = start + (end-start)/2;
        int a = mergesort(tab, aux, start, mid), b = mergesort(tab, aux, mid, end);
        a += b;
        // merge
        System.arraycopy(tab, start, aux, start, end-start);
        int i = start, j = mid, k = start;
        while (i < mid && j < end) {
            if (aux[i].compareTo(aux[j]) <= 0) {
                tab[k++] = aux[i++];
            } else {
                a += (mid - i);
                tab[k++] = aux[j++];
            }
        }
        while (i < mid) { // le tableau d'après a été terminé avant
            //a += (mid - i);
            tab[k++] = aux[i++];
        }
        while (j < end) {
            tab[k++] = aux[j++];
        }
        return a;
    }
    @Test
    public void test1() {
        Integer[] tab = new Integer[] {2, 0, 5, 8, 3, 9, 4, 1, 6, 7};
        assertEquals(16, unsortedPairs(tab));
    }
    @Test
    public void test2() {
        Integer[] tab = new Integer[] {1, 3, 2, 5, 6, 4, 8};
        assertEquals(3, unsortedPairs(tab));
    }
}
