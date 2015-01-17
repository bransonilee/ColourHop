import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class PauseScreen extends JLayeredPane implements ActionListener
{
  /**
   * int to represent a choice in pausing the game.
   */
  private int pause = 1;
    /**
   * JButton to represent the quit button.
   */
  private JButton s;
      /**
   * JButton to represent the resume button.
   */
  private JButton r;
  
  /**
   * Creates a PauseScreen with labels and buttons for user selection.
   */
  public PauseScreen ()
  {
    setLayout (null);
    setSize (350,500);
    
    JLabel c = new JLabel("PAUSED");
    c.setFont (new Font ("Serif", Font.BOLD, 20));
    c.setForeground(Color.RED);
    c.setLocation (130,50);
    c.setSize (500, 200);    
    add(c);
    
    s = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_quit_normal.png")));
    s.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_quit_over.png")));
    s.setLocation (121, 350);
    s.setSize (108, 38);
    s.setContentAreaFilled(false);
    s.setBorderPainted(false);
    s.addActionListener (this);
    s.setMnemonic (KeyEvent.VK_Q);
    s.setToolTipText ("Alt + q");
    add(s);
    
    r = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_resume_normal.png")));
    r.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_resume_over.png")));
    r.setLocation (90,300);
    r.setSize (169, 37);
    r.setContentAreaFilled(false);
    r.setBorderPainted(false);
    r.addActionListener (this);
    r.setMnemonic (KeyEvent.VK_R);
    r.setToolTipText ("Alt + r");
    add(r);
  }
  
  /**
   * Draws this PauseScreen over the game.
   */
  public void paintComponent(Graphics g)
  {
    g.fillRect (0, 0, 350, 500);
    Graphics2D g2 = (Graphics2D) g.create(); 
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); 
    super.paintComponent(g2); 
    g2.dispose(); 
  } 
  
  /**
   * This method listens for actions thrown by buttons in the panel.
   * <p>
   * This method will either quit the game or resume the game depending on what 
   * the user clicks
   * 
   * @param ae This is the action event thrown whenever a button is pressed.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getSource () == s)
    {
      setPause (2);
    }
    else
    {
      setPause (1);
    }
  }
  /**
   * Gets the pause value
   * 
   * @return int pause value
   */
  public int getPause()
  {
    return pause;
  }
    /**
   * Sets the pause value
   * 
   * @param p new pause value
   */
  public void setPause (int p)
  {
    pause = p;
  }
}