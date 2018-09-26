package sort;

/**
 * Algorithme :
 * Il s'agit d'un Insertion Sort amélioré. Comme on le sait, insertion sort est efficace pour des petits tableaux,
 * ainsi que pour des petits tableaux avec peu d'éléments qui ne sont pas à leur place finale.
 * Il est moins efficace pour de grands tableaux peu triés.
 * L'idée de ShellSort est d'effectuer, à chaque itération, h insertion sort sur des tableaux de longueur N/h,
 * et de diminuer la valeur de h à chaque itération.
 * A chaque itération, l'ensemble du tableau est de plus en plus trié (approximativement), et donc les insertion sort
 * tirent leur épingle du jeu en devenant de plus en plus efficaces sur ces petits tableaux triés. A la fin,
 * lorsque h vaut 1, tout le tableau subit un unique insertion sort, qui est très rapide.
 * L'algorithme dépend de manière cruciale sur la valeur des pas h utilisés. Ici, on approxime en prenant h_0=1
 * et h_{i+1}=3*h_i+1, soit 1, 4, 13, 40, 121, 364, 1093, 3280, 9841... D'autres suites (increment sequence) sont possibles.
 * Propriétés :
 * - La complexité de l'algorithme dépend de la séquence de h utilisée. Et même pour les séquences connues,
 *   sa complexité reste inconnue. Cette version s'exécute, dans le pire des cas, en O(n^1.5).
 *   Le meilleur cas, celui d'une liste déjà triée, nécessite quand même d'exécuter ceiling(log_3(n)) itérations,
 *   avec pour chaque itération un certain nombre de insertion sort qui, en pratique, parcourent tous les éléments :
 *   la complexité du pire cas est donc O(n log(n)).
 * - L'algorithme n'est <b>pas</b> stable. Exemple [2 1_1 3 4 1_2], qui devient [1_2 1_1 3 4 2] puis [1_2 1_1 2 3 4].
 * @param <T> le type de données que l'on doit trier.
 */
public class ShellSort<T extends Comparable<? super T>> implements Sort<T> {
    // default constructor

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        ShellSort.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        int N = tab.length;
        int h = 1;
        while (h < N/3) {
            h = 3*h + 1;
        }
        for (; h >= 1; h = h/3) {
            for (int i = h, j; i < N; i++) {
                T temp = tab[i];
                for (j = i; j >= h && tab[j-h].compareTo(temp) > 0; j -= h) {
                    tab[j] = tab[j - h];
                }
                tab[j] = temp; // don't forget me !!!!!
            }
        }
    }
}
