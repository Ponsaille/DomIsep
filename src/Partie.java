
import java.util.*;


/**
 * Class Partie
 */
public class Partie {

  //
  // Fields
  //

  /**
   * Indique le nombre de joueurs dans une partie
   */
  private int nbPlayer;
  /**
   * List<Player>
   */
  private List<Player> players = new ArrayList();
  private int round;
  private int playing;
  private Middle middle;
  private int nbCanPlay = nbPlayer;
  
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
   * Set the value of nbPlayer
   * Indique le nombre de joueurs dans une partie
   * @param newVar the new value of nbPlayer
   */
  private void setNbPlayer (int newVar) {
    nbPlayer = newVar;
  }

  /**
   * Get the value of nbPlayer
   * Indique le nombre de joueurs dans une partie
   * @return the value of nbPlayer
   */
  private int getNbPlayer () {
    return nbPlayer;
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
  private List<Player> getPlayers () {
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
    players.add(new Player());
  }


  /**
   * @param        id
   */
  public Player getPlayer(int id)
  {
    return players.get(id);
  }


}
