package model;

import org.newdawn.slick.Color;
import java.util.*;


/**
 * Class model.Partie
 */
public class Partie {

  //
  // Fields
  //

  /**
   * List<model.Player>
   */
  private List<Player> players = new ArrayList();
  private Middle middle;
  private int gameStage;
  
  //
  // Constructors
  //
  public Partie () {
    gameStage = 0;
  };
  
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

  public int getGameStage() {
    return this.gameStage;
  }

  public void setGameStage(int gameStage) {
    this.gameStage = gameStage;
  }

  /**
   * Get the value of players
   * List<model.Player>
   * @return the value of players
   */
  public List<Player> getPlayers () {
    return players;
  }

  /**
   * Get the value of middle
   * @return the value of middle
   */
  public Middle getMiddle () {
    return middle;
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

  public void newAI() {
    if(players.size() < 4) {
      players.add(new AI(players.size()));
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

  public void start() {
    this.middle = new Middle(players.size());

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
    Color[] disponnibleColors = {Color.black,Color.blue,Color.red,Color.green};
    disponnibleColors = Arrays.copyOfRange(disponnibleColors, 0, this.players.size()*nbKingsPerPlayer);

    // Génération des rois
    King[] kings = new King[this.players.size()*nbKingsPerPlayer];
    for(int i = 0; i < kings.length; i++) {
      kings[i] = new King(disponnibleColors[i], this.players.get(i%this.players.size()));
    }
    // Ajoute les rois au milieu
    this.middle.addKings(kings);

    this.middle.pick();
    this.middle.sort();
  }

  public boolean isDone() {
    for (Player player : players) {
      if(player.canPlay()) {
        return true;
      }
    }
    return false;
  }
}
