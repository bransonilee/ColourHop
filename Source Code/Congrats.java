import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A class to show the user that they've achieved a score in the top ten
 * 
 * @author Peng Chen, Branson Lee
 * @version Version 1.0 June 10th, 2012
 */
public class Congrats extends JPanel implements ActionListener
{
  /**
   * A <code>JTextField</code> for the user to enter their name.
   */
  private JTextField n;
  /**
   * An <b>int</b> to represent the score that the user achieved.
   */
  private int score;
  /**
   * An <b>int</b> to represent the user's place in the top ten high scores.
   */
  private int index;
  /**
   * A <code>String</code> to represent the user's level of difficulty.
   */
  private String difficulty;
  /**
   * A <code>Window</code> to represent the user's level of difficulty.
   */
  private Window frame;
  /**
   * A <code>ScoreList</code> instance that creates a new <code>ScoreList</code> 
   * for storing the user's high score.
   */
  private ScoreList scoreList = new ScoreList ();
  /**
   * An <code>Image</code> that will represent the background of this panel.
   */
  private Image background;
    
  /**
   * A <code>JLabel</code> to say "Congratulations".
   */
  private JLabel c;
  /**
   * A <code>JLabel</code> to say "You got a high score!".
   */
  private JLabel m;
  /**
   * A <code>JLabel</code> to say "Enter your name: ".
   */
  private JLabel a;
  /**
   * A <code>JButton</code> to let the user submit their score.
   */
  private JButton s;
  /**
   * A <code>JButton</code> to let the user exit without submitting score.
   */
  private JButton e;
  
  /**
   * Creates a new <code>Congrats</code> to let the user know that they've 
   * achieved a score in the top ten high scores ever achieved. This contructor 
   * will draw out the title, purpose, the space for user to enter their name, 
   * and the buttons to either sumbit or exit without saving.
   * 
   * @param frame A <code>Window</code> frame used to display this 
   *              <code>Jpanel</code> on the current frame.
   * @param difficulty A <code>String</code> that stores what level the user
   *                   was on.
   * @param score An <b>int</b> that represents the user's final score
   * @param index An <b>int</b> that represents the place of the user in the 
   *              top ten high scores.
   */
  public Congrats (Window frame, String difficulty, int score, int index)
  {
    setLayout (null);
    this.score = score;
    this.index = index;
    this.difficulty = difficulty;
    this.frame = frame;
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/congrats.png"))).getImage();
    
    c = new JLabel("CONGRATULATIONS!");
    c.setFont (new Font ("Serif", Font.BOLD, 40));
    c.setForeground(Color.RED);
    c.setLocation (25,0);
    c.setSize (500, 200);    
    
    
    m = new JLabel("You got a high score!");
    m.setFont (new Font ("Serif", Font.BOLD, 30));
    m.setForeground(Color.BLUE);
    m.setLocation (115,50);
    m.setSize (500, 200);    
    
    
    a = new JLabel("Please enter a name: ");
    a.setFont (new Font ("Serif", Font.BOLD, 18));
    a.setForeground(Color.GRAY);
    a.setLocation (80,150);
    a.setSize (500, 200);    
    
    
    n = new JTextField ();
    n.setLocation (255, 243);  
    n.setSize (140, 20);
    
    
    s = new JButton ("Submit");
    s.setLocation (180,300);
    s.setSize (100,20);
    s.addActionListener (this);
    
    e = new JButton ("Don't want to save score");
    e.setLocation (20,465);
    e.setSize (200,20);
    e.addActionListener (this);
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
    add(m);
    add(a);
    add(n);
    add(s);
    add(e);
  }
  
  /**
   * This method listens for actions thrown by buttons in the panel.
   * <p>
   * This method will store the score in the top ten list if the user enters a 
   * name in the <code>JTextField</code> and that name is not blank. The user 
   * may also choose to exit and go back to the main menu without saving their 
   * score by pressing "Don't want to save score".
   * 
   * @param ae This is the action event thrown whenever a button is pressed.
   */
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getSource () == s)
    {
      if (n.getText().equals (""))
        JOptionPane.showMessageDialog (new JFrame (), "You must enter a name!");
      else
      {
        ScoreList.getScores ().add (index, new Score (n.getText (), difficulty, Integer.toString (score)));
        ScoreList.getScores ().remove (10);
        scoreList.save ();
      }
    }
    frame.changeScreen (this, new MainMenu (frame));
  }
}