/**
 * Class: Heuristique
 * Cette heuristique note une grille pour savoir quel est son potentiel de gain pour le joueur donné
 */
public abstract class Heuristique {

    public static final int MAX_NOTE = Integer.MAX_VALUE;
    public static final int MIN_NOTE = Integer.MIN_VALUE;

    /**
     * Note la grille pour le joueur donné
     * @param grille
     * @param joueur
     * @return double (Note comprise entre MIN_VALUE et MAX_VALUE)
     */
    public abstract double noteGrille(Grille grille, Joueur joueur);
}
