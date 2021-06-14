public class Main {

    public static void main(String[]args) {
        Couleur jaune = new Couleur("Jaune");
        Couleur rouge = new Couleur("Rouge");
        JoueurClavier jeanClaude = new JoueurClavier(jaune,"Jean-Claude VANDAMME");
        JoueurAlphaBeta bruce = new JoueurAlphaBeta(rouge,"Bruce LEE",new HeuristiqueAlignementPossible());
        Partie partie = new Partie(jeanClaude,bruce);
        Partie.partie = partie;
        partie.jouer();
    }
}
