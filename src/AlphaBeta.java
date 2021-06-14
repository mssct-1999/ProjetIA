import java.util.ArrayList;

/**
 * Class: AlphaBeta
 * Cet algo permet grâce à une heuristique de déterminer où le joueur peut jouer son prochain coup.
 */
public class AlphaBeta {

    private Heuristique heuristique;
    private static final int PROFONDEUR_EXPLORATION_ALPHA_BETA = 4;

    /**
     *
     * @param h
     */
    public AlphaBeta(Heuristique h) {
        this.heuristique = h;
    }

    /**
     * Utilise l'heuristique et l'algo alpha beta pour déterminer dans quelle colonne jouer le prochain jeton.
     * @param grille
     * @param j
     * @return
     */
    public int calculeColonneAJouer(Grille grille, Joueur j) {
        ArrayList<Integer> colonneAJouer = new ArrayList<Integer>();
        double valeurDeJeu = Heuristique.MIN_NOTE;
        for(int i = 0; i < Grille.LARGEUR_GRILLE; i++) {
            try {
                if (!grille.isColonneFull(i)) {
                    colonneAJouer.add(Integer.valueOf(i));
                    break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < Grille.LARGEUR_GRILLE; i++) {
            try {
                if (!grille.isColonneFull(i)) {
                    Grille copie = grille.copie();
                    Jeton jeton = new Jeton(j.getCouleur());
                    double valeurDeJeuCourante = this.alphabeta(copie,j,PROFONDEUR_EXPLORATION_ALPHA_BETA);
                    if (valeurDeJeuCourante == valeurDeJeu) {
                        colonneAJouer.add(Integer.valueOf(i));
                    }
                    else if(valeurDeJeuCourante > valeurDeJeu) {
                        colonneAJouer.clear();
                        valeurDeJeu = valeurDeJeuCourante;
                        colonneAJouer.add(Integer.valueOf(i));
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        int numColonneAJouer = (int)(Math.random() * colonneAJouer.size());
        return colonneAJouer.get(numColonneAJouer);
    }

    /**
     * Renvoi la valeur de jeu pour le joueur donné en utilisant l'algo alpha beta sur la profondeur donnée.
     * L'algo calcule la valeur de jeu en simulant tous les coups possible du joueur, puis l'opposant.
     * Cet ensemble de possibilité forme un arbre des possibles. Chaque noeud de cet arbre est une grille sur lequel s'applique l'algorithme.
     * Il minimise alors la valuer de jeu de l'opposant et maximise celle du joueur.
     * @param grille
     * @param j
     * @param profondeur
     * @return
     */
    private double alphabeta(Grille grille, Joueur j, int profondeur) {
        double alpha = Heuristique.MIN_NOTE;
        double beta = Heuristique.MAX_NOTE;
        return this.min(grille,j,profondeur,alpha,beta);
    }

    /**
     * Applique la partie min de min-max
     * @param grille
     * @param joueur
     * @param profondeur
     * @param alpha
     * @param beta
     * @return
     */
    private double min(Grille grille, Joueur joueur, int profondeur, double alpha, double beta) {
        if (profondeur != 0) {
            double valeurDeJeu = Heuristique.MAX_NOTE;
            for (int i = 0; i < Grille.LARGEUR_GRILLE; i++) {
                try {
                     if (!grille.isColonneFull(i)) {
                         Jeton jeton = new Jeton(Partie.getPartie().joueurSuivant(joueur).getCouleur());
                         Grille copie = grille.copie();
                         copie.insereJeton(i,jeton);
                         valeurDeJeu = Math.min(valeurDeJeu, this.max(copie,joueur,profondeur-1,alpha,beta));

                         if (alpha >= valeurDeJeu) {
                             return valeurDeJeu;
                         }
                         beta = Math.min(beta,valeurDeJeu);
                     }
                } catch(Exception err) {
                    err.printStackTrace();
                }
            }
            return valeurDeJeu;
        }
        else {
            return this.heuristique.noteGrille(grille,joueur);
        }
    }

    /**
     * Applique la partie max du min-max
     * @param grille
     * @param joueur
     * @param profondeur
     * @param alpha
     * @param beta
     * @return
     */
    private double max(Grille grille, Joueur joueur, int profondeur, double alpha, double beta) {
        if(profondeur !=0) {
            double valeurDeJeu = Heuristique.MIN_NOTE;
            for(int i = 0; i < Grille.LARGEUR_GRILLE; i++) {
                try {
                    if (!grille.isColonneFull(i)) {
                        Grille copie = grille.copie();
                        Jeton jeton = new Jeton(joueur.getCouleur());
                        copie.insereJeton(i,jeton);
                        valeurDeJeu = Math.max(valeurDeJeu, this.min(copie,joueur,profondeur-1,alpha,beta));
                        if(valeurDeJeu >= beta) {
                            return valeurDeJeu;
                        }
                        alpha = Math.max(alpha,valeurDeJeu);
                    }
                } catch(Exception err) {
                    err.printStackTrace();
                }
            }
            return valeurDeJeu;
        }
        else {
            return this.heuristique.noteGrille(grille,joueur);
        }
    }

}
