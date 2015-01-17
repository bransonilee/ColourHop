import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The Highscores class controls the contents of the JPanel used for the highscores screen.
 * Currently, this only includes the background and a button to return to the main menu.
 * 
 * @author Branson Lee, Peng Chen
 * @version Version 2.0 May 25, 2012
 */
public class Highscores extends JPanel implements ActionListener, Printable
{
  /**
   * A <code>Window</code> to represent the user's level of difficulty.
   */
  private Window frame;
  /**
   * An <code>Image</code> that will represent the background of this panel.
   */
  private Image background;
  /**
   * A <code>JButton</code> used to return to the main menu.
   */
  private JButton back;
    /**
   * A <code>JButton</code> used to print the top 10 scores.
   */
  private JButton print;
    /**
   * A <code>JButton</code> used to clear the top 10 scores.
   */
  private JButton clear;
  /**
   * A <code>ScoreList</code> reference to access methods and variables 
   * in that class.
   */
  private ScoreList scoreList;
  
  /**
   * This constructor will set the reference variable frame to refer to the 
   * <code>Window</code> object given in the parameters. The layout will then 
   * set to be <code>null</code> and the background image is imported and 
   * assigned to the variable background. The back button is also created and 
   * its size and location are set here as well. Other properties of the button 
   * is also modified in the constructor.
   * 
   * @param frame This <code>Window</code> object is used later on to 
   *              access methods in the <code>Window</code> class.
   */
  public Highscores (Window frame)
  {
    this.frame = frame;
    scoreList = new ScoreList ();
    
    setLayout (null);
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_highscores.png"))).getImage ();
    
    print = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_print_normal.png")));
    print.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_print_over.png")));
    print.setSize (118, 38);
    print.setLocation (10, 10);
    print.setOpaque(false);
    print.setContentAreaFilled(false);
    print.setBorderPainted(false);
    print.addActionListener (this);
    add (print);
    
    clear = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_clear_normal.png")));
    clear.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_clear_over.png")));
    clear.setSize (120, 38);
    clear.setLocation (138, 10);
    clear.setOpaque(false);
    clear.setContentAreaFilled(false);
    clear.setBorderPainted(false);
    clear.addActionListener (this);
    add (clear);
    
    back = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_back_normal.png")));
    back.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_back_over.png")));
    back.setSize (168, 44);
    back.setLocation (10, 434);
    back.setOpaque(false);
    back.setContentAreaFilled(false);
    back.setBorderPainted(false);
    back.addActionListener (this);
    add (back);
    displayScores ();
  }
  
  /**
   * This method creates labels to store all the information from the 
   * top ten scores. It then displays this information on the highscores screen.
   * <p>
   * The for loops go through each row and column to write the correct score 
   * listings at the correct positions.
   * <p>
   * The if statements make sure that any score equal to 0 does not count.
   * <p>
   * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
   * label | JLabel 2DArray | Has all the top 10 names, levels, and scores.<br>
   * a | int | index for where the row & column in the 2DArray.
   */
  public void displayScores ()
  {
    JLabel[][] label = new JLabel[3][10];
    int a = 0;
    for (Score score : scoreList.getScores ())
    {
      label[0][a] = new JLabel (score.getName ());
      label[1][a] = new JLabel (score.getDifficulty ());
      if (score.getScore ().equals ("0"))
      {
        label[2][a] = new JLabel ("");
      }
      else
      {
        label[2][a] = new JLabel (score.getScore ());
      }
      a++;
    }
    for (int b = 0 ; b < 10 ; b++)
    {
      for (int c = 0 ; c < 3 ; c++)
      {
        add (label[c][b]);
        label[c][b].setSize ((int)(label[c][b].getMinimumSize ().getWidth ()), 16);
        label[c][b].setLocation (135 + (110 * c), 130 + (25 * b));
      }
    }
  }
  
  /**
   * prints the high scores and throws PrinterException.
   * For loop keeps the scores in nice columns
   * 
   * @param g Graphics to draw the Strings
   * @param pf PageFormate instance to format the paper
   * @param page int to make sure that there is only 1 page being printed.
   */
  public int print(Graphics g, PageFormat pf, int page) throws PrinterException 
  {
    if (page > 0)
    {
      return NO_SUCH_PAGE;
    }
    
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());
    
    g.drawString ("Name", 170, 150);
    g.drawString ("Level", 280, 150);
    g.drawString ("Score", 390, 150);
    for (int a = 0 ; a < 10 ; a++)
    {
      g.drawString(scoreList.getScores ().get (a).getName (), 170, 200 + (25 * a));
      g.drawString(scoreList.getScores ().get (a).getDifficulty (), 280, 200 + (25 * a));
      g.drawString(scoreList.getScores ().get (a).getScore (), 390, 200 + (25 * a));
    }
    
    return PAGE_EXISTS;
  }
  
  /**
   * The actionPerformed method listens for actions from buttons in the panel.
   * It will change the screen's contents using a method from <code>Window</code>.
   * The change made will differ based on the source of the ActionEvent.
   * 
   * @param ae This is the action event thrown whenever a button is used.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getSource () == back)
    {
      frame.changeScreen (this, new MainMenu (frame));
    }
    else if (ae.getSource () == print)
    {
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(this);
      boolean didPrint = job.printDialog();
      if (didPrint)
      {
        try 
        {
          job.print();
        } 
        catch (PrinterException ex) 
        {
        }
      }
    }
    else
    {
      clear ();
    }
  }
  /**
   * clears ALL scores
   */
  public void clear ()
  {
    for (Score score : scoreList.getScores ())
    {
      score.clear ();
      System.out.println ("Name: " + score.getName ());
    }
    scoreList.save ();
    scoreList.getScores ().clear ();
    scoreList.importScores ();
    frame.changeScreen (this, new MainMenu (frame));
    repaint ();
  }
  
  /**
   * This method is overrided from the JPanel class.
   * It is overrided to draw the <code>Image</code> background to the panel as 
   * well as set the opacity of all components to half so that transparencies 
   * are possible.
   * 
   * @param g The Graphics object used to access method in the Graphics class.
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