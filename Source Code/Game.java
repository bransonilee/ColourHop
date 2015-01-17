//original
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
/**
 * The Game class is the JPanel where the user can interact and play the game.
 * <p>
 *
 * @author Peng Chen, Branson Lee
 * @version Version 1.0 June 10th, 20120
 */
public class Game extends JPanel implements KeyListener, Runnable
{
  /**
   * This Window object is used to access methods in the Window class.
   */
  public Window frame;
  
  /**
   * The Avatar pointer
   */
  private Avatar a;
  /**
   * The Platform pointer
   */
  private ArrayList < Platform > p = new ArrayList < Platform > ();
  /**
   * The Item pointer
   */
  private ArrayList < Item > i = new ArrayList < Item > ();
  /**
   *The Monster pointer
   */
  private ArrayList < Monster > m = new ArrayList < Monster > ();
  
  /**
   * The width and height of the Avatar
   */
  public final int AVATAR_DEMENSION = 50;
  /**
   * The height of the Platform
   */
  public final int PLATFORM_HEIGHT = 15;
  /**
   * The width of the Platform
   */
  public final int PLATFORM_WIDTH = 50;
  /**
   * The width and height of the Item
   */
  public final int ITEM_DEMENSION = 50;
  /**
   *The width and height of the Monsters
   */
  public final int MONSTER_DEMENSION = 50;
  
  /**
   * The background Image.
   */
  Image background;
  /**
   * The image for the sidebar of the game.
   */
  private Image sidebar;
  
  /**
   * The Random instance that will be used to determine the next colour  
   * for the Platforms, Monster, and even Items.
   */
  private Random random = new Random ();
  
  /**
   * The boolean variable used to see if the Avatar had mad its jump.
   */
  private boolean jumped = false;
  
  /**
   * The boolean variable used to see if the Avatar had collected an Item.
   */
  private boolean gotItem = false;
  
  /**
   * The boolean variable used to see if the user lands on the correct platform.
   */
  private boolean goodLanding = false; 
  
  /**
   * The Color instance that will represent the next colour the user must jump 
   * on.
   */
  private Color nextColour;
  
  /**
   * The boolean variable that will determind if there is a need to scroll down 
   * the screen for the user to jump.
   */
  private boolean scrolled = false;
  
  /**
   * A JButton that if the user clicks, will pause the game.
   */
  private JButton pauseButton;
  
  /**
   * The boolean variable that will determind if a new Monster should be added.
   */
  private boolean yesMonster = false;
  
  /**
   * The boolean variable that will determind if a new Item should be added.
   */
  private boolean yesItem = false;
  
  
  /**
   * The PauseScreen instance which will create a new PauseScreen ready to 
   * display at the user's choice.
   */
  private PauseScreen ps = new PauseScreen ();
  
  /**
   * The <b>int</b> that will represent the user's current score when they play.
   */
  private int score = 0;
  
  /**
   * The int of how much the score should increase (depending on their level 
   * selection from before).
   */
  private int scoreIncrement;
  
  /**
   * The JLabel to display the user's current score.
   */
  private JLabel scoreLabel = new JLabel ("0");
  /**
   * The JLabel to notify the user when they progress to a harder level.
   */
  private JLabel level = new JLabel ("");
  
  /**
   * The boolean variable that will determind if the score should increase.
   */
  private boolean add = true;
  
