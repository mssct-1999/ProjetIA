/**
 * Class: HeuristiqueAlignementPossible
 * Cette heuristique compte le nombre d'alignement possible pour un joueur donné
 */
public class HeuristiqueAlignementPossible extends Heuristique {

    @Override
    public double noteGrille(Grille grille, Joueur joueur) {
        if (grille.chercheAlignementDeJeton(Partie.getPartie().joueurSuivant(joueur).getCouleur())) {
            return Heuristique.MIN_NOTE;
        }
        if (grille.chercheAlignementDeJeton(joueur.getCouleur())) {
            return Heuristique.MAX_NOTE;
        }
        Couleur couleur = joueur.getCouleur();
        double res = 0;
        for (int i = 0; i < Grille.LONGUEUR_GRILLE; i++) {
            for (int j = 0; j < Grille.LARGEUR_GRILLE; j++) {
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,0,1);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,1,1);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,1,0);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,1,-1);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,0,-1);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,-1,-1);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,-1,0);
                res += this.chercheAlignementDeJeton(grille,couleur,j,j,-1,1);
            }
        }
        return res;
    }

    /**
     * @param grille
     * @param couleur
     * @param indexCol
     * @param indexLigne
     * @param declinaisonHztale
     * @param declinaisonVrtcale
     * @return
     */
    private double chercheAlignementDeJeton(Grille grille, Couleur couleur, int indexCol, int indexLigne, int declinaisonHztale, int declinaisonVrtcale) {
        double res = 1;
        int tailleAlignement = 4;
        while(tailleAlignement != 0 && res != 0) {
            // On cherche l'alignement de la taille demandé
            Jeton jeton = null;
            try {
                Case c = grille.getCase(indexLigne, indexCol);
                jeton = c.getJeton();
            }
            catch(ArrayIndexOutOfBoundsException err) {
                // aucun alignement car hors de la grille
                res = 0;
            }
            // on teste la couleur du jeton
            if (jeton == null) {
                res*=0.5;
            }
            else if (jeton.getCouleur().equals(couleur)) {
                res*=1.0;
            }
            else {
                res*=0;
            }
            indexCol+=declinaisonHztale;
            indexLigne+=declinaisonVrtcale;
            tailleAlignement--;
        }
        return res;
    }
}
