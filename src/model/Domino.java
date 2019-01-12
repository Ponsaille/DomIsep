package model;


/**
 * Class model.Domino
 */
public class Domino {

  //
  // Fields
  //

  private int power;
  private Side leftSide;
  private Side rightSide;
  
  //
  // Constructors
  //
  /**
   * @param        leftSide
   * @param        rightSide
   * @param        power
   */
  public Domino(Side leftSide, Side rightSide, int power)
  {
    this.leftSide = leftSide;
    this.rightSide = rightSide;
    this.power = power;
  }
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Get the value of power
   * @return the value of power
   */
  public int getPower () {
    return power;
  }

  /**
   * Get the value of leftSide
   * @return the value of leftSide
   */
  public Side getLeftSide () {
    return leftSide;
  }

  /**
   * Get the value of rightSide
   * @return the value of rightSide
   */
  public Side getRightSide () {
    return rightSide;
  }

}
