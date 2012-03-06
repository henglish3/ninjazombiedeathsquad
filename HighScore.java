package ninjazombiedeathsquad;

import java.io.*;

/**
 * @author Mark Daniel Gibbons
 * HighScore class
 * Automatically reads and writes scores to scores.dat
 * Whenever score is added to scores array, file is rewritten
 *
 * Public methods:
 * boolean isHighScore(int score)
 *  Determine if score will be in the high scores
 * Score[] getScores()
 *  Get an array of all the Score objects
 * boolean addScore(String name, int score)
 *  Add a score to the high scores
 *  Does nothing if the score isn't a high score
 * Score getScore(int n)
 *  Get one specific Score object
 * int getScoreVal(int n)
 *  Get the score value in a Score object
 * String getScoreName(int n)
 *  Get the name value in a Score object
 * void setScore(int n, String name, int score)
 *  Set the Score object at location n in the scores array
 *  to a new score object with the given name and score
 */
public class HighScore implements Serializable {
    // Settings
    static int NUM_HIGH_SCORES = 10;
    static String FILENAME = "scores.dat";
    private static final boolean debug = true;

    // Serialized data
    Score[] scores;

    /**
     * Initializes a HighScore object and calls the method
     * to read the scores from scores.dat (readScores()).
     */
    HighScore()
    {
        readScores();

        
       /* if (debug)
        {
            System.out.println("First high score:");
            System.out.println(scores[0].getName()+scores[0].getScore());
            System.out.println("Second high scores:");
            System.out.println(scores[1].getName()+scores[1].getScore());
        }*/
        
        // First time only
      /*
        scores = new Score[10];
        for (int i=0; i < 10; i++)
            scores[i] = new Score();
        scores[0].setName("Anita Bohn");
        scores[0].setScore(99999);
        
        scores[1].setName("Earl E. Byrd");
        scores[1].setScore(40000);
        scores[2].setName("John Backus");
        scores[2].setScore(30000);
        scores[3].setName("Justin Thyme");
        scores[3].setScore(20000);
        scores[4].setName("Ada Lovelace");
        scores[4].setScore(3500);
        scores[5].setName("Frank N. Sense");
        scores[5].setScore(3504);
        scores[6].setName("Alan Turring");
        scores[6].setScore(3000);
        scores[7].setName("Benjamin Dover");
        scores[7].setScore(2990);
        scores[8].setName("Hugh G. Rection");
        scores[8].setScore(2750);
        scores[9].setName("Jim Bob Frankenstein");
        scores[9].setScore(250);
        writeScores();
        */
}
        
    /**
     * Determines if the score given would be in the
     * high scores.
     * 
     * @param score Score that you want to check to see if it would
     * be a high score.
     * @return Returns true if the score should be in the top 10;
     * otherwise false.
     */
    public boolean isHighScore(long score)
    {
        for (int i=0; i < scores.length; i++)
        {
            if (score > scores[i].getScore())
                return true;
        }
        return false;
    }

    /**
     * All Score objects
     *
     * @return Array of Score objects with scores in scores.dat
     */
    public Score[] getScores()
    {
        return scores;
    }

    /**
     * One Score object
     * @param n Location in scores array for the Score.
     * @return A Score object containing a name and score.
     */
    public Score getScore(int n)
    {
        return scores[n];
    }

    /**
     * Individual score for Score object
     *
     * @param n Location in scores array for the Score.
     * @return Integer value of the score in the Score object.
     */
    public int getScoreVal(int n)
    {
        return scores[n].getScore();
    }
 
    /**
     * Individual name for Score object
     * 
     * @param n Location in scores array for the Score.
     * @return Name of the player with high score at location n.
     */
    public String getScoreName(int n)
    {
        return scores[n].getName();
    }
    /**
     * Set Score at position n in scores array
     *
     * @param n Location in scores array for the Score.
     * @param name New player name for the score.
     * @param score Score that the player achieved.
     */
    public void setScore(int n, String name, int score)
    {
        scores[n]=new Score(name,score);
    }
    
    /**
     * Adds high score to the scores array
     * Returns true if score was added to top 10
     * Otherwise, false
     *
     * @param name Name of the player.
     * @param score Score that the player achieved.
     * @return True or false depending if the score was added or not.
     */
    public boolean addScore(String name, int score)
    {
        // Player name and score
        Score newScore = new Score(name,score);

        /* Player has a top 10 score -> Move lower and equal to scores down 1
         *                           -> Put score in correct spot
         *                           -> Serialize into file
         *                           -> Return true
         * 
         * Otherwise                 -> Return false
         */
        for (int i=0; i < scores.length; i++)
        {
            // Find highest score that this score is greater than
            if (score > scores[i].getScore())
            {
                // Move all lower scores
              /*  for (int j=i; j < scores.length-1; j++){
                    setScore(j+1,getScoreName(j),getScoreVal(j));
                }*/

                for (int k = 9; k >= i; k--){
                    setScore(k, getScoreName(k-1), getScoreVal(k-1));
                }
                // Put in new score
                setScore(i,name,score);

                writeScores();
                return true;
            }
        }

        // No scores, they get first position
        if (scores.length == 0)
        {
            scores[0] = newScore;
            writeScores();
            return true;
        }
        return false;
    }

    /**
     * Use internally only, writes scores object to scores.dat
     *
     */
    private void writeScores()
    {
        try
        {
            FileOutputStream fis = new FileOutputStream(FILENAME);
            ObjectOutputStream ois = new ObjectOutputStream(fis);

            ois.writeObject(scores);
            
            if (debug)
                System.out.println("Wrote high scores");
            
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error accessing file "+FILENAME);
            System.exit(-1);
        }
        catch (IOException e)
        {
            System.err.println("Input/Output Error");
            System.exit(-2);
        }
        catch (Exception e)
        {
            System.err.println("Unknown error reading");
            System.exit(-5);
        }
    }

    /** Use internally only, reads in scores object from scores.dat
     * 
     */
    private void readScores()
    {
        try
        {
            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            scores = (Score[])ois.readObject();

            if (debug)
                System.out.println("Read high scores");
            
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error accessing file "+FILENAME);
            System.exit(-1);
        }
        catch (IOException e)
        {
            System.err.println("Input/Output Error");
            System.exit(-2);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Error accessing class");
            System.exit(-3);
        }
        catch (Exception e)
        {
            System.err.println("Unknown error reading");
            System.exit(-4);
        }
    }

}
