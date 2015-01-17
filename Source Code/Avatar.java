import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

/**
 * An <code>Avatar</code> is a <code>Rectangle</code> that will move 
 * based on their user's keyboard entry.
 * 
 * @author Peng Chen, Branson Lee
 * @version Version 1.0 May 31, 2012
 */
public class Avatar extends Rectangle
{

  /**
   * An <code>Image</code> to represent the <code>Avatar</code>. It is static 
   * because there can only be ONE <code>Avatar</code>.
   */
  public static Image avatarPic;
  
  /**
   * To see if this <code>Avatar</code> has an <code>Item</code> in its inventory.
   */
  private Item inventory = null;
  
  /**
   * Constructs an <code>Avatar</code> based on the user set index value.
   * 
   * @param x The specified int x position
   * @param y The specified int y position
   * @param width The predetermined final int width of the <code>Avatar</code>
   * @param height The predetermined final int height of the <code>Avatar</code>
   */
  public Avatar(int x, int y,int width, int height, int index)
  {
    super (x,y,width,height);
    avatarPic = avatarChoices(index);
 }

  /**
   * Gets the <code>Item</code> that the <code>Avatar</code> may have.
   */
  public Item getItem ()
  {
    return inventory;
  }
  /**
   * Gets the <code>Item</code> that the <code>Avatar</code> may have to the specified.
   * 
   * @param item  The new <code>Item</code> that the <code>Avatar</code> will have
   */
  public void setItem(Item item)
  {
    inventory = item;
  }
  /**
   * Adds all possible <code>Avatar</code> images into an <code>ArrayList</code> 
   * (declared class above) so the user may choose and set an index.
   * <p>
   * VARIABLE DICTIONARY:<br>
   * aChoices   An <code>ArrayList</code> of <code>Images</code> that will 
   *            store the possible <code>Avatars</code>. The user will pick one  
   *            out of this list in <code>Settings</code> if they choose.
   * 
   * @param index The index of which <code>Avatar</code> will be used. 
   *              It is set by the user.
   * @return Image The <code>Image</code> that the user wants to be their 
   *               <code>Avatar</code>.
   */
  public Image avatarChoices(int index)
  {
    ArrayList <Image> aChoices = new ArrayList <Image>();
    aChoices.add ((new ImageIcon (getClass ().getResource ("Images/Characters/character1_small.png"))).getImage ());
    aChoices.add ((new ImageIcon (getClass ().getResource ("Images/Characters/character2_small.png"))).getImage ());
    aChoices.add ((new ImageIcon (getClass ().getResource ("Images/Characters/character3_small.png"))).getImage ());
    aChoices.add ((new ImageIcon (getClass ().getResource ("Images/Characters/character4_small.png"))).getImage ());
    aChoices.add ((new ImageIcon (getClass ().getResource ("Images/Characters/character5_small.png"))).getImage ());
    aChoices.add ((new ImageIcon (getClass ().getResource ("Images/Characters/character6_small.png"))).getImage ());
    return aChoices.get(index);
  }
}
