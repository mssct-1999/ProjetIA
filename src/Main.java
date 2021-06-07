public class Main {

    public static void main(String[]args) {
        Couleur rouge = new Couleur("Rouge");
        Couleur jaune = new Couleur("Jaune");
        Jeton j = new Jeton(jaune);
        Case c1 = new Case(j);
        System.out.println(c1);
    }
}
