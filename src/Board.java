
import java.util.*;
/**
 * Class Board
 */
public class Board {

  //
  // Fields
  //

  private List<Domino> dominos;
  
  //
  // Constructors
  //
  public Board () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of dominos
   * @param newVar the new value of dominos
   */
  private void setDominos (List<Domino> newVar) {
    dominos = newVar;
  }

  /**
   * Get the value of dominos
   * @return the value of dominos
   */
  private List<Domino> getDominos () {
    return dominos;
  }

  //
  // Other methods
  //

}
