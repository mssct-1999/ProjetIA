/**
 * Class: Grille
 * Une grille est représentée par une matrice de Case et peut contenir des Jeton
 */
public class Grille {

    public static final int LARGEUR_GRILLE = 7;
    public static final int LONGUEUR_GRILLE = 6;
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

    /**
     * Insère un jeton dans la colonne insiquée si cela est possible (si la colonne n'est pas pleine)
     * @param indexCol
     * @param j
     * @return
     */
    public boolean insereJeton(int indexCol, Jeton j) {
        if (this.isColonneFull(indexCol)) {
            System.out.println("La colonne dans laquelle on tente d'insérer le jeton " + j + " est pleine");
            return false;
        }
        else {
            int indexLigne = this.plusHautJetonIndex(indexCol);
            this.placerJeton(indexLigne,indexCol,j);
            return true;
        }
    }

    /**
     * Retourne vrai si la colonne passé en paramètre est pleine
     * @param indexCol
     * @return boolean
     */
    public boolean isColonneFull(int indexCol) {
        return (this.plusHautJetonIndex(indexCol) == -1 && !this.getCase(0,indexCol).isEmpty());
    }

    /**
     * Retourne l'index de la ligne du plaut haut jeton de la colonne donnée.
     * @param indexCol int
     * @return int
     */
    public int plusHautJetonIndex(int indexCol) {
        int indexPlusHaut = LONGUEUR_GRILLE-1;
        for (int i = 0; i < LONGUEUR_GRILLE; i++) {
            if (!this.getCase(i,indexCol).isEmpty()) {
                return i-1;
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

    /**
     * Retourne vrai si il existe un alignement de 3 jetons de la couleur passé en paramètre.
     * La recherche se fait à partir de l'index de la colonne (indexCol) et de la ligne (indexLigne).
     * Elle va dans le sens des déclinaisons horizontales et verticales fournies en paramètre. Si on donne (0,0) alors la recherche se fera sur la même case.
     * (1,1) la recherche se fera en diagonale "Nord-Est". (-1,0) Direction "Ouest"
     * @param couleur
     * @param indexCol
     * @param indexLigne
     * @param declinaisonHztale
     * @param declinaisonVrtcale
     * @return
     */
    public boolean chercheAlignementDeJeton(Couleur couleur, int indexCol, int indexLigne, int declinaisonHztale, int declinaisonVrtcale) {
        boolean alignementPreserve = true;
        int tailleAlignement = 4;
        Jeton jeton = null;
        while (tailleAlignement != 0 && alignementPreserve) {
            try {
                /*if (declinaisonHztale == 0 && declinaisonVrtcale == 1) {
                    System.out.println("Recherche Sud / Ligne " + indexLigne + " Col " + indexCol);
                }
                if (declinaisonHztale == 1 && declinaisonVrtcale == 0) {
                    System.out.println("Recherche Est / Ligne " + indexLigne + " Col " + indexCol);
                }
                if (declinaisonHztale == 1 && declinaisonVrtcale == 1) {
                    System.out.println("Recherche Sud Est / Ligne " + indexLigne + " Col " + indexCol);
                }
                if (declinaisonHztale == 0 && declinaisonVrtcale == 0) {
                    System.out.println("Recherche Case courante / Ligne " + indexLigne + " Col " + indexCol);
                }*/
                // On récupère le jeton correspondant pour vérifier sa couleur
                Case c = this.getCase(indexLigne, indexCol);
                jeton = c.getJeton();
                // on teste si il y a un jeton et si la couleur du jeton correspond à la couleur de l'alignement
                if (jeton == null || !jeton.getCouleur().equals(couleur)) {
                    alignementPreserve = false;
                }
                // on cherche sur la prochaine et on réduit le nombre de cases à chercher et donc l'alignement
                indexCol+=declinaisonHztale;
                indexLigne+=declinaisonVrtcale;
                tailleAlignement--;
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                // L'alignement n'existe pas puisqu'on est hors grille
                alignementPreserve = false;
                break;
            }
        }
        return alignementPreserve;
    }

    /**
     * Vérifie sur l'intégralité de la grille s'il existe un alignement de 3 jetons de la couleur renseignées.
     * @param couleur
     * @return boolean
     */
    public boolean chercheAlignementDeJeton(Couleur couleur) {
        boolean alignementTrouve = false;

        for (int i = 0; i < LONGUEUR_GRILLE && !alignementTrouve; i++) {
            for(int j = 0; j < LARGEUR_GRILLE && !alignementTrouve; j++) {
                alignementTrouve = this.chercheAlignementDeJeton(couleur,j,i,0,1) ||
                        this.chercheAlignementDeJeton(couleur,j,i,1,1) ||
                        this.chercheAlignementDeJeton(couleur,j,i,1,0) ||
                        this.chercheAlignementDeJeton(couleur,j,i,1,-1) ||
                        this.chercheAlignementDeJeton(couleur,j,i,0,-1) ||
                        this.chercheAlignementDeJeton(couleur,j,i,-1,-1) ||
                        this.chercheAlignementDeJeton(couleur,j,i,-1,0) ||
                        this.chercheAlignementDeJeton(couleur,j,i,-1,1);

            }
        }
        return alignementTrouve;
    }


    /**
     * Retourne vrai si toutes les colonnes sont pleines, false sinon.
     * @return boolean
     */
    public boolean isGrilleFull() {
        for (int i = 0; i < LARGEUR_GRILLE; i++) {
            try {
                if (!this.isColonneFull(i)) {
                    return false;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // test
    public static void main(String[]args) {
        Grille grille = new Grille();
        Couleur jaune = new Couleur("Jaune");
        Couleur rouge = new Couleur("Rouge");
        Case[][] cases = grille.getCases();
        Jeton jetonJaune = new Jeton(new Couleur("Jaune"));
        Jeton jetonRouge = new Jeton(new Couleur("Rouge"));

        System.out.println(grille.isGrilleFull());
        System.out.println(grille);
    }
}
