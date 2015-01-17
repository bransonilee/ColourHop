import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The Settings class controls the contents of the JPanel used for the settings screen.
 * Currently, this only includes the background and a button to return to the main menu.
 * 
 * <p>
 * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
 * background | Image | This Image object stores the picture used for the background of the screen.
 * back | JButton | This JButton is used to return to the main menu.
 * frame | Window | This Window object is given through a parameter in the constructor and is used to access methods in Window.
 * 
 * @author Branson Lee, Peng Chen
 * @version Version 2.0 May 25, 2012
 */
public class Settings extends JPanel implements ActionListener
{
  /**
   * <b>int</b> of the index that the user chose to be their <code>Avatar</code>
   */
  public static int index = 0;
  /**
   * It is used to access methods in <code>Window</code>.
   */
  private Window frame;
  /**
   *  <code>Image</code> that stores the background picture.
   */
  private Image background;
  /**
   * <code>JButton</code> used to return to the main menu.
   */
  private JButton back;
  /**
   *  <code>JButton Array</code> that stores the 6 button options.
   */
  private JButton[] character = new JButton[7];
  
  /**
   * This constructor will set the reference variable frame to refer to the 
   * <code>Window</code> object given in the parameters.
   * The layout will then set to be null and the background image is imported 
   * and assigned to the variable background.
   * The back button is also created here with set location and size.
   * Other properties of the button is also modified in the constructor.
   * 
   * @param frame <code>Window</code> object that accesses 
   *              <code>Window</code> methods later.
   */
  public Settings (Window frame)
  {
    this.frame = frame;
    
    setLayout (null);
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_settings.png"))).getImage ();
    
    addButtons ();
  }
  
  /**
   * This method will create the buttons needed for the main menu.
   * The pictures used in the buttons will also be imported here as 
   * <code>ImageIcon</code> objects.
   * The size, location and other properties of each button are also set here.
   * <p>
   * The for loop goes through each button in the button array.
   */
  public void addButtons ()
  {
    back = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_back_normal.png")));
    back.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_back_over.png")));
    back.setSize (168, 44);
    back.setLocation (10, 434);
    back.setOpaque(false);
    back.setContentAreaFilled(false);
    back.setBorderPainted(false);
    back.addActionListener (this);
    add (back);
    
    for (int a = 1 ; a < 7 ; a++)
    {
      character[a - 1] = new JButton (new ImageIcon (getClass ().getResource ("Images/Characters/character" + a + "_small.png")));
      character[a - 1].setSize (50, 50);
      character[a - 1].setLocation (-45 + (77 * a), 300);
      character[a - 1].setOpaque(false);
      character[a - 1].setContentAreaFilled(false);
      character[a - 1].setBorderPainted(false);
      character[a - 1].addActionListener (this);
      add (character[a - 1]);
    }
    character[6] = new JButton (new ImageIcon (getClass ().getResource ("Images/Characters/character1_small.png")));
    character[6].setSize (50, 50);
    character[6].setLocation (330, 365);
    character[6].setOpaque(false);
    character[6].setContentAreaFilled(false);
    character[6].setBorderPainted(false);
    add (character[6]);
  }
  
  /**
   * This method listens for actions thrown by buttons in the panel.
   * <p>
   * This method will set an <code>Avatar</code> based on the user's button 
   * click.
   * 
   * @param ae This is the action event thrown whenever a button is pressed.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getSource () == back)
    {
      frame.changeScreen (this, new MainMenu (frame));
    }
    else
    {
      if (ae.getSource () == character[0])
      {
        character[6].setIcon (character[0].getIcon ());
        index = 0;
      }
      else if (ae.getSource () == character[1])
      {
        character[6].setIcon (character[1].getIcon ());
        index = 1;
      }
      else if (ae.getSource () == character[2])
      {
        character[6].setIcon (character[2].getIcon ());
        index = 2;
      }
      else if (ae.getSource () == character[3])
      {
        character[6].setIcon (character[3].getIcon ());
        index = 3;
      }
      else if (ae.getSource () == character[4])
      {
        character[6].setIcon (character[4].getIcon ());
        index = 4;
      }
      else
      {
        character[6].setIcon (character[5].getIcon ());
        index = 5;
      }
    }
  }
  
  /**
   * This method is overrided from the <code>JPanel</code> class.
   * It is overrided to draw the <code>Image</code> background to the panel 
   * as well as set the opacity of all components to half so that 
   * transparencies are possible.
   * 
   * @param g The <code>Graphics</code> instance used to draw everything.
   */
  public void paintComponent(Graphics g)
  { 
    g.drawImage (background, 0, 0, null);
    Graphics2D g2 = (Graphics2D) g.create(); 
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); 
    super.paintComponent(g2); 
    g2.dispose(); 
  } 
}