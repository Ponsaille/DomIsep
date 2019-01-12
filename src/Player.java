
import java.util.*;
import java.util.stream.Collectors;


/**
 * Class Player
 */
public class Player {

  //
  // Fields
  //

  protected Side[][] kingdom;
  private int id;
  protected int mostLeftPosition = 4;
  protected int mostRightPosition = 4;
  protected int highestPosition = 4;
  protected int lowestPosition = 4;
  protected boolean isAI;
   
  //
  // Constructors
  //
  public Player (int id) {
    this.id = id;
    this.kingdom = new Side[9][9];
    for(int i = 0; i<9; i++) {
      Arrays.fill(this.kingdom[i], new Side("Vide", 0));
    }
    this.kingdom[4][4] = new Side("Chateau", 0);
    this.isAI = false;
  }
  
  //
  // Methods
  //


  //
  // Accessor methods
  //}

  public boolean getIsAI() {
      return isAI;
    }

  /**
   * Get the value of kingdom
   * @return the value of kingdom
   */
  public Side[][] getKingdom () {
    return kingdom;
  }

  public int getId() {
      return this.id;
  }

  //
  // Other methods
  //

  protected int[] getRightPosition(Domino domino, int[] leftPosition, int orientation) {
      int[] rightPosition = new int[2];
      switch (orientation) {
          case 0:
              rightPosition[0] = leftPosition[0]+1;
              rightPosition[1] = leftPosition[1];
              break;
          case 1:
              rightPosition[0] = leftPosition[0];
              rightPosition[1] = leftPosition[1]-1;
              break;
          case 2:
              rightPosition[0] = leftPosition[0]-1;
              rightPosition[1] = leftPosition[1];
              break;
          case 3:
              rightPosition[0] = leftPosition[0];
              rightPosition[1] = leftPosition[1]+1;
              break;
          default:
              System.out.println("L'orientation n'est pas correcte");
              break;
      }

      return rightPosition;
  }

  protected boolean canPlaceAt(Domino domino, int[] leftPosition, int orientation) {
      int[] rightPosition = getRightPosition(domino, leftPosition, orientation);

      return (isSideInside(leftPosition)
              && isSideInside(rightPosition)
              && isEmptyCell(leftPosition)
              && isEmptyCell(rightPosition)
              && isValidDominoTypes(domino, leftPosition, rightPosition));
  }

  public boolean placeDomino(Domino domino, int[] leftPosition, int orientation) {
    int[] rightPosition = getRightPosition(domino, leftPosition, orientation);

    if(canPlaceAt(domino, leftPosition, orientation)) {
      this.kingdom[leftPosition[0]][leftPosition[1]] = domino.getLeftSide();
      this.kingdom[rightPosition[0]][rightPosition[1]] = domino.getRightSide();

      this.updateExtremums(leftPosition);
      this.updateExtremums(rightPosition);
      return true;
    } else {
      System.out.println("Position invalide");
      return false;
    }
  }

  private boolean isEmptyCell(int[] position) {
    return this.kingdom[position[0]][position[1]].getType().equals("Vide");
  }

