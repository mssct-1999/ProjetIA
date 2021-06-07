public class Couleur {

    private String couleur;

    /**
     * Constructeur: Initialise couleur.
     */
    public Couleur(String _couleur) {
        this.couleur = _couleur;
    }

    /**
     * Retourne le libellé de l'attribut couleur
     * @return String
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * Retourne première lettre de la couleur
     * @return char
     */
    @Override
    public String toString() {
        char firstLetter = this.couleur.charAt(0);
        return String.valueOf(firstLetter);
    }
}
