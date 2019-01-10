
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;


/**
 * Class Pioche
 */
public class Pioche {

  //
  // Fields
  //

  /**
   * List<Domino>
   */
  private Stack<Domino> dominos;
  
  //
  // Constructors
  //
  /**
   * @param        nbPlayer
   */
  public Pioche(int nbPlayer)
  {
    this.dominos = new Stack<Domino>();
    // Charger les Dominos depuis le fichier csv
    this.populate();
    // Mélanger
    Collections.shuffle(dominos);
    // Supprimer ceux qui sont en trop en fonction du nombre de joueur
    switch (nbPlayer) {
      case 2:
        this.removeNDominos(24);
        break;
      case 3:
        this.removeNDominos(12);
        break;
      case 4:
        break;
      default:
        System.err.println("Le nombre de joueur n'est pas standart.");
    }
  }
  
  //
  // Methods
  //

  private void populate() {
    String csvFile = "data/dominos.csv";
    String filePath = new File(csvFile).getAbsolutePath();
    System.out.println(filePath);
    BufferedReader br = null;
    String line = "";
    Side leftSide, rightSide;

    try {
      br = new BufferedReader(new FileReader(filePath));
      line = br.readLine(); // Pour éviter d'avoir la première ligne
      while ((line = br.readLine()) != null) {
        String[] dominoData = line.split(",");
        leftSide = new Side(dominoData[1], Integer.parseInt(dominoData[0]));
        rightSide = new Side(dominoData[3], Integer.parseInt(dominoData[2]));
        this.dominos.push(new Domino(leftSide, rightSide, Integer.parseInt(dominoData[4])));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    if (br != null) {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  }

private void removeNDominos(int n) {
  for(int i=0; i<n; i++) {
    this.pick();
  }
}


  //
  // Accessor methods
  //

  /**
   * Set the value of dominos
   * List<Domino>
   * @param newVar the new value of dominos
   */
  private void setDominos (Stack<Domino> newVar) {
    dominos = newVar;
  }

  /**
   * Get the value of dominos
   * List<Domino>
   * @return the value of dominos
   */
  private Stack<Domino> getDominos () {
    return dominos;
  }

  //
  // Other methods
  //


  /**
   * @return       Domino
   */
  public Domino pick() { return dominos.pop(); }

  public boolean isEmpty() {
    return  dominos.isEmpty();
  }


}