  protected boolean isSideInside(int[] position) {
    if(position[0] > 8 || position[1] > 8 || position[0] < 0 || position[1] < 0)  {
      return false;
    }

    //Editer les nouveaux extremums
    int mostLeftPosition = position[1] < this.mostLeftPosition ? position[1] : this.mostLeftPosition;
    int mostRightPosition = position[1] > this.mostRightPosition ? position[1] : this.mostRightPosition;
    int highestPosition = position[0] < this.highestPosition ? position[0] : this.highestPosition;
    int lowestPosition = position[0] > this.lowestPosition ? position[0] : this.lowestPosition;

    int width = mostRightPosition - mostLeftPosition;
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

  protected boolean isNearSameType(String type, int[] position) {
    int[][] positionsToCheck = {
            {position[0], position[1]+1},
            {position[0]-1, position[1]},
            {position[0], position[1]-1},
            {position[0]+1, position[1]}
    };
    for (int[] actualPosition : positionsToCheck) {
      if (isSideInside(actualPosition)) {
        String nextType = this.kingdom[actualPosition[0]][actualPosition[1]].getType();
        if (nextType.equals(type)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean canPlay() {
    if(this.mostRightPosition - this.mostLeftPosition < 4 && this.lowestPosition - this.highestPosition < 4) {
      return true;
    } else {
      for(int x = this.highestPosition; x <=  this.lowestPosition; x++) {
        for (int y = this.mostLeftPosition; y <= this.mostRightPosition; y++) {
      /*for(int x = 0; x <  9; x++) {
        for (int y = 0; y < 9; y++) {*/
          if(this.kingdom[x][y].getType().equals("Vide")) {
            int[] position = {x, y};
            if(this.isNearSameType("Vide", position)) {
              return true;
            }
          }
        }
      }
      return false;
    }
  }

  public boolean canPlace(Domino domino) {
      for(int x = 0; x <  9; x++) {
          for (int y = 0; y < 9; y++) {
              int[] position = {x, y};
              if(this.getKingdom()[x][y].getType() == "Vide" && isSideInside(position)) {
                  if(this.isNearSameType("Vide", position) &&
                          (this.isNearSameType(domino.getLeftSide().getType(), position)
                                  || this.isNearSameType(domino.getRightSide().getType(), position)
                                  || this.isNearSameType("Chateau", position))) {
                      return true;
                  }
              }
          }
      }
      return false;
  }

  protected List<List<Side>> getGroups(Side[][] kingdom) {
      List<List<Side>> groups = new ArrayList<>();
      Set<List<Integer>> alreadyVisited = new HashSet<>();
      for(int x = this.highestPosition; x <= this.lowestPosition; x++) {
          for (int y = this.mostLeftPosition; y <= this.mostRightPosition; y++) {
              String type = kingdom[x][y].getType();
              List<Integer> position = new ArrayList<>();
              position.add(x);
              position.add(y);
              if(!type.equals("Vide") && !alreadyVisited.contains(position)) {
                  List<Integer> firstPosition = new ArrayList<>();
                  firstPosition.add(x);
                  firstPosition.add(y);
                  List<Side> group = new ArrayList<>();
                  group.add(kingdom[x][y]);
                  Stack<List<Integer>> toVisit = new Stack<>();
                  getAdjacents(firstPosition, type, toVisit);
                  alreadyVisited.add(firstPosition);
                  while (!toVisit.empty()) {
                      List<Integer> nextPosition = toVisit.pop();
                      if(!alreadyVisited.contains(nextPosition)) {
                          alreadyVisited.add(nextPosition);
                          group.add(kingdom[nextPosition.get(0)][nextPosition.get(1)]);
                          getAdjacents(nextPosition, type, toVisit);
                      }
                  }
                  groups.add(group);
              }
          }
      }
      return groups;
  }

  protected int countPoints(Side[][] kingdom) {
      List<List<Side>> groups = this.getGroups(kingdom);

      int total = 0;
      Iterator<List<Side>> groupsIterator = groups.iterator();
      while (groupsIterator.hasNext()) {
          int crowns = 0;
          List<Side> group = groupsIterator.next();
          Iterator<Side> groupIterator = group.iterator();
          while (groupIterator.hasNext()) {
              crowns += groupIterator.next().getCrowns();
          }
          total += crowns*group.size();
      }
      return total;
  }

  public int countPoints() {
      return countPoints(this.kingdom);
  }

  protected void getAdjacents(List<Integer> position, String type, Stack toVisit) {
      int[][] positionsToCheck = {
              {position.get(0), position.get(1)+1},
              {position.get(0)-1, position.get(1)},
              {position.get(0), position.get(1)-1},
              {position.get(0)+1, position.get(1)}
      };

      for (int[] actualPosition : positionsToCheck) {
          if (isSideInside(actualPosition)) {
              Side actualSide = this.kingdom[actualPosition[0]][actualPosition[1]];
              if (actualSide.getType().equals(type)) {
                toVisit.push(Arrays.stream(actualPosition).boxed().collect(Collectors.toList()));
              }
          }
      }
  }
}
