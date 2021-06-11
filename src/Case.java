/**
 * Class: Case
 * Une case peut être vide ou avec un jeton
 */
public class Case {

    private Jeton jeton;

    /**
     * Constructeur case vide sans jeton
     */
    public Case() {
        this.jeton = null;
    }

    /**
     * Constructeur case avec jeton
     * @param j Jeton
     */
    public Case(Jeton j) {
        this.jeton = j;
    }

    /**
     * Setter du jeton
     * @param jeton
     */
    public void setJeton(Jeton jeton) {
        this.jeton = jeton;
    }

    /**
     * Getter du  jeton
     * @return Jeton
     */
    public Jeton getJeton() { return this.jeton; }

    /**
     * Retourne vrai si la case est vide (sans jeton), false sinon
     * @return
     */
    public boolean isEmpty() {
        if (this.jeton == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Une case est soit vide soit occupée par un jeton. Retourne une chaine vide si aucun jeton, la couleur du jeton sinon
     * @return
     */
    @Override
    public String toString() {
        if (this.jeton != null) {
            return this.jeton.toString();
        }
        else {
            return " ";
        }
    }

}
