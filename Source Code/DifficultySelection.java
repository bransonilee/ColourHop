import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * The DifficultySelection class is a menu that allows the user to choose the
 * difficulty level of their game.
 *
 * @author Peng Chen, Branson Lee
 * @version Version 1.0 June 1st, 2012
 */
public class DifficultySelection extends JPanel implements ActionListener
{
  /**
   * User's level selection <b>int</b> increment
   */
  private int addScore;
  /**
   * A <code>Window</code> to represent the user's level of difficulty.
   */
  private Window frame;
  /**
   * An <code>Image</code> that will represent the background of this panel.
   */  
  private Image background;
  /**
   * A <code>JLabel</code> that will ask for user selection.
   */  
  private JLabel instructions;
  /**
   * A <code>JButton</code> for the Easy level.
   */  
  private JButton easy;
  /**
   * A <code>JButton</code> for the Medium level.
   */  
  private JButton medium;
  /**
   * A <code>JButton</code> for the Hard level.
   */  
  private JButton hard;
  /**
   * This contructor creates a JPanel which will ask the user to select their
   * level of difficulty.
   * 
   * @param frame A <code>Window</code> that will display this 
   *              <code>JPanel</code>.
   */
  public DifficultySelection (Window frame)
  {
    this.frame = frame;
    setLayout (null);
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/selection.png"))).getImage();
    
    easy = new JButton ("Easy");
    easy.addActionListener (this);
    easy.setMnemonic (KeyEvent.VK_E);
    easy.setToolTipText ("Alt + e");
    easy.setSize (100, 25);
    easy.setLocation (125, 190);
    
    medium = new JButton ("Medium");
    medium.addActionListener (this);
    medium.setMnemonic (KeyEvent.VK_M);
    medium.setToolTipText ("Alt + m");
    medium.setSize (100, 25);
    medium.setLocation (125, 240);
    
    hard = new JButton ("Hard");
    hard.addActionListener (this);
    hard.setMnemonic (KeyEvent.VK_H);
    hard.setToolTipText ("Alt + h");
    hard.setSize (100, 25);
    hard.setLocation (125,290);
    
    instructions = new JLabel ("Choose your level of difficulty:");
    instructions.setFont (new Font ("Serif", Font.BOLD, 25));
    instructions.setForeground(Color.BLACK);
    instructions.setLocation (50,0);
    instructions.setSize (500, 200); 
  }
  
  /**
   * Draws the <code>JLabels</code> and the <code>JButtons</code> that are 
   * created along this class.
   * <p>
   * This method is inherited from <code>JPanel</code>.
   * 
   * @param g Inherited parameter of <code>paintCoponent</code> which will 
   *          point to <code>Graphics</code> and draw the background and the 
   *          necessities that come with this screen.
   */
  public void paintComponent (Graphics g)
  {
    g.drawImage (background, 0, 0, null);
    add (instructions);
    add (easy);
    add (medium);
    add (hard);
  }
  /**
   * Method that must be implemented. It will be involked automatically
   * once the user clicks on a button.
   * <p>
   * Depending on which level the user selected, the score increment will be 
   * different. Either way, any choice will lead to the <code>Game</code> class. 
   * 
   * @param ae The <code>ActionEvent</code> that has all the information 
   *           regarding on what the user clicked.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals ("Easy"))
      addScore = 10;
    else if (ae.getActionCommand ().equals ("Medium"))
      addScore = 15;
    else //(ae.getActionCommand ().equals ("Hard"))
      addScore = 20;
    frame.changeScreen (this, new Game (frame, addScore));
  }
}
