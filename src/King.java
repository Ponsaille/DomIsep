import org.newdawn.slick.Color;

/**
 * Class King
 */
public class King {

  //
  // Fields
  //
  private Color color;
  private Player player;
  
  //
  // Constructors
  //
  public King (Color color, Player player) {
    this.color = color;
    this.player = player;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //
  public Color getColor () {
    return this.color;
  }

  public Player getPlayer() {
    return this.player;
  }

  //
  // Other methods
  //

}
