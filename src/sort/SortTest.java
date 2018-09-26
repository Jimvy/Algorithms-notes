package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;

// Test case restants : triée, triée à l'envers, un seul type d'élément, deux types d'éléments (mélangés, trié ->, trié <-), peu de type d'éléments différents (mélangés, trié ->, trié <-),
// taille 0, taille 1, taille 2, totalement trié à part 5% de la fin, totalement trié à part 5% des éléments qui sont dispersés.

public class SortTest {
    private static final int N = 1083;
    @Test
    public void testSelectionSort() {
        Sort<Integer> s = new SelectionSort<>();
        test1(s);
        testSlightlyUnsortedList(s);
        testSlightlyMoreUnsortedList(s);
        testQuasiSortedList(s);
        testSortedList(s);
        testReverseSortedList(s);
    }
    @Test
    public void testSelectionSort2() {
        Sort<Integer> s = new SelectionSort2<>();
        test1(s);
        testSlightlyUnsortedList(s);
        testSlightlyMoreUnsortedList(s);
        testQuasiSortedList(s);
        testSortedList(s);
        testReverseSortedList(s);
    }
    @Test
    public void testInsertionSort() {
        Sort<Integer> s = new InsertionSort<>();
        testReverseSortedList(s);
        testSortedList(s);
        testQuasiSortedList(s);
        testSlightlyMoreUnsortedList(s);
        testSlightlyUnsortedList(s);
        test1(s);
    }
    @Test
    public void testInsertion2Sort() {
        Sort<Integer> s = new InsertionSort2<>();
        testReverseSortedList(s);
        testSortedList(s);
        testQuasiSortedList(s);
        testSlightlyMoreUnsortedList(s);
        testSlightlyUnsortedList(s);
        test1(s);
    }
    /*@Test
    public void testInsertion2SortBis() {
    TODO tester le tri partiel
        Sort<Integer> s = new InsertionSort2<>();
        testReverseSortedList(s);
        testSortedList(s);
        testQuasiSortedList(s);
        testSlightlyMoreUnsortedList(s);
        testSlightlyUnsortedList(s);
        test1(s);
    }*/
    @Test
    public void testInsertion3Sort() {
        Sort<Integer> s = new InsertionSort3<>();
        testReverseSortedList(s);
        testSortedList(s);
        testSlightlyMoreUnsortedList(s);
        testSlightlyUnsortedList(s);
        testQuasiSortedList(s);
        test1(s);
    }
    @Test
    public void testShellSort() {
        Sort<Integer> s = new ShellSort<>();
        testReverseSortedList(s);
        testSortedList(s);
        testQuasiSortedList(s);
        testSlightlyMoreUnsortedList(s);
        testSlightlyUnsortedList(s);
        test1(s);
    }
    @Test
    public void testTDMergeSort() {
        Sort<Integer> s = new TDMergeSort<>();
        testReverseSortedList(s);
        testSortedList(s);
        testQuasiSortedList(s);
        testSlightlyMoreUnsortedList(s);
        testSlightlyUnsortedList(s);
        test1(s);
    }
    @Test
    public void testBUMergeSort() {
        Sort<Integer> s = new BUMergeSort<>();
        testQuasiSortedList(s);
        testSortedList(s);
        testReverseSortedList(s);
        testSlightlyUnsortedList(s);
        testSlightlyMoreUnsortedList(s);
        test1(s);
    }
    @Test
    public void testNaturalMergeSort() {
        Sort<Integer> s = new NaturalMergeSort<>();
        //testQuasiSortedList(s);
        //testSlightlyUnsortedList(s);
        //testSlightlyMoreUnsortedList(s);
        //testReverseSortedList(s);
        testSortedList(s);
        //test1(s);
    }
    @Test
    public void testQueueMergeSort() {
        Sort<Integer> s = new QueueMergeSort<>();
        testReverseSortedList(s);
        testSortedList(s);
        testSlightlyUnsortedList(s);
        testQuasiSortedList(s);
        testSlightlyMoreUnsortedList(s);
        test1(s);
    }
    @Test
    public void testHoarePartitionQuickSort() {
        Sort<Integer> s = new HoarePartitionQuickSort<>();
        testQuasiSortedList(s);
        testSortedList(s);
        testSlightlyUnsortedList(s);
        testSlightlyMoreUnsortedList(s);
        test1(s);
        testReverseSortedList(s);
    }
    @Test
    public void testHeapSort() {
        Sort<Integer> s = new HeapSort<>();
        testReverseSortedList(s);
        testQuasiSortedList(s);
        testSortedList(s);
        testSlightlyUnsortedList(s);
        testSlightlyMoreUnsortedList(s);
        test1(s);
    }
    /*@Test
    public void lol() {
        for (char c = 0; c < 65535; c++) {
            System.out.print('.');
            if (c % 200 == 0) System.out.println();
        }
        System.out.println((int)Character.MAX_VALUE);
        System.out.println((int)Character.MIN_VALUE);
        System.out.println(Character.BYTES);
    }*/

