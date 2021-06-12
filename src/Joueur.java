import java.util.Scanner;

/**
 * Class: Joueur
 * Un joueur est représenté par son pseudo et la couleur de ses jetons
 */
 public abstract class Joueur {

    /**
     * Couleur des jetons du joueurs
     */
    protected Couleur couleur;

    /**
     * Pseudo du joueur
     */
    protected String pseudo;

    /**
     * Initialise le joueur avec la couleur et le pseudo donné
     */
    public Joueur(Couleur _couleur, String _pseudo) {
        this.pseudo = _pseudo;
        this.couleur = _couleur;
    }

    /**
     * Retourne la couleur du joueur
     * @return Couleur
     */
    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * Retourne le pseudonyme du joueur
     * @return String
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * Attend la saisie de l'utilisateur pour placer un jeton
     *
     * @param grille
     * @return indexCol le numéro de la colonne où placer le jeton
     */
    public abstract int chooseColumn(Grille grille);
}
