public class Main {

    public static void main(String[]args) {
        Grille grille = new Grille();
        Case[][] cases = grille.getCases();
        Jeton jetonJaune = new Jeton(new Couleur("Jaune"));
        Jeton jetonRouge = new Jeton(new Couleur("Rouge"));
        //System.out.println(grille.plusHautJetonIndex(0));

        grille.insereJeton(0,jetonJaune);
        grille.insereJeton(0,jetonJaune);
        grille.insereJeton(0,jetonJaune);
        grille.insereJeton(0,jetonJaune);
        grille.insereJeton(0,jetonJaune);
        grille.insereJeton(1,jetonRouge);

        System.out.println(grille);
    }
}
