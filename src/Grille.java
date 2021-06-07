import java.util.ArrayList;

public class Grille {

    public static final int LARGEUR_GRILLE = 3;
    public static final int LONGUEUR_GRILLE = 4;
    private Case[][] cases;

    /**
     * Initialisation de la grille avec uniquement des cases vides
     */
    public Grille() {
        this.cases = new Case[LONGUEUR_GRILLE][LARGEUR_GRILLE];
        for(int i = 0; i < LONGUEUR_GRILLE; i++) {
            for (int j = 0; j < LARGEUR_GRILLE; j++) {
                this.cases[i][j] = new Case();
            }
        }
    }

    public Case[][] getCases() {
        return this.cases;
    }

    /**
     * Retourne la case aux index renseignés
     * @param indexCol int
     * @param indexCol int
     * @return Case Object
     */
    public Case getCase(int indexLigne, int indexCol) {
        return this.cases[indexLigne][indexCol];
    }

    /**
     * Place un jeton aux index renseignés. Retourne vrai si le jeton a pu être placé, false sinon.
     * @param indexCol int
     * @param indexLigne int
     * @param j Jeton
     * @return boolean
     */
    public boolean placerJeton(int indexLigne , int indexCol, Jeton j) {
        if (this.cases[indexLigne][indexCol].isEmpty()) {
            this.cases[indexLigne][indexCol] = new Case(j);
            return true;
        }
        else {
            return false;
        }
    }

    /*public boolean insereJeton(int indexCol, Jeton j) {
        int indexLigne = this.plusHautJetonIndex(indexCol);

    }*/

    /**
     * Retourne vrai si la colonne passé en paramètre est pleine
     * @param indexCol
     * @return boolean
     */
    public boolean isColonneFull(int indexCol) {
        return (this.plusHautJetonIndex(indexCol) == LONGUEUR_GRILLE);
    }

    /**
     * Retourne l'index de la ligne du plaut haut jeton de la colonne donnée.
     * @param indexCol int
     * @return int
     */
    public int plusHautJetonIndex(int indexCol) {
        int indexPlusHaut = 0;
        for (int i = 0; i < LONGUEUR_GRILLE; i++) {
            if (!this.getCase(i,indexCol).isEmpty()) {
                return i;
            }
        }
        return indexPlusHaut;
    }

    /**
     * Affichage de la grille et des éventuels jeton qu'elle contient
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < LONGUEUR_GRILLE; i++) {
            for (int j = 0; j < LARGEUR_GRILLE; j++) {
                Case c = this.getCase(i,j);
                if (c.isEmpty()) {
                    if (j == 0) {
                        str += "| |";
                    }
                    else {
                        str += " |";
                    }
                }
                else {
                    if (j == 0) {
                        str += "|"+ c.toString() +"|";
                    }
                    else {
                        str += c.toString()+ "|";
                    }
                }
            }
            str += "\n";
        }
        return str;
    }

    // test
    public static void main(String[]args) {
        Grille grille = new Grille();
        Case[][] cases = grille.getCases();
        Jeton jetonJaune = new Jeton(new Couleur("Jaune"));
        Jeton jetonRouge = new Jeton(new Couleur("Rouge"));

        grille.placerJeton(2,0,jetonJaune);
        grille.placerJeton(3,0,jetonJaune);
        System.out.println(grille.isColonneFull(0));
        int indexLigneCol1 = grille.plusHautJetonIndex(0);
        System.out.println(indexLigneCol1);
        //grille.placerJeton(3,1, jetonRouge);
        System.out.println(grille);
    }
}
