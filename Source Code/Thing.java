import java.awt.*;
import javax.swing.*;
/* 
 * The super class that markers all the "Things" to be created in the game.
 * All Things have a Coordinate, as well as width and height.
 * 
 * @author Peng Chen, Branson Lee
 * @version 1.0 May 23rd, 2012
 */

public class Thing
{
  /**
   * A <code>Coordinate</code> of the current <code>Thing</code>.
   */
  private Coordinate itCoordinate;
  /**
   * The height of the current <code>Thing</code>.
   */
  private int height;
  /**
   * The width of the current <code>Thing</code>.
   */
  private int width;
  
  /** 
   * Creates a new Thing and store information about itself
   * 
   * @param x The specified <b>int</b> x point in the <code>Coordinate</code>
   * @param y The specified <b>int</b> y point in the <code>Coordinate</code>
   * @param w The specified <b>int</b> width of the <code>Thing</code>
   * @param h The specified <b>int</b> height of the <code>Thing</code>
   */
  public Thing(int x, int y, int w, int h)
  {
    itCoordinate = new Coordinate (x,y);
    width = w;
    height = h;
  }
  /**
   * Returns the current <code>Coordinate</code>.
   * 
   * @return The Tcurrent <code>Coordinate</code>.
   */
  public Coordinate getCoordinate()
  {
    return itCoordinate;
  }
  /**
   * Returns the current height.
   * 
   * @return The current height
   */    
  public int getHeight()
  {
    return height;
  }
  /**
   * Returns the current width.
   * 
   * @return The current width
   */
  public int getWidth()
  {
    return width;
  }
}