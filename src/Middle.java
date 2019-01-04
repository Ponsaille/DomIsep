
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
   * Set the value of firstColumn
   * @param newVar the new value of firstColumn
   */
  private void setFirstColumn (Domino[] newVar) {
    firstColumn = newVar;
  }

  /**
   * Get the value of firstColumn
   * @return the value of firstColumn
   */
  private Domino[] getFirstColumn () {
    return firstColumn;
  }

  /**
   * Set the value of secondColum
   * @param newVar the new value of secondColum
   */
  private void setSecondColumn (Domino[] newVar) {
    secondColum = newVar;
  }

  /**
   * Get the value of secondColum
   * @return the value of secondColum
   */
  private Domino[] getSecondColumn () {
    return secondColum;
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
    this.kingsFirstPositions[position] = null;
    this.kingsSecondPositions[nextPosition] = king;
    System.out.println(this.firstColumn.get(position));
    return this.firstColumn.get(position);
  }


  public void kingsRound() {
    Scanner scanner = new Scanner(System.in);
    for(int i = 0; i<this.kingsFirstPositions.length; i++) {
      System.out.println("Votre roi " + this.kingsFirstPositions[i]);
      Domino domino;
      do {
        try {
          domino = moveKing(this.kingsFirstPositions[i], scanner.nextInt());
        } catch (InputMismatchException e) {
          System.out.println("Veuillez entrer un nombre entier");
          scanner.next();
          domino = null;
        }
      } while(domino == null);
      System.out.println();
    }
  }


}
