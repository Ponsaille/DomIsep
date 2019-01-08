
import java.awt.Color;
import java.util.*;


/**
 * Class Middle
 */
public class Middle {

  //
  // Fields
  //

  private List<Domino> firstColumn;
  private List<Domino> secondColumn;
  private Pioche pioche;
  private int nbDominos;
  private King[] kingsFirstPositions;
  private King[] kingsSecondPositions;
  
  //
  // Constructors
  //
  public Middle (int nbPlayer) {
    pioche = new Pioche(nbPlayer);
    
    switch(nbPlayer) {
      case 2:
      case 4:
        nbDominos = 4;
        break;
      case 3:
        nbDominos = 3;
        break;
      default:
      System.err.println("Le nombre de joueur n'est pas standart.");
    }

    firstColumn = new ArrayList<>();
    secondColumn = new ArrayList<>();
    kingsFirstPositions = new King[nbDominos];
    kingsSecondPositions = new King[nbDominos];
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Get the value of firstColumn
   * @return the value of firstColumn
   */
  private List<Domino> getFirstColumn () {
    return firstColumn;
  }

  /**
   * Get the value of secondColum
   * @return the value of secondColum
   */
  private List<Domino> getSecondColumn () {
    return secondColumn;
  }

  /**
   * Set the value of pioche
   * @param newVar the new value of pioche
   */
  private void setPioche (Pioche newVar) {
    pioche = newVar;
  }

  /**
   * Get the value of pioche
   * @return the value of pioche
   */
  private Pioche getPioche () {
    return pioche;
  }

  /**
   * Set the value of nbDominos
   * @param newVar the new value of nbDominos
   */
  private void setNbDominos (int newVar) {
    nbDominos = newVar;
  }

  /**
   * Get the value of nbDominos
   * @return the value of nbDominos
   */
  private int getNbDominos () {
    return nbDominos;
  }

  //
  // Other methods
  //

  /**
   */
  public void pick() {
    // Moving second column to the first one
    firstColumn = new ArrayList<>(secondColumn);
    secondColumn = new ArrayList<>();

    // Moving the kings to the first row
    kingsFirstPositions = kingsSecondPositions.clone();
    kingsSecondPositions = new King[this.nbDominos];

    // Populating the new second column
    for(int i = 0; i<nbDominos; i++) {
      secondColumn.add(pioche.pick());
    }

    System.out.println("First column");

    Iterator<Domino> iterator1 = firstColumn.iterator();
    while (iterator1.hasNext()) {
      System.out.println(iterator1.next().getPower());
    }

    System.out.println("Second colomun");

    Iterator<Domino> iterator = secondColumn.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next().getPower());
    }
  }


  /**
   */
  public void sort()
  {
    List<Domino> temp = new ArrayList<>();
    Iterator<Domino> secondIterator = secondColumn.iterator();
    while (secondIterator.hasNext()) {
      Domino next = secondIterator.next();
      Iterator<Domino> tempIterator = temp.iterator();
      int i = 0;
      while (tempIterator.hasNext() && tempIterator.next().getPower() < next.getPower()) {
        i = i+1;
      }
      temp.add(i, next);
    }

    secondColumn = temp;

    System.out.println("Sorted :");

    Iterator<Domino> iterator = secondColumn.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next().getPower());
    }
  }


  /**
   * @return       Domino[2][]
   */
  public Domino[][] getMiddle()
  {
    Domino[][] middle = new Domino[2][nbDominos];
    for (int i = 0; i<this.firstColumn.size(); i++) {
      middle[0][i] = this.firstColumn.get(i);
    }
    for (int i = 0; i<this.secondColumn.size(); i++) {
      middle[1][i] = this.secondColumn.get(i);
    }
    return middle;
  }

  public King[][] getKings() {
    King[][] kings = {
            kingsFirstPositions,
            kingsSecondPositions
    };

    return kings;
  }

  public void addKings(King[] kings) {
    this.kingsSecondPositions = kings;
    // Mélange les rois pour le premier round.
    for(int i = 0; i < this.kingsSecondPositions.length; i++) {
      int randVal = i + (int) (Math.random()*(this.kingsSecondPositions.length-i));
      King element = this.kingsSecondPositions[randVal];
      this.kingsSecondPositions[randVal] = this.kingsSecondPositions[i];
      this.kingsSecondPositions[i] = element;
    }
  }


  /**
   * @return Domino
   */
  public Domino moveKing(King king, int nextPosition) {
    if(nextPosition >= this.kingsFirstPositions.length || this.kingsSecondPositions[nextPosition] != null) {
      return null;
    }
    int position = Arrays.asList(this.kingsFirstPositions).indexOf(king);
    System.out.println(Arrays.toString(kingsFirstPositions));
    System.out.println(king);
    this.kingsFirstPositions[position] = null;
    this.kingsSecondPositions[nextPosition] = king;
    if(this.firstColumn.size() > 0) {
      return this.firstColumn.get(position);
    } else {
      return null;
    }
  }


  public void kingsRound() {
    Scanner scanner = new Scanner(System.in);
    for(int i = 0; i<this.kingsFirstPositions.length; i++) {
      King actualKing = this.kingsFirstPositions[i];
      System.out.println("Votre roi " + actualKing);
      Domino domino;
      do {
        try {
          domino = moveKing(actualKing, scanner.nextInt());
        } catch (InputMismatchException e) {
          System.out.println("Veuillez entrer un nombre entier");
          scanner.next();
          domino = null;
        }
      } while(domino == null);
      actualKing.getPlayer().moveDomino(domino);
      System.out.println();
    }
  }


}
