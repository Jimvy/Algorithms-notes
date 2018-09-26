package sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/*
Améliorations possibles :
- Passer à insertion sort pour de petits tableaux
- Arrêter la récursion pour de petits tableaux, puis passer à insertion sort sur tout le tableau
- Samplesort : on commence par trier un échantillon de taille 2^k-1 du tableau, qui contiendra les pivots à utiliser.
  Lors du partitionnement, on utilise la médiane de l'échantillon comme pivot, et on répartit les deux moitiés
  de l'échantillon dans les deux sous-tableaux, afin d'éviter de devoir le retrier ou de déterminer des pivots.
- Quicksort non récursif : utiliser une pile (et on insère d'abord le plus grand). Une file exploserait en taille.
- Calcul du pivot : aléatoire, median-of-3, median-of-5 (7 comparaisons), ninther (median-of-3 of 3 sub median-of-3)
- 3-way partition : une première version utilise des indices i, j, p et q, en plus de start et end, et fait en sorte
  que [start..p[ contienne des éléments == pivot, [p..i[ contienne des éléments < pivot, ]j..q] contienne
  des éléments > pivot, et ]q..end[ contienne des éléments == pivot. Lors du scan, on commencer par vérifier que
  l'élément est égal au pivot, auquel cas on le déplace au bon endroit (swap a[p] et a[i] ou swap a[q] et a[j]),
  et sinon on le laisse tel quel, et dans les deux cas on incrémente i ou décrémente j. A la fin,
  on déplace les éléments égaux à leur position finale, avec pleins de swap.
  Ce partitionnement sait gérer le cas où le tableau contient pleins d'éléments égaux, donc la remarque dans partition
  ne s'applique pas. Il est également plus rapide que la deuxième version.
  Le 3-way partition rend Quicksort bien plus rapide que mergesort en cas de clés identiques.
- 3-way partition : une deuxième version. On a i, p et q, tels que [start..p[ soit < pivot, [p..i[ soit == pivot,
  et ]q..end[ soit > pivot. Lors du scan, si < swap(a[i++], a[p++]), si > swap(a[i], a[q--]), sinon i++. Techniquement,
  l'indice j est inutile, car si on commencer par scanner en i, alors j sera épuisé quand viendra son tour.
  Défaut : il utilise trop de swap si il y a trop peu d'éléments == pivot.
 */
/**
 *
 * @param <T>
 */
public class HoarePartitionQuickSort<T extends Comparable<? super T>> implements Sort<T> {
    @Override
    public void doSort(T[] tab) {
        HoarePartitionQuickSort.sort(tab);
    }

    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        HoarePartitionQuickSort.sort(tab, 0, tab.length);
    }
    private static <T extends Comparable<? super T>> void sort(T[] tab, int start, int end) {
        if (end - start <= 1) return; // 1 element or less, already sorted
        int pivotIndex = start + (int)(Math.random() * (end-start));
        int mid = partition(tab, start, end, pivotIndex);
        // The item at position mid is already at the correct place
        if (mid - start < end - mid) {
            /* The first subarray is the smallest of the two : recurse on him to reduce, on average, call stack size
             * so that it is bound by O(log_2(n)) strictly. */
            sort(tab, start, mid);
            sort(tab, mid + 1, end);
        } else {
            // the second sub array is the smallest
            sort(tab, mid + 1, end);
            sort(tab, start, mid);
        }
    }
    private static <T extends Comparable<? super T>> int partition(T[] tab, int start, int end, int pivotIndex) {
        T pivot = tab[pivotIndex];
        tab[pivotIndex] = tab[start];
        int i = start + 1, j = end - 1;
        while (true) {
            /* In these two loops, we have to stop when tab[i] == pivot. If the array contains only one key,
             * it would otherwise increase i up to the end, resulting in an array with zero element
             * and the other with nearly all elements, resulting in quadratic performance for the sorting of this array.
             */
            while (i < end && pivot.compareTo(tab[i]) > 0) i++;
            while (j >= start && pivot.compareTo(tab[j]) < 0) j--;
            if (i >= j) break;
            T tmp = tab[i];
            tab[i] = tab[j];
            tab[j] = tmp;
        }
        tab[start] = tab[j];
        tab[j] = pivot;
        return j;
    }
    @Test
    public void testPartition1() {
        // (13, 15), (0, 5), (2, 0)
        Character[] tab = new Character[] {'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S'};
        assertEquals(15, HoarePartitionQuickSort.partition(tab, 0, tab.length, 13));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition2() {
        Integer[] tab = new Integer[] {2, 3, 1, 4};
        assertEquals(1, HoarePartitionQuickSort.partition(tab, 0, tab.length, 0));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition3() {
        Integer[] tab = new Integer[] {4, 3, 2, 1};
        assertEquals(3, HoarePartitionQuickSort.partition(tab, 0, tab.length, 0));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition4() {
        Integer[] tab = new Integer[] {2, 3, 1, 4};
        assertEquals(2, HoarePartitionQuickSort.partition(tab, 0, tab.length, 1));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition5() {
        Integer[] tab = new Integer[] {3, 2, 1};
        assertEquals(2, HoarePartitionQuickSort.partition(tab, 0, tab.length,0));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition6() {
        Integer[] tab = new Integer[] {1, 2};
        assertEquals(0, HoarePartitionQuickSort.partition(tab, 0, tab.length,0));
        System.out.println(Arrays.toString(tab));
    }
}
