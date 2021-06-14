/**
 * Class: Partie
 * Une partie est constituée de 2 joueurs
 */
public class Partie {

    private Joueur joueur1;
    private Joueur joueur2;
    private Grille grille;

    public static Partie partie = null;

    public Partie(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.grille = new Grille();
    }

    /**
     * Retourne la partie
     * @return Partie
     */
    public static Partie getPartie() {
        return Partie.partie;
    }

    /**
     * Lance une partie
     */
    public void jouer() {
        boolean finish = false;
        // joueur 1 démarre par défaut
        Joueur joueurCourant = this.joueur1;
        while(!finish) {
            this.playTour(joueurCourant);
            finish = this.analyseVictoireJoueur(joueurCourant) || this.analyseEgalite();
            joueurCourant = joueurSuivant(joueurCourant);
        }

        System.out.println("La partie est terminée ! Grille finale : ");
        System.out.println(grille);
        if (this.analyseEgalite()) {
            System.out.println("Egalité");
        }
        else {
            if (this.analyseVictoireJoueur(this.joueur1)) {
                System.out.println("C'est " + this.joueur1.getPseudo() + " qui a gagné !");
            }
            else {
                System.out.println("C'est " + this.joueur2.getPseudo() + " qui a gagné !");
            }
        }
    }

    /**
     * Retourne le joueur qui devra jouer après le joueur "j" passé en paramètre.
     * @param j
     * @return Joueur : le joueur suivant
     */
    public Joueur joueurSuivant(Joueur j) {
        return (j == this.joueur1) ? this.joueur2 : this.joueur1;
    }

    /**
     * Analyse et retourne vrai si le joueur à gagné
     * @param joueur
     * @return boolean
     */
    public boolean analyseVictoireJoueur(Joueur joueur) {
        return this.grille.chercheAlignementDeJeton(joueur.getCouleur());
    }

    /**
     * Si la grille est pleine, retourne vrai
     * @return boolean
     */
    public boolean analyseEgalite() {
        return this.grille.isGrilleFull();
    }

    /**
     * Fais jouer un tour au joueur donné
     * @param joueur Le joueur qui doit jouer un tour
     */
    public void playTour(Joueur joueur) {
        System.out.println("C'est au joueur " + joueur.getPseudo() + " de jouer !");
        boolean coupJouer = true;
        // tant que le joueur n'a pas jouer correctement -> boucle
        do {
            coupJouer = true;
            int indexCol = joueur.chooseColumn(this.grille,joueur);
            System.out.println("Le joueur " + joueur.getPseudo() + " joue dans la colonne " + indexCol);
            Jeton j = new Jeton(joueur.getCouleur());
            try {
                if (this.grille.isColonneFull(indexCol)) {
                    System.out.println("La colonne est pleine - Impossible de placer le jeton !");
                    System.out.println("Veuillez rejouer");
                    coupJouer = false;
                }
                else {
                    this.grille.insereJeton(indexCol, j);
                }
            } catch(ArrayIndexOutOfBoundsException err) {
                System.out.println("L'index souhaité n'est pas compris entre 1 et " + Grille.LARGEUR_GRILLE);
                System.out.println("Veuillez rejouer");
                coupJouer = false;
            }
        } while(!coupJouer);
        System.out.println("Ok, " + joueur.getPseudo() + " a joué, voici la grille : ");
        System.out.println(grille);
        System.out.println("\n");
    }

    public static void main(String[]args) {
        Couleur jaune = new Couleur("Jaune");
        Couleur rouge = new Couleur("Rouge");
        JoueurClavier jeanClaude = new JoueurClavier(jaune,"Jean-Claude VANDAMME");
        JoueurClavier bruce = new JoueurClavier(rouge,"Bruce LEE");
        Partie partie = new Partie(jeanClaude,bruce);
        partie.jouer();
    }
}
