public class Main {
    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.newPlayer();
        partie.newPlayer();
        System.out.println(partie.getPlayers());
        partie.run();
    }
}
