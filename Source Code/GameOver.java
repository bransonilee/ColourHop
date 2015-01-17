import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameOver extends JPanel implements ActionListener
{
  /**
   * A <code>Window</code> to represent the user's fail.
   */
  private Window frame;
  /**
   * The <b>int</b> that will represent the user's final score.
   */
  private int score;
  /**
   * A <code>String</code> to represent the user's level of difficulty.
   */
  private String difficulty;
  
  /**
   * An <code>Image</code> that will represent the background of this panel.
   */
  private Image background;
  /**
   * A <code>JLabel</code> to say "Game Over!".
   */
  private JLabel c;
  /**
   * A <code>JLabel</code> to say why they died.
   */
  private JLabel why;
  /**
   * A <code>JLabel</code> to say tell the user their final score.
   */
  private JLabel scoreLabel;
  /**
   * A <code>JButton</code> to let the user proceed to either the 
   * <code>JPanel</code> that asks them to enter their name or the main menu.
   */
  private JButton next;
  
  /**
   * Creates a new <code>GameOver</code> to let the user know that they 
   * died in the game. This contructor will draw out the title, why, and 
   * their final score with a next button to proceed in proper order.
   * 
   * @param frame A <code>Window</code> frame used to display this 
   *              <code>Jpanel</code> on the current frame.
   * @param score An <b>int</b> that represents the user's final score
   * @param difficulty A <code>String</code> that stores what level the user
   *                   was on.
   * @param deathMessage A <code>String</code> that tells the user why they failed.
   */
  public GameOver (Window frame, int score, String difficulty, String deathMessage)
  {
    this.frame = frame;
    this.score = score;
    this.difficulty = difficulty;
    setLayout (null);
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/gameOver.png"))).getImage();
    
    c = new JLabel("Game Over!");
    c.setFont (new Font ("Serif", Font.BOLD, 55));
    c.setForeground(Color.RED);
    c.setLocation (90,50);
    c.setSize (500, 200);    
    
    why = new JLabel(deathMessage);
    why.setFont (new Font ("Serif", Font.BOLD, 20));
    why.setForeground(Color.GRAY);
    why.setLocation (50,125);
    why.setSize (500, 200);    
    
    Color teal = new Color (0, 128, 128);
    scoreLabel = new JLabel("Your score is: " + score);
    scoreLabel.setFont (new Font ("Serif", Font.BOLD, 35));
    scoreLabel.setForeground(teal);
    scoreLabel.setLocation (100,225);
    scoreLabel.setSize (500, 200);    
    
    
    next = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_next_normal.png")));
    next.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_next_over.png")));
    next.setContentAreaFilled (false);
    next.setBorderPainted (false);
    next.setLocation (350, 450);
    next.setSize (149,42);
    next.addActionListener (this);
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
    add(c);
    add(why);
    add(scoreLabel);
    add(next);
  }

  /**
   * Method that must be implemented. It will be involked automatically
   * once the user clicks on a button.
   * <p>
   * Depending on the user's score, if it is in the top ten, they will proceed 
   * to the <code>Congrats</code>. If not, they will just return to the main menu.
   * 
   * @param ae The <code>ActionEvent</code> that has all the information 
   *           regarding on what the user clicked.
   */
  public void actionPerformed(ActionEvent ae)
  {
    if (legendary (score) >= 0)
    {
      frame.changeScreen (this, new Congrats (frame, difficulty, score, legendary (score)));
    }
    else
    {
      frame.changeScreen (this, new MainMenu (frame));
    }
  }
  
  /**
   * Method that returns the proper place in the top 10 if the user achieves it.
   * 
   * @param score The <b>int</b> that represents the user's final score.
   * @return int Either the place in the top 10 or -1 if the user's score did 
   *             not make top 10.
   */
  private int legendary (int score)
  {
    for (Score top : ScoreList.getScores ())
    {
      if (score > Integer.parseInt (top.getScore ()))
      {
        return ScoreList.getScores ().indexOf (top);
      }
    }
    return -1;
  }
}