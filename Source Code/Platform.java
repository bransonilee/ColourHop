import java.awt.*;
import javax.swing.*;
/**
 * The <code>Thing</code> the user must jump on with a colour 
 * specified in the <code>Game</code>.
 * 
 * @author Peng Chen, Branson Lee
 * @version 1.0 May 23, 2012
 */
public class Platform extends Thing
{
  /**
   * The <code>Color</code> of the <code>Platform</code>.
   */
  private Color itColor;
  /**
   * Creates a <code>Platform</code> at the specified 
   * location at the specified dimensions.
   * 
   * @param x  the specified <b>int</b> x point in the game
   * @param y  the specified <b>int</b> y point in the game
   * @param c  the specified <code>Color</code> of the <code>Platform</code>
   * @param w  the specified <b>int</b> width of the <code>Platform</code>
   * @param h  the specified <b>int</b> height of the <code>Platform</code>
   */
  public Platform(int x, int y, Color c, int w, int h)
  {
    super (x,y,w,h);
    itColor = c;
  }
  /**
   * Returns the current <code>Platform</code> colour.
   * 
   * @return the current <code>Platform</code> <code>Color</code>.
   */
  public Color getColour()
  {
    return itColor;
  }
}