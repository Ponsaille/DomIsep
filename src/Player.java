
import java.util.*;


/**
 * Class Player
 */
public class Player {

  //
  // Fields
  //

  private Side[][] kingdom = new Side[9][9];
  private Color color;
  private int[] kingsPositions;
  private boolean canPlay;
  
  //
  // Constructors
  //
  public Player () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of kingdom
   * @param newVar the new value of kingdom
   */
  private void setKingdom (Side[][] newVar) {
    kingdom = newVar;
  }

  /**
   * Get the value of kingdom
   * @return the value of kingdom
   */
  private Side[][] getKingdom () {
    return kingdom;
  }

  /**
   * Set the value of color
   * @param newVar the new value of color
   */
  private void setColor (Color newVar) {
    color = newVar;
  }

  /**
   * Get the value of color
   * @return the value of color
   */
  private Color getColor () {
    return color;
  }

  /**
   * Set the value of kingsPositions
   * @param newVar the new value of kingsPositions
   */
  private void setKingsPositions (int[] newVar) {
    kingsPositions = newVar;
  }

  /**
   * Get the value of kingsPositions
   * @return the value of kingsPositions
   */
  private int[] getKingsPositions () {
    return kingsPositions;
  }

  /**
   * Set the value of canPlay
   * @param newVar the new value of canPlay
   */
  private void setCanPlay (boolean newVar) {
    canPlay = newVar;
  }

  /**
   * Get the value of canPlay
   * @return the value of canPlay
   */
  private boolean getCanPlay () {
    return canPlay;
  }

  //
  // Other methods
  //

  /**
   * @return       int
   */
  public int getPoints()
  {
  }


  /**
   * @return       Side[9][9]
   */
  public Side[][] getBoard()
  {
  }


  /**
   * @param        leftPosition
   * @param        rightPosition
   */
  public void placeDomino(int[] leftPosition, int[] rightPosition)
  {
  }


  /**
   * @param        color
   * @param        nbPlayer
   */
  public void Player(Color color, int nbPlayer)
  {
  }


}
