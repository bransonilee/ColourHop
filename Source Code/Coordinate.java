/**
 * This class has all the information regarding where a <code>Thing</code> is.
 * The set location of a <code>Thing</code> is its top left corner.
 * <p>
 * GRIDWORLD LOCATION CLASS WAS USED AS A TEMPLATE!
 * 
 * @author Peng Chen, Branson Lee
 * @version Version 1.0 May 18, 2012
 */
public class Coordinate 
{
  /**
   * The <b>int</b> x point in the <code>Coordinate</code>.
   */
  private int x; 
  /**
   * The <b>int</b> y point in the <code>Coordinate</code>.
   */
  private int y; 
  
  /**
   * Constructs a postion with given x and y coordinates.
   * 
   * @param xPos the <b>int</b> x postion
   * @param yPos the <b>int</b> y postion
   */
  public Coordinate(int xPos, int yPos)
  {
    x=xPos;
    y=yPos ;
  }
  
  /**
   * Gets the x coordinate.
   * 
   * @return the <b>int</b> x postion.
   */
  public int getXPos()
  {
    return x;
  }
  
  /**
   * Gets the y coordinate.
   * 
   * @return the <b>int</b> y postion.
   */
  public int getYPos()
  {
    return y;
  }
  
  /**
   * Sets the x coordinate.
   * 
   * @param  newX the new x postion
   */
  public void setXPos(int newX)
  {
    x = newX;
  }
  
  /**
   * Sets the y coordinate.
   * 
   * @param newY the y postion
   */
  public void setYPos(int newY)
  {
    y = newY;
  }
  
  /**
   * Indicates whether some other <code>Coordinate</code> object is "equal to"
   * this one.
   * 
   * @param other the other coordinate to test
   * @return <code>true</code> if <code>other</code> is a
   * <code>Coordinate</code> with the same x and y as this coordinate;
   * <code>false</code> otherwise.
   */
  public boolean equals(Object other)
  {
    if (!(other instanceof Coordinate))
      return false;
    
    Coordinate otherLoc = (Coordinate) other;
    return getXPos() == otherLoc.getXPos() && getYPos() == otherLoc.getYPos();
  }
  
  /**
   * Creates a string that describes this coordinate.
   * 
   * @return a <code>String</code> with the x and y of this coordinate, 
   * in the format (x, y).
   */
  public String toString()
  {
    return "(" + getXPos() + ", " + getYPos() + ")";
  }
}
