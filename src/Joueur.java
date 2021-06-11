/**
 * Class: Joueur
 * Un joueur est représenté par son pseudo et la couleur de ses jetons
 */
 class Joueur {

    /**
     * Couleur des jetons du joueurs
     */
    private Couleur couleur;

    /**
     * Pseudo du joueur
     */
    private String pseudo;

    /**
     * Initialise le joueur avec la couleur et le pseudo donné
     */
    public Joueur(Couleur _couleur, String _pseudo) {
        this.pseudo = _pseudo;
        this.couleur = _couleur;
    }
}