    /*
     * L'ensemble des tests effectués.
     */
    private void test1(Sort<Integer> s) {
        Random r = new Random(42);
        Integer[] tab = new Integer[N], tab2 = new Integer[N];
        for (int i = 0; i < N; i++)
            tab[i] = r.nextInt();
        System.arraycopy(tab, 0, tab2, 0, tab2.length);
        s.doSort(tab2);
        assertTrue(isSorted(tab, tab2));
    }
    private void testSortedList(Sort<Integer> s) {
        Integer[] tab = new Integer[N], tab2 = new Integer[N];
        for (int i = 0; i < tab.length; i++)
            tab[i] = i;
        System.arraycopy(tab, 0, tab2, 0, tab2.length);
        s.doSort(tab2);
        assertTrue(isSorted(tab, tab2));
    }
    private void testReverseSortedList(Sort<Integer> s) {
        Integer[] tab = new Integer[N], tab2 = new Integer[N];
        for (int i = 0; i < tab.length; i++)
            tab[i] = N - i;
        System.arraycopy(tab, 0, tab2, 0, tab2.length);
        s.doSort(tab2);
        assertTrue(isSorted(tab, tab2));
    }
    private void testSlightlyUnsortedList(Sort<Integer> s) {
        Integer[] tab = slighlyUnsortedList(N), tab2 = new Integer[N];
        System.arraycopy(tab, 0, tab2, 0, tab2.length);
        s.doSort(tab2);
        assertTrue(isSorted(tab, tab2));
    }
    private void testSlightlyMoreUnsortedList(Sort<Integer> s) {
        Integer[] tab = slightlyMoreUnsortedList(N), tab2 = new Integer[N];
        System.arraycopy(tab, 0, tab2, 0, tab2.length);
        s.doSort(tab2);
        assertTrue(isSorted(tab, tab2));
    }
    private void testQuasiSortedList(Sort<Integer> s) {
        Integer[] tab = quasiSortedList(N), tab2 = new Integer[N];
        System.arraycopy(tab, 0, tab2, 0, tab2.length);
        s.doSort(tab2);
        assertTrue(isSorted(tab, tab2));
    }
    private static <T extends Comparable<? super T>> boolean isSorted(T[] originalTab, T[] sortedTab) {
        Arrays.sort(originalTab);
        for (int i = 0; i < originalTab.length; i++) {
            if (!originalTab[i].equals(sortedTab[i]))
                return false;
        }
        return true;
    }

    /**
     * Génère une liste quasiment triée : certains éléments sont au plus à 2 échanges de leur vraie position
     * @param size la longueur de la liste que l'on veut obtenir ; doit être supérieur à 10 pour obtenir quelque chose d'intéressant
     * @return une liste quasiment triée
     */
    private static Integer[] slighlyUnsortedList(int size) {
        Integer[] list = new Integer[size];
        for (int i = 0; i < size; i++) {
            list[i] = i;
            if (i % 10 == 6) {
                /*
                 * A partir de [0 1 2 3 4 5 6 7 8 9], on obtient [0 1 3 5 2 6 4 7 8 9]
                 */
                int temp = list[i];
                list[i] = list[i - 2];
                list[i - 2] = list[i - 4];
                list[i - 4] = list[i - 3];
                list[i - 3] = list[i - 1];
                list[i - 1] = temp;
            }
        }
        return list;
    }

    /**
     * Génère une liste quasiment triée : certains éléments sont au plus à 5 échanges de leur vraie position.
     * @param size la longueur de la liste que l'on veut obtenir ; doit être supérieur à 10 pour obtenir quelque chose d'intéressant
     * @return une liste quasiment triée
     */
    private static Integer[] slightlyMoreUnsortedList(int size) {
        Integer[] list = new Integer[size];
        for (int i = 0; i < size; i++) {
            list[i] = i;
            if (i % 10 == 2 && i > 10) {
                /*
                 * à partir de [0 1 2 3 4 5 6 7 8 9 0 1 2],
                 * on obtient  [0 1 2 7 6 3 5 9 2 4 0 1 8]
                 */
                int temp = list[i];
                list[i] = list[i - 4];
                list[i - 4] = temp;
                temp = list[i - 3];
                list[i - 3] = list[i - 8];
                list[i - 8] = list[i - 6];
                list[i - 6] = list[i - 7];
                list[i - 7] = list[i - 9];
                list[i - 9] = list[i - 5];
                list[i - 5] = temp;
            }
        }
        return list;
    }

    /**
     * Génère une liste quasiment triée : un certain nombre d'éléments sont assez loins
     * de leur position normale, et les nombres entre sont décalés.
     * @param size la longueur de la liste que l'on veut obtenir
     * @return une liste quasiment triée
     */
    private static Integer[] quasiSortedList(int size) {
        Integer[] list = new Integer[size];
        /*
         * Pour chaque 20taine d'éléments, on va décaler les éléments entre 8 et 16 vers la gauche,
         * en plaçant le 8 au 16,
         * on va mettre l'élément 8 à la place de l'élément 16, l'élément 16 à la place de l'élément 4,
         * et l'élément 4 à la place de l'élément 8. Donc :
         * [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19] ->
         * [0 1 2 3 4 5 6 7 9 10 11 12 13 14 15 16 8 17 18 19] ->
         * [0 1 2 3 8 5 6 7 4 10 11 12 13 14 15 16 9 17 18 19]
         * ce qui signifie qu'on fait un truc un peu très compliqué
         */
        for (int i = 0; i < size; i++) {
            if ((i % 20) == 4)
                list[i] = i + 4;
            else if (i % 20 == 8)
                list[i] = i - 4;
            else if ((i % 20 >= 9) && (i % 20 <= 15))
                list[i] = i + 1;
            else if (i % 20 == 16)
                list[i] = i - 7;
            else
                list[i] = i;
        }
        return list;
    }
}
