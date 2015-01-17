import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Settings class controls the contents of the JPanel used for the settings screen.
 * Currently, this only includes the background and a button to return to the main menu.
 *
 * @author Branson Lee, Peng Chen
 * @version Version 2.0 May 25, 2012
 */
public class Instructions extends JPanel implements ActionListener
{
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
   * <code>JButton</code> used to go the next screen.
   */
  private JButton next;
    /**
   * <b>int</b> used count which screen it is on.
   */
  private int screen = 1;
  
  /**
   * This constructor will set the reference variable frame to refer to the 
   * <code>Window</code> object given in the parameters.
   * The layout will then set to be null and the background image is imported 
   * and assigned to the variable background.
   * The back button is also created and its size and location set here as well.
   * Other properties of the button is also modified in the constructor.
   * 
   * @param frame This <code>Window</code> object is used to access 
   *              methods in the <code>Window</code> class.
   */
  public Instructions (Window frame)
  {
    this.frame = frame;
    
    setLayout (null);
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_instructions_1.png"))).getImage ();
    
    back = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_back_normal.png")));
    back.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_back_over.png")));
    back.setSize (168, 44);
    back.setLocation (10, 434);
    back.setOpaque(false);
    back.setContentAreaFilled(false);
    back.setBorderPainted(false);
    back.addActionListener (this);
    add (back);
    
    next = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_next_normal.png")));
    next.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_next_over.png")));
    next.setSize (168, 44);
    next.setLocation (322, 434);
    next.setOpaque(false);
    next.setContentAreaFilled(false);
    next.setBorderPainted(false);
    next.addActionListener (this);
    add (next);
  }
  
  /**
   * This method listens for actions thrown by buttons in the panel.
   * This method will change the screen's contents using the changeScreen 
   * method from the <code>Window</code> class.
   * The change made will differ based on the source 
   * of the <code>ActionEvent</code>.
   * 
   * @param ae This is the action event thrown whenever a button is used.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getSource () == back)
    {
      if (screen == 1)
      {
        frame.changeScreen (this, new MainMenu (frame));
      }
      else
      {
        screen--;
        background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_instructions_" + (screen) + ".png"))).getImage ();
        frame.getFrame ().repaint ();
      }
    }
    else if (ae.getSource () == next)
    {
      if (screen == 7)
      {
        frame.changeScreen (this, new MainMenu (frame));
      }
      else
      {
      screen++;
      background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_instructions_" + (screen) + ".png"))).getImage ();
      frame.getFrame ().repaint ();
      }
    }
  }
  
  /**
   * This method is overrided from the <code>JPanel</code> class.
   * It is overrided to draw the <code>Image</code> background to the panel 
   * as well as set the opacity of all components to half so that 
   * transparencies are possible.
   * 
   * @param g The <code>Graphics</code> object used to access methods 
   *          in the <code>Graphics</code> class.
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