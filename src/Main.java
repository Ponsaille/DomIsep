public class Main {
    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.newPlayer();
        System.out.println(partie.getPlayers().get(0));
    }
}
