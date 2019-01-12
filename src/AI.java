
import java.util.*;


/**
 * Class AI
 */
public class AI extends Player {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public AI (int id) {
    super(id);
    this.isAI = true;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //
  private List<int[]> whereCanPlace(Domino domino) {
    List<int[]> positions = new ArrayList<>();
    for(int x = 0; x <  9; x++) {
      for (int y = 0; y < 9; y++) {
        int[] position = {x, y};
        if(this.getKingdom()[x][y].getType() == "Vide" && isSideInside(position)) {
          if(this.isNearSameType("Vide", position) &&
                  (this.isNearSameType(domino.getLeftSide().getType(), position)
                          || this.isNearSameType(domino.getRightSide().getType(), position)
                          || this.isNearSameType("Chateau", position))) {
            positions.add(position);
          }
        }
      }
    }
    return positions;
  }

  private int calcPoints(Domino domino, int[] leftPosition, int orientation) {
    Side[][] kingdom = new Side[9][9];
    for(int i = 0; i<kingdom.length; i++) {
      kingdom[i] = Arrays.copyOf(this.kingdom[i], this.kingdom.length);
    }
    int[] rightPosition = getRightPosition(domino, leftPosition, orientation);
    kingdom[leftPosition[0]][leftPosition[1]] = domino.getLeftSide();
    kingdom[rightPosition[0]][rightPosition[1]] = domino.getRightSide();

    return countPoints(kingdom);
  }

  private List<int[]> addOrientation(List<int[]> positions, Domino domino) {
    List<int[]> results = new ArrayList<>();
    for(int[] position:positions) {
      for(int i = 0; i<4; i++) {
        if(canPlaceAt(domino, position, i)) {
          int[] data = {position[0], position[1], i, calcPoints(domino, position, i)};
          results.add(data);
        }
      }
    }
    return results;
  }

  public int[] getBestPosition(Domino domino) {
    List<int[]> positions = addOrientation(whereCanPlace(domino), domino);
    int[] result = {0, 0, 0, 0};
    for(int[] position : positions) {
      if(position[3] >= result[3]) {
        result = position;
      }
    }

    return result;
  }

  public Domino bestDomino(List<Domino> dominos) {
    Domino bestDomino = dominos.get(0);
    int lastBestPoints = getBestPosition(dominos.get(0))[3];
    for(int i = 1; i< dominos.size(); i++) {
      int points = getBestPosition(dominos.get(i))[3];
      if(points > lastBestPoints) {
        lastBestPoints = points;
        bestDomino = dominos.get(i);
      }
    }
    return bestDomino;
  }

}
