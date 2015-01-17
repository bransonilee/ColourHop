/**
 * The <code>Scor</code>e class is a data class that 
 * holds the info of the high scores achieved by the players.
 * 
 * @author Branson Lee, Peng Chen
 * @version Version 2.0 May 25, 2012
 */
public class Score
{
  /**
   * <code>String</code> for the name of the player.
   */
  private String name;
  /**
   * <code>String</code> for the level of the player.
   */
  private String difficulty;
  /**
   * <code>String</code> for the score of the player.
   */
  private String score;
  
  /**
   * This constructor accepts a name, a difficulty and a score as parameters.
   * It then saves this information.
   * 
   * @param name <code>String</code> of user's name when saving.
   * @param difficulty <code>String</code> of user's level when saving.
   * @param score <code>String</code> of user's score when saving.
   */
  public Score (String name, String difficulty, String score)
  {
    this.name = name;
    this.difficulty = difficulty;
    this.score = score;
  }
  
  /**
   * Clears top 10 scores
   */
  public void clear ()
  {
    name = "";
    difficulty = "";
    score = "0";
  }
  
  /**
   * @return String name of the player that attained this highscore.
   */
  public String getName ()
  {
    return name;
  }
  
  /**
   * @return String The difficulty at which the score was achieved.
   */
  public String getDifficulty ()
  {
    return difficulty;
  }
  
  /**
   * @return String The score that achieved the highscore.
   */
  public String getScore ()
  {
    return score;
  }
}