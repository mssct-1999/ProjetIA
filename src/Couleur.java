public class Couleur {

    private String couleur;

    /**
     * Constructeur: Initialise couleur.
     */
    public Couleur(String _couleur) {
        this.couleur = _couleur;
    }

    /**
     * Retourne le libellé de l'attribut couleur
     * @return String
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * Retourne première lettre de la couleur
     * @return char
     */
    @Override
    public String toString() {
        char firstLetter = this.couleur.charAt(0);
        return String.valueOf(firstLetter);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Couleur other = (Couleur) obj;
        if (couleur == null) {
            if (other.couleur != null)
                return false;
        } else if (!couleur.equals(other.couleur))
            return false;
        return true;
    }
}
