import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;
/**
 * The <code>Thing</code> the user gets to help them progress in the Hard level.
 * 
 * @author Peng Chen, Branson Lee
 * @version 1.0 May 31, 2012
 */
public class Item extends Thing
{
  /** 
   * An <code>ArrayList</code> of <code>Images</code> that will 
   * store the possible <code>Items</code>.
   * The game will randomly choose an <code>Item</code> to display.
   */
  private ArrayList <Image> iChoices = new ArrayList <Image>();
  /**
   * An <code>Image<bcode> to represent the <code>Item</code>.
   */
  public Image itemPic;
  /**
   * An <b>int</b> to represent which <code>Item</code> the user has.
   */
  private int theIndex;
  

  /**
   * Creates an <code>Item</code> at the specified <code>Coordinate</code>
   * and with the specified size.
   * 
   * @param x the specified <b>int</b> x point in the game
   * @param y the specified <b>int</b> y point in the game
   * @param w the specified <b>int</b> width of the <code>Item</code>
   * @param h the specified <b>int</b> height of the <code>Item</code>
   * @param index  the <b>int</b> index of the specified <code>Item</code> type
   */
  public Item(int x, int y, int w, int h, int index)
  {
    super (x,y,w,h);
    theIndex = index;
    itemPic = itemChoices(index);
  }
  /**
   * Adds all possible <code>Item</code> images into an <code>ArrayList</code> 
   * (declared class above) so the game may choose and set an index.
   * 
   * @param index the index of which <code>Item</code> will be used
   * @return Image the <code>Image</code> that will be returned
   */
  public Image itemChoices(int index)
  {
    iChoices.add (new ImageIcon (getClass ().getResource ("Images/Items/shield.png")).getImage ());
    iChoices.add (new ImageIcon (getClass ().getResource ("Images/Items/border.png")).getImage ());
    return iChoices.get(index);
  }
  
  /**
   * Gets the index of an <code>Item</code>.
   * 
   * @return int the index of an <code>Item</code>.
   */
  public int getIndex()
  {
    return theIndex;
  }
}