  /**
   * The String that will tell the user why the died.
   */
  private String deathMessage;
  
  
  /**
   * Allows the JPanel to listen for keyboard inputs and creates the proper
   * Things that are needed in the game with backgrounds and sidebars.
   * <p>
   * A button is in here with a ActionListener to pause the game anytime.
   * 
   * @param frame A Window used to display this game.
   * @param addScore The increment of the score based on the user's selection of
   *                 difficulty.
   */
  public Game (Window frame, int addScore)
  {
    this.frame = frame;
    setLayout (null);
    score = 0;
    scoreIncrement = addScore;
    
    background = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_game.png"))).getImage();
    sidebar = (new ImageIcon (getClass ().getResource ("Images/Screens/screen_gameSidebar.png"))).getImage ();
    pauseButton = new JButton (new ImageIcon (getClass ().getResource ("Images/Buttons/button_pause_normal.png")));
    pauseButton.setRolloverIcon (new ImageIcon (getClass ().getResource ("Images/Buttons/button_pause_over.png")));
    pauseButton.setContentAreaFilled (false);
    pauseButton.setBorderPainted (false);
    pauseButton.setLocation (350, 200);
    pauseButton.setSize (149, 42);
    pauseButton.setMnemonic (KeyEvent.VK_P);
    pauseButton.setToolTipText ("Alt + p");
    add (pauseButton);
    pauseButton.addActionListener (new ActionListener ()
                                     {
      public void actionPerformed (ActionEvent e)
      {
        ps.setPause (0);
        pauseButton.setEnabled (false);
      }
    }
    );
    
    frame.getFrame ().add (ps);
    ps.moveToBack(this);
    ps.setEnabled (false);
    ps.setVisible (false);
    scoreLabel.setSize ((int) (scoreLabel.getMinimumSize ().getWidth ()), 16);
    scoreLabel.setLocation (425 - (int) (scoreLabel.getMinimumSize ().getWidth () / 2), 425);
    add (scoreLabel);
    if (scoreIncrement == 10)
      level.setText ("EASY LEVEL");
    else if (scoreIncrement ==15)
      level.setText ("MEDIUM LEVEL");
    else
      level.setText ("HARD LEVEL");
    level.setSize ((int) (level.getMinimumSize ().getWidth ()), 16);
    level.setLocation (425 - (int) (level.getMinimumSize ().getWidth () / 2), 350);
    add (level);
    
    setFocusable (true);
    addKeyListener (this);
    a = new Avatar (150, 400, AVATAR_DEMENSION, AVATAR_DEMENSION, Settings.index);
    addPlatforms ();
    start ();
  }
  
  
  /**
   * Draws the Things that are created along with the game background.
   * This method is inherited from JPanel.
   * <p>
   * The if statement is so that if the user just went above the screen, the 
   * whole screen will be cleared first.
   *
   * @param g Inherited parameter of paintCoponent which will point to the Graphics
   *          class and draw Things.
   */
  public void paintComponent (Graphics g)
  {
    g.drawImage (background, 0, 0, null);
    g.drawImage (sidebar, 350, 0, null);
    
    if (a.getItem () != null)
      g.drawImage (a.getItem ().itemPic, 400, 275, null);
    if (scrolled)
    {
      removeMonster ();
      removeItems ();
      
      if (scoreIncrement == 20)
      {
        yesMonster = random.nextBoolean ();
        if (yesMonster)
          addMonster ();
        if (!gotItem)
        {
          yesItem = random.nextBoolean ();
          if (yesItem)
            addItems ();
        }
      }
      scrolled = false;
    }
    else
    {
      g.setColor (getNextColour ());
      g.fillOval (400, 100, 50, 50);
      
      for (Platform pf:p)
      {
        g.setColor (pf.getColour ());
        g.fillRoundRect (pf.getCoordinate ().getXPos (), pf.getCoordinate ().getYPos (), PLATFORM_WIDTH, PLATFORM_HEIGHT, 80, 80);
      }
      if (scoreIncrement == 20)
      {
        if (yesMonster)
        {
          for (Monster ms:m)
            g.drawImage (ms.monsterPic, ms.getCoordinate ().getXPos (), ms.getCoordinate ().getYPos (), null);
        }
        if (yesItem)
        {
          for (Item item:i)
            g.drawImage (item.itemPic, item.getCoordinate ().getXPos (), item.getCoordinate ().getYPos (), null);
        }
      }
    }
    g.drawImage (a.avatarPic, (int) a.getX (), (int) a.getY (), null);
  }
  
  /**
   * Removes all the Platforms on the screen.
   */
  public void removePlatforms ()
  {
    p.clear ();
    scrolled = true;
  }
  
