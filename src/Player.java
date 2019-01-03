
import java.awt.Color;
import java.util.*;


/**
 * Class Player
 */
public class Player {

  //
  // Fields
  //

  private Side[][] kingdom;
  private boolean canPlay;
   
  //
  // Constructors
  //
  public Player (int id) {
    this.kingdom = new Side[9][9];
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //}

  /**
   * Get the value of kingdom
   * @return the value of kingdom
   */
  private Side[][] getKingdom () {
    return kingdom;
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
  private int getPoints()
  {
  }


  /**
   * @return       Side[9][9]
   */
  private Side[][] getBoard()
  {
  }


  /**
   * @param        leftPosition
   * @param        rightPosition
   */
  private void placeDomino(int[] leftPosition, int[] rightPosition, Side leftSide, Side rightSide)
  {
    kingdom[leftPosition] = leftSide;
    kingdom[rightPosition] = rightSide;
  }

  private boolean isGoodPosition(int[] leftPosition, Side leftSide) 
  {
    /*if(
      (kingdom[leftPosition-1] && kingdom[leftPosition-1].type.equals(leftSide.type)) ||
      (kingdom[leftPosition+1] && kingdom[leftPosition+1].type.equals(leftSide.type))
    )*/
  }


}
