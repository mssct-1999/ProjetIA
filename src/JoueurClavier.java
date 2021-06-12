import java.util.Scanner;

public class JoueurClavier extends Joueur {
    /**
     * Initialise le joueur avec la couleur et le pseudo donné
     *
     * @param _couleur
     * @param _pseudo
     */
    public JoueurClavier(Couleur _couleur, String _pseudo) {
        super(_couleur, _pseudo);
    }

    /**
     * Attend la saisie de l'utilisateur pour placer un jeton
     * @param grille
     * @return indexCol le numéro de la colonne où placer le jeton
     */
    @Override
    public int chooseColumn(Grille grille) {
        System.out.println("Où placer le prochain jeton ? Donnez un numéro de colonne entre 0 et " + Grille.LARGEUR_GRILLE + " : ");
        Scanner scan = new Scanner(System.in);
        int indexCol = scan.nextInt();
        return indexCol;
    }
}
