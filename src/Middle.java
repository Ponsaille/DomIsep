
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
    secondColumn = firstColumn;

    // Populating the new second column
    for(int i = 0; i<nbDominos; i++) {
      secondColumn.add(pioche.pick());
    } 

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

    System.out.println("End sort");

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


}
