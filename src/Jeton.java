/**
 * Class: Jeton
 * Un jeton est lié à une couleur / Le but du jeu est d'aligner 4 jetons de la même couleur
 */
public class Jeton {

    /**
     * Couleur du jeton
      */
    private Couleur couleur;

    /**
     * Constructeur du jeton - Un jeton à forcément une couleur
     * @param _couleur
     */
     public Jeton(Couleur _couleur) {
            this.couleur = _couleur;
     }

    /**
     * Getter sur la couleur du jeton
     * @return Couleur
     */
    public Couleur getCouleur() {
         return this.couleur;
    }

    /**
     * Un jeton est représenté par sa couleur : Retourne la première lettre de la couleur à laquelle il est associé
     * @return String
     */
     @Override
     public String toString() {
        if (this.couleur != null) {
            return this.couleur.toString();
        }
        else {
            return "";
        }
     }
}
