
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
    return 0;
  }


  /**
   * @return       Side[9][9]
   */
  private Side[][] getBoard()
  {
    // A modifier
    return this.kingdom;
  }

  
  // For testings purpose while wainting for the graphics
  public void moveDomino(Domino domino) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Orientation du domino dans le sens trigonométrique");
    int orientation = scanner.nextInt();
    System.out.println("Placement du coté gauche du domino");
    int[] leftPosition = new int[2];
    System.out.print("x: ");
    leftPosition[0] = scanner.nextInt();
    System.out.print("y: ");
    leftPosition[1] = scanner.nextInt();
    placeDomino(domino, leftPosition, orientation);
  }

  public void placeDomino(Domino domino, int[] leftPosition, int orientation) {
    int[] rightPosition = new int[2];
    switch (orientation) {
      case 0:
        rightPosition[1] = leftPosition[1]+1;
        rightPosition[0] = leftPosition[0];
        break;
      case 1:
        rightPosition[1] = leftPosition[1];
        rightPosition[0] = leftPosition[0]-1;
        break;
      case 2:
        rightPosition[1] = leftPosition[1]-1;
        rightPosition[0] = leftPosition[0];
        break;
      case 3:
        rightPosition[1] = leftPosition[1];
        rightPosition[1] = leftPosition[1]+1;
        break;
      default:
        System.out.println("L'orientation n'est pas correcte");
        break;
    }

    // Vérification du placement à réaliser
    System.out.println(domino);
    this.kingdom[leftPosition[0]][leftPosition[1]] = domino.getLeftSide();
    this.kingdom[rightPosition[0]][rightPosition[1]] = domino.getRightSide();
  }

  private boolean isGoodPosition(Domino domino, int[] leftPosition, Side leftSide) 
  {
    /*if(
      (kingdom[leftPosition-1] && kingdom[leftPosition-1].type.equals(leftSide.type)) ||
      (kingdom[leftPosition+1] && kingdom[leftPosition+1].type.equals(leftSide.type))
    )*/
    return true;
  }


}
