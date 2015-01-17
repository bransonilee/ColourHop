import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;
/**
 * The Thing the user must jump on with a colour specified in the Game JPanel.
 * 
 * @author Peng Chen, Branson Lee
 * @version 1.0 May 31, 2012
 */
public class Monster extends Thing
{  
  /**
   * An <code>ArrayList</code> of <code>Images</code> that will store 
   * the possible <code>Monsters</code>.
   * The game will randomly choose an <code>Monster</code> to display.
   */
  private ArrayList <Image> mChoices = new ArrayList <Image>();
  
  /**
   * An <code>Image</code> to represent the <code>Monster</code>.
   */
  public Image monsterPic;
  /**
   * Creates a Monster at the specified Coordinate and with the specified size.
   * 
   * @param x the specified <b>int</b> x point in the game
   * @param y the specified <b>int</b> y point in the game
   * @param w the specified <b>int</b> width of the <code>Item</code>
   * @param h the specified <b>int</b> height of the <code>Item</code>
   * @param index  the <b>int</b> index of the specified <code>Item</code> type
   */
  public Monster(int x, int y, int w, int h, int index)
  {
    super (x,y,w,h);
    monsterPic=monsterChoices(index);
  }

  /**
   * Adds all <code>Monster</code> images into an <code>ArrayList</code> 
   * so the Game may choose and set an index.
   * 
   * @param index The index of which <code>Monster</code> will be displayed
   * @return Image The <code>Image</code> of the specified <code>Monster</code>
   */
  private Image monsterChoices(int index)
  {
    mChoices.add ((new ImageIcon (getClass ().getResource ("Images/Monsters/monster_monster1.png"))).getImage ());
    mChoices.add ((new ImageIcon (getClass ().getResource ("Images/Monsters/monster_monster2.png"))).getImage ());
    mChoices.add ((new ImageIcon (getClass ().getResource ("Images/Monsters/monster_monster3.png"))).getImage ());
    mChoices.add ((new ImageIcon (getClass ().getResource ("Images/Monsters/monster_monster4.png"))).getImage ());
    return mChoices.get(index);
  }
}