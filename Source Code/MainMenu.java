import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The <code>MainMenu</code> class is and controls a <code>JPanel</code> that 
 * contains the contents of the main menu screen.
 * This includes drawing the background and adding any components such 
 * as buttons that may be needed.
 * These buttons include the Play button to start the game, a button to 
 * access the instructions, a button to access the settings menu and a button 
 * to access the highscores.
 * This class also controls the functions of the buttons using the 
 * <code>ActionListener</code> class.
 * 
 * @author Branson Lee, Peng Chen
 * @version Version 1.0 May 18, 2012
 */

public class MainMenu extends JPanel implements ActionListener
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
   *  <code>JButton Array</code> that stores the 4 button options.
   */
  private JButton[] button = new JButton[6];
  
  /**
   * This constructor will assign the reference variable frame to the instance of Window given in the parameter.
   * The layout will then set to be null and the background image is imported and assigned to the variable background.
   * The buttons () method is then called to create and add all the buttons used on the screen.
   * 
   * @param frame This Window object is used to access method in the Window class later on.
   */
  public MainMenu (Window frame)
  {
    this.frame = frame;
    
    setLayout (null);
        
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_mainMenu.png"))).getImage ();
    
    buttons ();
  }
  
  /**
   * This method will create the buttons needed for the main menu.
   * The pictures used in the buttons will also be imported here as 
   * <code>ImageIcon</code> objects.
   * The size, location and other properties of each button are also set here.
   */
  private void buttons ()
  {
    button[0] = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_play_normal.png")));
    button[0].setLocation (10, 368);
    button[0].setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_play_over.png")));
    button[0].setMnemonic (KeyEvent.VK_P);
    button[0].setToolTipText ("Alt + p");
    button[1] = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_instructions_normal.png")));
    button[1].setLocation (237, 369);
    button[1].setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_instructions_over.png")));
    button[1].setMnemonic (KeyEvent.VK_I);
    button[1].setToolTipText ("Alt + i");
    button[2] = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_settings_normal.png")));
    button[2].setLocation (10, 434);
    button[2].setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_settings_over.png")));
    button[2].setMnemonic (KeyEvent.VK_S);
    button[2].setToolTipText ("Alt + s");
    button[3] = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_highscores_normal.png")));
    button[3].setLocation (232, 425);
    button[3].setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_highscores_over.png")));
    button[3].setMnemonic (KeyEvent.VK_H);
    button[3].setToolTipText ("Alt + h");
    button[4] = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_exit_normal.png")));
    button[4].setLocation (393, 10);
    button[4].setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_exit_over.png")));
    button[4].setMnemonic (KeyEvent.VK_E);
    button[4].setToolTipText ("Alt + e");
    button[5] = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_helpFile_normal.png")));
    button[5].setLocation (315, 50);
    button[5].setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_helpFile_over.png")));
    button[5].setMnemonic (KeyEvent.VK_F);
    button[5].setToolTipText ("Alt + f");
    
    for (JButton buttons : button)
    {
      add (buttons);
      //buttons.setOpaque(false);
      buttons.setSize ((int)(buttons.getMinimumSize ().getWidth ()), (int)(buttons.getMinimumSize ().getHeight ())); 
      buttons.setContentAreaFilled(false);
      buttons.setBorderPainted(false);
      buttons.addActionListener (this);
    }
  }
  
  /**
   * This method listens for actions thrown by buttons in the panel.
   * It will change the screen using methods in the <code>Window</code> class.
   * The change will differ based on the source of the <code>ActionEvent</code>.
   * 
   * @param ae An <code>ActionEvent</code> that has all the information 
   *           of a mouse click.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getSource () == button[0])
    {
      frame.changeScreen (this, new DifficultySelection (frame));
    }
    else if (ae.getSource () == button[1])
    {
      frame.changeScreen (this, new Instructions (frame));
    }
    else if (ae.getSource () == button[2])
    {
      frame.changeScreen (this, new Settings (frame));
    }
    else if (ae.getSource () == button[3])
    {
      frame.changeScreen (this, new Highscores (frame));
    }
    else if (ae.getSource () == button[4])
    {
      System.exit (0);
    }
    else
    {
      try
      {
        Desktop.getDesktop ().open (new File ("Resources/Help.chm"));
      }
      catch (IOException ioe)
      {
      }
    }
  }
  
  /**
   * This method is overrided from the <code>JPanel</code> class.
   * It is overrided to draw the <code>Image</code> background to the 
   * panel as well as set the opacity of all components to half so that 
   * transparencies are possible.
   * 
   * @param g The <code>Graphics</code> object used to access 
   *          <code>Graphics</code> methods.
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