  /**
   * Add 3 Platforms on the screen with randomized Coordinates and colours.
   * If the user's score increment is 10, the randomizing will only go through 
   * 6 colours. Anything above that, the randomizing will go through 12 colours.
   * The next colour to be landed on is created here randomly.
   * The while loops are to make sure no Platform overlap eachother and that 
   * they do not share the same colours.
   * <p>
   * All added Platforms go in to the ArrayList of Platforms
   * <p>
   * VARIABLE DICTIONARY:<br>
   * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
   * theColourList | ArrayList<Color> | all the colours available<br>
   * rightColourIndex | int | the index of the Platform that will contain 
   *                          the next Color<br>
   * numColours | int | the highest index the colours will go up to in 
   *                    theColourList. Used to differenciate the Easy level 
   *                    with the rest. <br>
   * rightColour | int | the random selected index of the next colour in 
   *                     theColourList. <br>
   * other1 | int | the first other Platform that is not the one the user must 
   *                land on. <br>
   * other2 | int | the second other Platform that is not the one the user must 
   *                land on. <br>
   * colourIndex1 | int | the index of the Color that will be assigned 
   *                      to other1. <br>
   * colourIndex2 | int | the index of the Color that will be assigned 
   *                      to other2. <br>
   * yCoord | int | y location of the lowest Platform added. <br>
   * xCoord1 | int | first random x location of the first Platform. <br>
   * xCoord2 | int | second random x location of the second Platform. <br>
   * xCoord3 | int | third random x location of the third Platform. <br>
   */
  public void addPlatforms ()
  {
    if (a.getY()<=100)
      return;
    ArrayList < Color > theColourList = getColoursList ();
    Color[] colourChoices = new Color [3];
    int rightColourIndex = (random.nextInt (2));
    int numColours = 0;
    if (scoreIncrement == 10)
      numColours = 5;
    else
      numColours = 11;
    int rightColour = (random.nextInt (numColours));
    colourChoices [rightColourIndex] = theColourList.get (rightColour);
    nextColour = colourChoices [rightColourIndex];
    int other1 = 0;
    int other2 = 0;
    int colourIndex1 = 0;
    int colourIndex2 = 0;
    while (true)
    {
      other1 = (random.nextInt (2));
      if (other1 != rightColourIndex)
        break;
    }
    while (true)
    {
      colourIndex1 = (random.nextInt (numColours));
      if (colourIndex1 != rightColour)
        break;
    }
    colourChoices [other1] = theColourList.get (colourIndex1);
    other2 = (3 - (rightColourIndex + other1));
    while (true)
    {
      colourIndex2 = (random.nextInt (numColours));
      if ((colourIndex2 != rightColour) &&
          (colourIndex2 != colourIndex1))
        break;
    }
    colourChoices [other2] = theColourList.get (colourIndex2);
    
    int yCord = ((int) a.getY () - 25);
    
    int xCord1 = 0;
    int xCord2 = 0;
    int xCord3 = 0;
    
    xCord1 = (random.nextInt (80) + 110);
    p.add (new Platform (xCord1, yCord, colourChoices [other1], PLATFORM_WIDTH, PLATFORM_HEIGHT));
    while (true)
    {
      xCord2 = (random.nextInt (240));
      if (xCord2 <= xCord1 - 60 || xCord2 >= xCord1 + 60)
        break;
    }
    p.add (new Platform (xCord2, yCord - 10, colourChoices [rightColourIndex], PLATFORM_WIDTH, PLATFORM_HEIGHT));
    while (true)
    {
      xCord3 = (random.nextInt (240));
      if ((xCord3 <= xCord1 - 60 || xCord3 >= xCord1 + 60) && (xCord3 <= xCord2 - 60 || xCord3 >= xCord2 + 60))
        break;
    }
    p.add (new Platform (xCord3, yCord - 15, colourChoices [other2], PLATFORM_WIDTH, PLATFORM_HEIGHT));
    
  }
  
  /**
   * Add Items to the Items ArrayList.
   */
  public void addItems ()
  {
    int iIndex = (random.nextInt (1));
    int xCoord = (random.nextInt (300));
    int yCoord = (random.nextInt (225));
    i.add (new Item (xCoord, yCoord, ITEM_DEMENSION, ITEM_DEMENSION, iIndex));
  }
  
