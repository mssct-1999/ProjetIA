public class JoueurAlphaBeta extends Joueur {

    private AlphaBeta alphaBeta;

    public JoueurAlphaBeta(Couleur couleur, String pseudo, Heuristique h) {
        super(couleur,pseudo);
        this.alphaBeta = new AlphaBeta(h);
    }

    @Override
    public int chooseColumn(Grille grille, Joueur j) {
        double colAJouer = this.alphaBeta.calculeColonneAJouer(grille,j);
        System.out.println("Colonne calculée par AlphaBeta : " + colAJouer);
        return this.alphaBeta.calculeColonneAJouer(grille,j);
    }
}
