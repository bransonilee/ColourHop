import javax.swing.*;

/**
 * The Window class is and controls the JFrame used to contain the contents of the game.
 * It also contains a method to change the contents of the screen based on the parameters.
 * 
 * @author Branson Lee, Peng Chen
 * @version Version 1.0 May 18, 2012
 */
public class Window 
{ 
  /**
   * <code>JFrame</code> that contains all the contents of the screen.
   */
  private JFrame frame;
    /**
   * A new <code>Intro</code> to display the splash screen.
   */
  private Intro i = new Intro (this);
  
  /**
   * This contructor creates the <code>JFrame</code> with the title "Colour Hop", 
   * which is the name of our game.
   * It then sets the <code>JPanel</code> used to the one of the 
   * <code>MainMenu</code> class as the game starts from the main menu.
   * The <code>JFrame</code> size as well as other properties such as whether 
   * or not is is undecorated and resizable and the default close operation 
   * are all set here as well.
   * The <code>JFrame</code> is then set to visible.
   */
  public Window ()
  {
    new ScoreList ();
    frame = new JFrame ("Colour Hop");
    frame.setSize (500, 500);
    frame.setUndecorated (true);
    frame.setResizable (false);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.setVisible (true);
    frame.setLocationRelativeTo (null);
    frame.getContentPane ().add (i);
    frame.validate ();
  }
  
  /**
   * Method to display the splash screen.
   * 
   * @exception Exception incase the <code>Thread</code> fails
   */
  public void waitForIt ()
  {
    try 
    {
      Thread.sleep (3000);
    }
    catch (Exception e)
    {
    }
    frame.getContentPane().remove (i);
    frame.getContentPane().add (new MainMenu(this));
    frame.validate();
  }
  /**
   * This method changes disables and hides the <code>JPanel</code> given as 
   * the first parameter and makes the second <code>JPanel</code> passed 
   * visible and enabled.
   * The new one is then added to the <code>JFrame</code>.
   * This method is used when the screen needs to be changed. 
   * It is called in the other classes such as <code>MainMenu</code> 
   * and <code>Settings</code> to remove itself and make another class visible.
   * 
   * @param old This is the old <code>JPanel</code> to be hidden and disabled.
   * @param panel The new <code>JPanel</code> that will be shown and enabled.
   */
  public void changeScreen (JPanel old, JPanel panel)
  {
    old.setVisible (false);
    old.setEnabled (false);
    old = null;
    frame.add (panel);
    panel.setVisible (true);
    panel.setEnabled (true);
    panel.requestFocusInWindow ();
  }
  
  /**
   * Returns the current <code>JFrame</code>.
   * 
   * @return JFrame The current frame.
   */
  public JFrame getFrame ()
  {
    return frame;
  }
  
  
  /**
   * The main method creates an instance of <code>Window</code>.
   *
   * @param args An <code>Array</code> of arguments given to the <code>main</code>.
   */
  public static void main (String[] args)
  {
    Window w = new Window ();
    w.waitForIt();
  }
}