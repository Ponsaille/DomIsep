

/**
 * Class Board
 */
public class Board {

  //
  // Fields
  //

  private List<Dominos> dominos;
  
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
  private void setDominos (List<Dominos> newVar) {
    dominos = newVar;
  }

  /**
   * Get the value of dominos
   * @return the value of dominos
   */
  private List<Dominos> getDominos () {
    return dominos;
  }

  //
  // Other methods
  //

}
