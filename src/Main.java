import model.Player;

public class Main {
    public static void main(String[] args) {
        /*model.Partie partie = new model.Partie();
        partie.newPlayer();
        partie.newPlayer();
        System.out.println(partie.getPlayers());
        partie.run();*/
        Player player = new Player(0);
        System.out.println(player.countPoints());
    }
}
