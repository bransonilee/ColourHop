import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
/**
 * This class will produce a splash screen containing the game name 
 * and the production company.
 * 
 * @author Peng Chen, Branson Lee
 * @version 2
 */
public class Intro extends JPanel implements Runnable
{
  /* 
   * An <code>Image</code> that will be used to represent 
   * the background of the splash screen.
   */
  private Image background;
  /* 
   * Creates the <code>Color</code> purple
   */
  private Color purple = new Color (128, 0, 128);
  /* 
   * presets the x coordinate position of where the <code>Avatar</code> is.
   */
  private int x = 50;
  /* 
   * presets the y coordinate position of where the <code>Avatar</code> is.
   */
  private int y = 330;
  
  /**
   * This constructor will create all the text that will be displayed.
   * <p>
   * VARIABLE DICTIONARY:<br>
   * writing - A <code>JLabel</code> used to write the production company<br>
   * g - A <code>JLabel</code> used to write the game name
   */
  public Intro (Window frame)
  {
    setLayout (null);        
    JLabel g = new JLabel ("COLOUR HOP");
    g.setFont (new Font ("Serif", Font.BOLD, 40));
    g.setForeground(Color.BLUE);
    add (g);
    g.setLocation (100,-50);
    g.setSize (500, 200); 
    
    JLabel writing = new JLabel ("A Cr4k3r5 4nd Ch33z3 Production");
    writing.setFont (new Font ("Serif", Font.BOLD, 20));
    writing.setForeground(purple);
    add (writing);
    writing.setLocation (100,350);
    writing.setSize (500, 200);    
  }
  /**
   * This method will draw the background.It is inherited.
   * This method will then call the start method which will start
   * a thread to run the <code>Runnable</code>.
   * <p>
   * VARIABLE DICTIONARY:<br>
   * ava - Image - An image that represents the avatar
   * 
   * @param g   The Graphics reference pointer used to draw stuff
   */
  public void paintComponent(Graphics g) 
  {
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_intro.gif"))).getImage ();
    g.drawImage(background, 0, 0, null);
    g.setColor (Color.LIGHT_GRAY);
    g.fillRect (87,65,300,5);
    g.setColor (Color.BLACK);
    g.drawRoundRect (210,249,152,41,80,80);
    g.setColor (Color.PINK);
    g.fillRoundRect (211,250,150,40,80,80);
    Image ava = (new ImageIcon (getClass ().getResource ("Images/Characters/character1_modified.png"))).getImage ();
    g.drawImage(ava, x, y, null);
    start();
  }
  /**
   * Inherited method from Runnable to run the animation. The
   * repaint method is called to refresh the page so the animation
   * works as intended.
   * 
   * @exception InterruptedExcetion in case the delay fails.
   */
  public void run ()
  {        
    while (y>150)
    {
      x++;
      y--;
      repaint ();
      try
      {
        Thread.sleep (15000);
      }
      catch (InterruptedException ex)
      {  
      }      
    }
  }
    /**
   * Method that will create a new <code>Thread</code> so it can run 
   * the implemented <code>Runnable</code> method <code>run</code>.
   * <p>
   * VARIABLE DICTIONARY: <br>
   * th - Thread - points to Thread to make a new Thread class and calls
   *               start().
   */
  public void start ()
  {    
    Thread th = new Thread (this);    
    th.start ();
  }
}