
import java.awt.Color;
import java.util.*;


/**
 * Class Partie
 */
public class Partie {

  //
  // Fields
  //

  /**
   * List<Player>
   */
  private List<Player> players = new ArrayList();
  private int round;
  private int playing;
  private Middle middle;
  private int nbCanPlay  = 0;
  private Scanner scanner = new Scanner(System.in);
  
  //
  // Constructors
  //
  public Partie () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Get the value of nbPlayer
   * Indique le nombre de joueurs dans une partie
   * @return the value of nbPlayer
   */
  public int getNbPlayer () {
    return players.size();
  }

  /**
   * Set the value of players
   * List<Player>
   * @param newVar the new value of players
   */
  private void setPlayers (Player[] newVar) {
    players = newVar;
  }

  /**
   * Get the value of players
   * List<Player>
   * @return the value of players
   */
  public List<Player> getPlayers () {
    return players;
  }

  /**
   * Set the value of round
   * @param newVar the new value of round
   */
  private void setRound (int newVar) {
    round = newVar;
  }

  /**
   * Get the value of round
   * @return the value of round
   */
  private int getRound () {
    return round;
  }

  /**
   * Set the value of playing
   * @param newVar the new value of playing
   */
  private void setPlaying (int newVar) {
    playing = newVar;
  }

  /**
   * Get the value of playing
   * @return the value of playing
   */
  private int getPlaying () {
    return playing;
  }

  /**
   * Set the value of middle
   * @param newVar the new value of middle
   */
  private void setMiddle (Middle newVar) {
    middle = newVar;
  }

  /**
   * Get the value of middle
   * @return the value of middle
   */
  private Middle getMiddle () {
    return middle;
  }

  /**
   * Set the value of nbCanPlay
   * @param newVar the new value of nbCanPlay
   */
  private void setNbCanPlay (int newVar) {
    nbCanPlay = newVar;
  }

  /**
   * Get the value of nbCanPlay
   * @return the value of nbCanPlay
   */
  private int getNbCanPlay () {
    return nbCanPlay;
  }

  //
  // Other methods
  //

  /**
   */
  public void nextRound()
  {
  }


  /**
   */
  public void newPlayer()
  {
    if(players.size() < 4) {
      players.add(new Player(players.size()));
    } else {
      System.err.println("The maximum players number has alredy been reached");
    }
  }


  /**
   * @param        id
   */
  public Player getPlayer(int id)
  {
    return players.get(id);
  }


  public boolean isPlayable() {
    return players.size() > 1;
  }

  public void run() {
    this.middle = new Middle(players.size());
    this.middle.pick();
    this.middle.sort();

    // Setting the number of kings per player
    int nbKingsPerPlayer;
    switch(players.size()) {
      case 2:
        nbKingsPerPlayer = 2;
        break;
      case 3:
      case 4:
        nbKingsPerPlayer = 1;
        break;
      default:
        System.err.println("Le nombre de joueur n'est pas standart");
        return;
    }
    // Pour éviter d'avoir 2 fois la même couleur
    Color[] disponnibleColors = {Color.BLACK,Color.BLUE,Color.RED,Color.GREEN};
    disponnibleColors = Arrays.copyOfRange(disponnibleColors, 0, this.players.size()*nbKingsPerPlayer);

    // Génération des rois
    King[] kings = new King[this.players.size()*nbKingsPerPlayer];
    for(int i = 0; i < kings.length; i++) {
      kings[i] = new King(disponnibleColors[i], this.players.get(i%this.players.size()));
    }
    // Ajoute les rois au milieu
    this.middle.addKings(kings);

    this.middle.pick();

    this.middle.kingsRound();
    
    //Reset iterator
    //playersIterator = players.iterator();
    /*
    int playerId = 0;
    while(playersIterator.hasNext()) {
      Player actualPlayer = playersIterator.next();
      List<Color> kings = actualPlayer.getKings();
      for(int i = 0; i<kings.size(); i++) {
        System.out.println("Votre roi " + kings.get(i).getRGB());
        System.out.println("Joueur " + (playerId+1) + " sur quel domino voulez-vous vous placer votre roi numero " + (i+1));
        this.middle.moveKing(kings.get(i), scanner.nextInt());
      }
      playerId++;
    }*/
  }
}
