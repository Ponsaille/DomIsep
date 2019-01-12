
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
