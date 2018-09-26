package sort;

/**
 * Algorithme :
 * Pour i=1...n-1
 * - L'algorithme maintient l'invariant que les éléments 0...(i-1) sont ordonnés, mais ils ne sont pas encore
 *   à leur place définitive : l'algorithme peut les changer de place pour insérer un nouvel élément.
 * - Pour chaque élément en position i, l'algorithme l'insère parmi les éléments en position 0...(i-1),
 *   de sorte que ceux-ci restent ordonnés : l'élément précédent sera inférieur ou égal, l'élément suivant strictement supérieur.
 * - Pour ce faire, on sauvegarde l'élément E à insérer.
 * - Pour j=i...1
 *   - Si les éléments en position j-1 et j ne sont pas dans le bon ordre, on décale l'élément j-1 en j.
 *   - Dès que les éléments sont dans le bon ordre, on arrête de décaler et on insère E en j : l'élément a été inséré.
 * Propriétés :
 * - La complexité est O(n^2) dans le pire cas (en sens inverse), et Omega(n) dans le meilleur cas (déjà trié).
 *   En moyenne, n^2/4 comparaisons et n^2/4 échanges ou déplacements.
 *   Dans le pire des cas, il y a n(n-1)/2 échanges et comparaisons, et le quadruple d'accès.
 *   Dans le meilleur, n-1 comparaisons, 0 échanges et 3(n-1) accès (à cause de temp).
 * - L'algorithme est en-place.
 * - L'algorithme est stable.
 * @param <T> le type de données que l'on doit trier.
 */
public class InsertionSort2<T extends Comparable<? super T>> implements Sort<T> {
    // default constructor
    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        InsertionSort2.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        // [1 2 4 0 3] -> [
        for (int i = 1, j; i < tab.length; i++) {
            T temp = tab[i]; // l'élément qui sera déplacé
            for (j = i; j >= 1 && tab[j-1].compareTo(temp) > 0; j--) {
                tab[j] = tab[j - 1];
            }
            tab[j] = temp;
        }
    }

    /**
     * Trie le tableau {@code tab} entre les indices {@code startIndex} compris et {@code endIndex} non compris.
     * Voir description de la classe pour l'algorithme.
     * @param tab le tableau à trier.
     * @param startIndex Indice du premier élément du tableau à trier
     * @param endIndex Indice du premier élément hors du tableau à trier ; {@code endIndex - 1} est dès lors
     *                 l'indice du dernier élément du tableau à trier.
     * @param <T> le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    static <T extends Comparable<? super T>> void sort(T[] tab, int startIndex, int endIndex) {
        for (int i = startIndex + 1, j; i < endIndex; i++) {
            T temp = tab[i];
            for (j = i; j >= startIndex + 1 && tab[j-1].compareTo(temp) > 0; j--) {
                tab[j] = tab[j-1];
            }
            tab[j] = temp;
        }
    }
}
