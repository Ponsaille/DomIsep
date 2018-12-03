
import java.util.*;


/**
 * Class Domino
 */
public class Domino {

  //
  // Fields
  //

  private int power;
  private Side leftSide;
  private Side rightSide;
  /**
   * Pour la periode de placement
   */
  private int leftPosition;
  /**
   * Au moment de la position
   */
  private int angle;
  
  //
  // Constructors
  //
  public Domino () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of power
   * @param newVar the new value of power
   */
  private void setPower (int newVar) {
    power = newVar;
  }

  /**
   * Get the value of power
   * @return the value of power
   */
  private int getPower () {
    return power;
  }

  /**
   * Set the value of leftSide
   * @param newVar the new value of leftSide
   */
  private void setLeftSide (Side newVar) {
    leftSide = newVar;
  }

  /**
   * Get the value of leftSide
   * @return the value of leftSide
   */
  private Side getLeftSide () {
    return leftSide;
  }

  /**
   * Set the value of rightSide
   * @param newVar the new value of rightSide
   */
  private void setRightSide (Side newVar) {
    rightSide = newVar;
  }

  /**
   * Get the value of rightSide
   * @return the value of rightSide
   */
  private Side getRightSide () {
    return rightSide;
  }

  /**
   * Set the value of leftPosition
   * Pour la periode de placement
   * @param newVar the new value of leftPosition
   */
  private void setLeftPosition (int newVar) {
    leftPosition = newVar;
  }

  /**
   * Get the value of leftPosition
   * Pour la periode de placement
   * @return the value of leftPosition
   */
  private int getLeftPosition () {
    return leftPosition;
  }

  /**
   * Set the value of angle
   * Au moment de la position
   * @param newVar the new value of angle
   */
  private void setAngle (int newVar) {
    angle = newVar;
  }

  /**
   * Get the value of angle
   * Au moment de la position
   * @return the value of angle
   */
  private int getAngle () {
    return angle;
  }

  //
  // Other methods
  //

  /**
   * @return       int[2]
   */
  public int[2] getLeftPosition()
  {
  }


  /**
   * @return       int[2]
   */
  public int[2] getRightPosition()
  {
  }


  /**
   * @param        leftSide
   * @param        rightSide
   * @param        power
   */
  public void Domino(Side leftSide, Side rightSide, int power)
  {
  }


  /**
   * @return       int
   */
  public int getPower()
  {
  }


}
