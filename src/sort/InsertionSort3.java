package sort;

/**
 * Algorithme :
 * Avant toute chose, l'algorithme recherche le premier plus petit élément et l'insère à sa place : il servira de sentinelle.
 * Pour i=1...n-1
 * - L'algorithme maintient l'invariant que les éléments 0...(i-1) sont ordonnés, mais ils ne sont pas encore
 *   à leur place définitive : l'algorithme peut les changer de place pour insérer un nouvel élément.
 * - Pour chaque élément en position i, l'algorithme l'insère parmi les éléments en position 0...(i-1),
 *   de sorte que ceux-ci restent ordonnés : l'élément précédent sera inférieur ou égal, l'élément suivant strictement supérieur.
 * - Pour ce faire, on sauvegarde l'élément E à insérer.
 * - Pour j=i décroissant (condition d'arrêt par la sentinelle) :
 *   - Si les éléments en position j-1 et j ne sont pas dans le bon ordre, on décale l'élément j-1 en j.
 *   - Dès que les éléments sont dans le bon ordre, on arrête de décaler et on insère E en j : l'élément a été inséré.
 * Propriétés :
 * - La complexité est O(n^2) dans le pire cas (en sens inverse), et Omega(n) dans le meilleur cas (déjà trié).
 *   En moyenne, n^2/4 comparaisons et n^2/4 échanges.
 *   Dans le pire des cas, il y a n(n-1)/2 échanges et comparaisons, et le sextuple d'accès.
 *   Dans le meilleur, n-1 comparaisons, 0 échanges et 2(n-1) accès (à un facteur près : le nombre exact est laissé au lecteur).
 * - L'algorithme est en-place.
 * - L'algorithme n'est <b>PLUS</b> stable : l'élément en position 0, après avoir été déplacé par la sentinelle,
 *   peut avoir atteri après un élément égal à lui, et l'ordre relatif est brisé.
 * @param <T> le type de données que l'on doit trier.
 */
public class InsertionSort3<T extends Comparable<? super T>> implements Sort<T> {
    // default constructor
    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        InsertionSort3.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        // [1 2 4 0 3] -> [
        int k = 0;
        for (int i = 0; i < tab.length; i++) // find smallest value : it will act as a sentinel value
            if (tab[i].compareTo(tab[k]) < 0)
                k = i;
        if (k != 0) {
            T temp = tab[0];
            tab[0] = tab[k];
            tab[k] = temp;
        }
        // This cause the algorithm to loose stability.
        for (int i = 1, j = 1; i < tab.length; i++) {
            T temp = tab[i]; // l'élément qui sera déplacé
            for (j = i; tab[j-1].compareTo(temp) > 0; j--) {
                tab[j] = tab[j - 1];
            }
            tab[j] = temp;
        }
    }
}
