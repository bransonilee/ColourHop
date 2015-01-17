import java.io.*;
import java.util.*;

import javax.swing.*;

/**
 * The ScoreList class controls the Scores that represent the highscores.
 * It imports the scores from a file, sorts the scores by score and also saves the scores to the file.
 * 
 * <p>
 * <b><u>Variable</u> | <u>Type</u> | <u>Description <br> </u></b>
 * 
 * 
 * @author Branson Lee, Peng Chen
 * @version Version 2.0 May 25, 2012
 */
public class ScoreList
{
  /**
   * An <code>ArrayList<Score></code> used to stores the top ten scores.
   */
  private static ArrayList<Score> scoreList = new ArrayList<Score> (10);
  
  /**
   * This constructor calls the <code>importScores</code> method which
   * imports the scores from a file.
   * It then calls the sort method to sort the <code>scoreList 
   * ArrayList</code> by score.
   */
  public ScoreList ()
  {
    scoreList.clear ();
    importScores ();
    sort ();
  }
  
  /**
   * This method imports the top ten scores from a file.
   * The for loops are used to access every score and the if statements check 
   * for the header to see if the file is useable.
   * 
   * @exception IOException in case the file IO fails.
   */
  public void importScores ()
  {
    try
    {
      BufferedReader in = new BufferedReader (new FileReader ("Resources/bcltoqz.qwack"));
      if (!(in.readLine ().equals ("dqplgy Pengchen Branson Cr4k3r5 4nd Ch33z3 brsvrm")))
      {
        JOptionPane.showMessageDialog (null, "The high scores file has been tampered with and will now be wiped.", "Error", JOptionPane.ERROR_MESSAGE);
        try
        {
          PrintWriter out = new PrintWriter (new FileWriter ("Resources/bcltoqz.qwack"));
          out.println ("dqplgy Pengchen Branson Cr4k3r5 4nd Ch33z3 brsvrm");
          for (int a = 0 ; a < 10 ; a++)
          {
            out.println ("");
            out.println ("");
            out.println ("0");
          }
        }
        catch (IOException ioe)
        {
        }
        return;
      }
      else
      {
        for (int a = 0 ; a < 10 ; a++)
        {
          scoreList.add (new Score (in.readLine (), in.readLine (), in.readLine ()));
        }
      }
      in.close ();
    }
    catch (IOException ioe)
    {
      JOptionPane.showMessageDialog (null, "The high scores file is missing or has been corrupted. A new one will be generated.", "Error", JOptionPane.ERROR_MESSAGE);
      try
      {
        PrintWriter out = new PrintWriter (new FileWriter ("Resources/bcltoqz.qwack"));
        out.println ("dqplgy Pengchen Branson Cr4k3r5 4nd Ch33z3 brsvrm");
        for (int a = 0 ; a < 10 ; a++)
        {
          out.println ("");
          out.println ("");
          out.println ("0");
        }
        out.close ();
      }
      catch (IOException ioe2)
      {
      }
      importScores ();
    }
  }
  
  /**
   * This method writes the top ten scores in the 
   * <code>scoreList ArrayList</code> to a file.
   * For loop used to access every score.
   * 
   * @exception IOException in case the file IO fails.
   */
  public void save ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter ("Resources/bcltoqz.qwack"));
      out.println ("dqplgy Pengchen Branson Cr4k3r5 4nd Ch33z3 brsvrm");
      int a = 0;
      for (Score score : scoreList)
      {
        System.out.println (a);
        out.println (score.getName ());
        out.println (score.getDifficulty ());
        out.println (score.getScore ());
        a++;
      }
      out.close ();
    }
    catch (IOException ioe) 
    {
    }
  }
  
  /**
   * This method sorts the Score objects in <code>scoreList ArrayList</code> 
   * by highest to lowest score. For loop used to sort. If statement skips all 
   * the scores that are higher.
   */
  private void sort ()
  {
    for (int a = 0 ; a < 10 ; a++)
    {
      Score temp = scoreList.get (a);
      for (int b = a + 1 ; b < 10 ; b++)
      {
        if (!temp.getScore ().equals ("") && Integer.parseInt (temp.getScore ()) < Integer.parseInt (scoreList.get (b).getScore ()))
        {
          scoreList.set (a, scoreList.get (b));
          scoreList.set (b, temp);
          break;
        }
      }
    }
    save ();
  }
  
  /**
   * @return ArrayList<Score> The top ten scores.
   */
  public static ArrayList<Score> getScores ()
  {
    return scoreList;
  }
}