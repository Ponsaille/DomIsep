
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
  private int mostLeftPosition = 4;
  private int mostRightPosition = 4;
  private int highestPosition = 4;
  private int lowestPosition = 4;
   
  //
  // Constructors
  //
  public Player (int id) {
    this.kingdom = new Side[9][9];
    for(int i = 0; i<9; i++) {
      Arrays.fill(this.kingdom[i], new Side("Vide", 0));
    }
    this.kingdom[4][4] = new Side("Chateau", 0);
  }
  
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
  public Side[][] getKingdom () {
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
  public boolean getCanPlay () {
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
    return 0;
  }


  /**
   * @return       Side[9][9]
   */
  public Side[][] getBoard()
  {
    Side[][] board = new Side[5][5];
    for(int i = 0; i<5; i++) {
        board[i] = Arrays.copyOfRange(this.kingdom[this.highestPosition + i], this.mostLeftPosition, this.mostLeftPosition + 6);
    }
    return board;
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

    if(isSideInside(leftPosition)
            && isSideInside(rightPosition)
            && isEmptyCell(leftPosition)
            && isEmptyCell(rightPosition)
            && isValidDominoTypes(domino, leftPosition, rightPosition)
    ) {
      this.kingdom[leftPosition[0]][leftPosition[1]] = domino.getLeftSide();
      this.kingdom[rightPosition[0]][rightPosition[1]] = domino.getRightSide();

      this.updateExtremums(leftPosition);
      this.updateExtremums(rightPosition);
    } else {
      System.out.println("Position invalide");
      this.moveDomino(domino);
    }
  }

  private boolean isEmptyCell(int[] position) {
    return this.kingdom[position[0]][position[1]].getType().equals("Vide");
  }

  private boolean isGoodPosition(Domino domino, int[] leftPosition, int[] rightPosition, King king)
  {
    // Verifier si le cote droit est dans la bonne range

    return true;
  }

  private boolean isSideInside(int[] position) {
    if(position[0] > 8 || position[1] > 8 || position[0] < 0 || position[1] < 0)  {
      return  false;
    }

    //Editer les nouveaux extremums
    int mostLeftPosition = position[1] < this.mostLeftPosition ? position[1] : this.mostLeftPosition;
    int mostRightPositon = position[1] > this.mostRightPosition ? position[1] : this.mostRightPosition;
    int highestPosition = position[0] < this.highestPosition ? position[0] : this.highestPosition;
    int lowestPosition = position[0] > this.lowestPosition ? position[0] : this.lowestPosition;

    int width = mostRightPositon - mostLeftPosition;
    int heigh = lowestPosition - highestPosition;

    return (width < 5 && heigh < 5);
  }

  private void updateExtremums(int[] position) {
      this.mostLeftPosition = position[1] < this.mostLeftPosition ? position[1] : this.mostLeftPosition;
      this.mostRightPosition = position[1] > this.mostRightPosition ? position[1] : this.mostRightPosition;
      this.highestPosition = position[0] < this.highestPosition ? position[0] : this.highestPosition;
      this.lowestPosition = position[0] > this.lowestPosition ? position[0] : this.lowestPosition;
  }

  private boolean isValidDominoTypes(Domino domino, int[] leftPosition, int[] rightPosition) {
    return (this.isValidSideType(domino.getLeftSide(), leftPosition)
            || this.isValidSideType(domino.getRightSide(), rightPosition));
  }

  private boolean isValidSideType(Side side, int[] position) {
    int[][] positionsToCheck = {
            {position[0], position[1]+1},
            {position[0]-1, position[1]},
            {position[0], position[1]-1},
            {position[0]+1, position[1]}
    };

      for (int[] actualPosition : positionsToCheck) {
          if (isSideInside(actualPosition)) {
              String type = this.kingdom[actualPosition[0]][actualPosition[1]].getType();
              if (type.equals(side.getType())
                      || type.equals("Chateau")) {
                  return true;
              }
          }
      }
    return false;
  }


}