  /**
   * Remove everything in the Items ArrayList
   */
  public void removeItems ()
  {
    i.clear ();
  }
  
  /**
   * Add Monsters to the Monster ArrayList.
   */
  public void addMonster ()
  {
    int mIndex = (random.nextInt (4));
    int xCoord = (random.nextInt (300));
    int yCoord = (random.nextInt (225));
    m.add (new Monster (xCoord, yCoord, MONSTER_DEMENSION, MONSTER_DEMENSION, mIndex));
  }
  
  /**
   * Remove everything in the Monster ArrayList
   */
  public void removeMonster ()
  {
    m.clear ();
  }
  
  
  /**
   * The method that checks if the Avatar and everything else in the Game are
   * colliding.
   * <p>
   * The first if statment suggests that if the Avatar has fallen to the ground/
   * the bottom of the screen, it's an automatic bad crash. If the user has the
   * border Item however, they will start up top again without being game-over.
   * <p>
   * The first forloop runs through EVERYTHING in the JPanel to see if anything
   * is touching the avatar.
   * <p>
   * The next for loops check for every Coordinate that the 2 specified Things touch.
   * If the two Things do share the same Coordinate ever, true will be returned.
   * If they do not, this method will return false.
   * <p>
   * The indivisual if statments inside do last minute error-traps such as the
   * user may only game-over when they hit the wrong platform going down and
   * how hitting a Monster will result game-over unless the user obtained the
   * shield. Of course, the inventory will reset itself / go back to null after
   * use.
   * 
   * @param all The ArrayList that contains every Thingg in this JPanel
   * @return boolean If the collision is a bad collision, it will return true.
   *                 If the collision is legal, it will return false.
   */
  public boolean badCollision (ArrayList < Thing > all)
  {
    if (a.getY () + AVATAR_DEMENSION > 500)
    {
      if (a.getItem () == null || a.getItem ().getIndex () != 1)
      {
        deathMessage = "Because you fell off the screen unprotected.";
        return true;
      }
      else
      {
        scrolled = true;
        a.setItem (null);
        gotItem = false;
        a.setLocation (185, 250);
        jumped = true;
        repaint();
      }
    }
    
    for (Thing second:all)
    {
      int fx = (int) a.getX ();
      int fy = (int) a.getY ();
      int sx = second.getCoordinate ().getXPos ();
      int sy = second.getCoordinate ().getYPos ();
      for (int oneH = 0 ; oneH < AVATAR_DEMENSION ; oneH++)
      {
        for (int oneW = 0 ; oneW < AVATAR_DEMENSION ; oneW++)
        {
          for (int twoH = 0 ; twoH < second.getHeight () ; twoH++)
          {
            for (int twoW = 0 ; twoW < second.getWidth () ; twoW++)
            {
              Coordinate one = new Coordinate (fx + oneW, fy + oneH);
              Coordinate two = new Coordinate (sx + twoW, sy + twoH);
              if (one.equals (two))
              {
                if (jumped)
                {
                  if (second instanceof Platform)
                  {
                    if (!colourMatch ((Platform) second))
                    {
                      deathMessage = "Because you didn't match the platform colour.";
                      return true;
                    }
                    else
                    {
                      if (add)
                      {
                        score = score + scoreIncrement;
                        scoreLabel.setText (Integer.toString (score));
                        scoreLabel.setSize ((int) (scoreLabel.getMinimumSize ().getWidth ()), 16);
                        scoreLabel.setLocation (425 - (int) (scoreLabel.getMinimumSize ().getWidth () / 2), 425);
                        if (scoreIncrement != 20)
                        {
                          if (score >= 230)
                            scoreIncrement = 15;
                          if (score >= 420)
                            scoreIncrement = 20;
                        }
                        
                        if (scoreIncrement == 10)
                          level.setText ("EASY LEVEL");
                        else if (scoreIncrement ==15)
                          level.setText ("MEDIUM LEVEL");
                        else
                          level.setText ("HARD LEVEL");
                        level.setSize ((int) (level.getMinimumSize ().getWidth ()), 16);
                        level.setLocation (425 - (int) (level.getMinimumSize ().getWidth () / 2), 350);
                        
                        add = false;
                      }
                      goodLanding = true;
                    }
                  }
                }
                if (second instanceof Monster)
                {
                  if (a.getItem()==null|| a.getItem ().getIndex () ==1)
                  {
                    deathMessage = "Because you hit a monster with no protection.";
                    return true;
                  }
                  else
                  {
                    removeMonster ();
                    removeItems ();
                    a.setItem (null);
                    gotItem = false;
                    return false;
                  }
                }
                if (second instanceof Item)
                {
                  a.setItem ((Item) second);
                  gotItem = true;
                  yesItem = false;
                  removeItems ();
                  return false;
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  /**
   * Gets the next colour that the user must land on.
   * 
   * @return Color The next colour the user must land on.
   */
  public Color getNextColour ()
  {
    return nextColour;
  }
  
  
  /**
   * Method that checks if the Platform that the user landed on corresponds
   * to the one that was asked for by the program.
   *
   * @param pf - the Platform that the user landed on
   * @return boolean -if the User landed correctly, returns true
   */
  public boolean colourMatch (Platform pf)
  {
    return pf.getColour ().equals (getNextColour ());
  }
  
  
  /**
   * Method that adds all the possible colours for the Platforms.
   * <p>
   * VARIABLE DICTIONARY: <br>
   * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
   * vanilla | Color | a vanilla colour <br>
   * brown | Color | a brown colour.<br>
   * c | ArrayList<Color> | a list of all the colours that a Platform may have
   *
   * @return ArrayList -Contains all the Colors that a Platform may have.
   */
  public ArrayList < Color > getColoursList ()
  {
    Color purple = new Color (128, 0, 128);
    Color brown = new Color (150, 75, 0);
    Color olive = new Color (128, 128, 0);
    ArrayList < Color > c = new ArrayList < Color > ();
    c.add (Color.GREEN);
    c.add (Color.RED);
    c.add (Color.ORANGE);
    c.add (Color.YELLOW);
    c.add (Color.CYAN);
    c.add (Color.PINK);
    
    c.add (Color.BLACK);
    c.add (Color.LIGHT_GRAY);
    c.add (Color.MAGENTA);
    c.add (purple);
    c.add (brown);
    c.add (olive);
    
    return c;
  }
  
  
  /**
   * Method that stores and returns all the Things in this JPanel/Game.
   * <p>
   * The for-each loops turns everything in p and i into Things and store them
   * all indivisually in the t ArrayList.
   * <p>
   * VARIABLE DICTIONARY: <br>
   * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
   * t | ArrayList<Thing> | a list of all the Things that this Game has
   * excluding the Avatar.
   *
   * @return ArrayList<Thing> a list of everything in the game.
   */
  public ArrayList < Thing > getAllThings ()
  {
    ArrayList < Thing > t = new ArrayList < Thing > ();
    try
    {
      t.add (p.get (p.size () - 1));
      t.add (p.get (p.size () - 2));
      t.add (p.get (p.size () - 3));
    }
    catch (Exception e)
    {
    }
    for (Thing items:i)
      t.add (items);
    for (Thing monsters:m)
      t.add (monsters);
    return t;
  }
  
  
  /**
   * A method that must be implemented because KeyListener was implemented.
   * It does not particularly do anything.
   */
  public void keyTyped (KeyEvent e)
  {
  }
  
  
  /**
   * A method that must be implemented because KeyListener was implemented.
   * It checks for the user's keyboard entry and if it's either the left or
   * right arrow key, it will move the Avatar either left or right. The JPanel
   * shall be repainted to erase the Avatar's previous location.
   */
  public void keyPressed (KeyEvent e)
  {
    if (e.getKeyCode () == java.awt.event.KeyEvent.VK_LEFT)
      if (a.getX () > 0)
      a.setLocation ((int) a.getX () - 5, (int) a.getY ());
    if (e.getKeyCode () == java.awt.event.KeyEvent.VK_RIGHT)
      if (((int) a.getX () + AVATAR_DEMENSION) <= 349)
      a.setLocation ((int) a.getX () + 5, (int) a.getY ());
    repaint ();
  }
  
  /**
   * A method that must be implemented because KeyListener was implemented.
   * It does not particularly do anything.
   */
  public void keyReleased (KeyEvent e)
  {
  }
  
  
  /**
   * Inherited method from Runnable to run the animation. The
   * repaint method is called to refresh the page so the animation
   * works as intended.
   * <p>
   * The while-loop is made so that if the Avatar jumped the first time, it will
   * fall the 2nd time and repeat itself. During this level, a badCollision
   * is called to make sure the Avatar did not hit anything that may cause game-
   * over. If that should happen, the while loop will break. Also, the while
   * loop will only work if the Avatar is above the bottom of the screen.
   * <p>
   * The pause will work regardless of whether the Avatar is going up or down.
   * The fact that it is assigned int values is because it is used to determine 
   * if the user later would want to exit or resume the game.
   * <p>
   * VARIABLE DICATIONARY: <br>
   * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
   * difficulty | String | represents which level the user is playing
   */
  public void run ()
  {
    while (!badCollision (getAllThings ()))
    {
      String difficulty = "";
      if (scoreIncrement == 10)
        difficulty = "Easy";
      else if (scoreIncrement == 15)
        difficulty = "Medium";
      else
        difficulty = "Hard";
      if (!jumped)
      {
        for (int x = 0 ; x < 25 ; x++)
        {
          a.setLocation ((int) a.getX (), (int) a.getY () - 5);
          if (badCollision (getAllThings ()))
          {
            frame.getFrame ().remove(ps);
            frame.changeScreen (this, new GameOver (frame, score, difficulty, deathMessage));
            return;
          }
          
          while (ps.getPause ()==0)
          {
            ps.moveToFront(this);
            ps.setEnabled (true);
            ps.setVisible (true);
          }
          if (ps.getPause () == 2)
          {
            frame.getFrame ().remove(ps);
            frame.changeScreen (this, new MainMenu (frame));
            return;
          }
          else
          {
            ps.moveToBack(this);
            ps.setEnabled (false);
            ps.setVisible (false);
          }
          pauseButton.setEnabled (true);
          repaint ();
        }
        jumped = true;
      }
      else
      {
        for (int x = ((int) a.getY ()) + AVATAR_DEMENSION ; x < 500 ; x++)
        {
          a.setLocation ((int) a.getX (), (int) a.getY () + 10);
          if (badCollision (getAllThings ()) || goodLanding||((int) a.getY () <= 10))
          {
            if (goodLanding || (goodLanding&&((int) a.getY () <= 10)))
              break;
            else
            {
              frame.getFrame ().remove(ps);
              frame.changeScreen (this, new GameOver (frame, score, difficulty, deathMessage));
              return;
            }
          }
          while (ps.getPause() == 0)
          {
            ps.moveToFront(this);
            ps.setEnabled (true);
            ps.setVisible (true);
          }
          if (ps.getPause () == 2)
          {
            frame.getFrame ().remove(ps);
            frame.changeScreen (this, new MainMenu (frame));
            return;
          }
          else
          {
            ps.moveToBack(this);
            ps.setEnabled (false);
            ps.setVisible (false);
          }
          pauseButton.setEnabled (true);
          repaint ();
        }
        if (goodLanding &&(int) a.getY () <= 100)
        {
          removePlatforms ();
          removeMonster ();
          removeItems ();
          yesMonster = false;
          a.setLocation ((int) a.getX (), 400);
          goodLanding = false;
          add = true;
        }
        else
        {
          if(goodLanding)
          {
            goodLanding = false;
            add = true;
          }
        }
        jumped = false;
        addPlatforms ();
        repaint ();
      }
    }
    repaint ();
  }
  
  
  /**
   * Method that will create a new Thread so it can run the implemented
   * Runnable method run().
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
