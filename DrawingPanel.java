/*
 * Mark Daniel Gibbons
 */

package ninjazombiedeathsquad;

import java.awt.geom.Line2D;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;


/**
 * @author Cody
 * DrawingPanel class
 * Creates the environment that the game takes place in.
 * Creates the player and enemy characters and creates the obstacle.
 *
 * Public methods:
 * void setDisplayTime(boolean t)
 *  set value of displayTime
 * void setDisplayScore(boolean s)
 *  set value of displayScore
 * void startTimeKeeping()
 *  creates a thread with a timeKeeper in it and starts the timer
 * void stopTimeKeeping()
 *  stops the timeKeeper thread
 * Player getPlayer()
 *  returns the value of player
 * void startScoring()
 *  creates a new thread that keeps the score
 * void initiateMovers(Obstacle[] obstacles)
 *  creates a new thread initiates the objects to be moved
 * Level getLevel()
 *  returns the value of level
 * void run()
 *  repaints the objects
 * void paintComponent(Graphics g)
 *  passes drawing to the level class
 */

public class DrawingPanel extends JPanel implements Runnable {
    Level level;    //Creates a player rectangle
    private int GROUND_LEVEL = 380;
    private int START_LEVEL = 180;

    ScoreTracking scoretracking;
    TimeKeeper timekeeper;
    Player player = new Player(376,START_LEVEL,100, Menu.playerChosen);
    //Creates a enemy rectangle
    Character enemy  = new Character(76,START_LEVEL,100, Menu.enemyChosen);
    
    // max number of obstacles we'll use
    private static int NUM_OBSTACLES = 7;
    boolean displayScore;
    boolean displayTime;
    Thread tkThread;
    Thread mThread;

    DrawingPanel(boolean ds, boolean dt, JFrame frame)
    {
        Obstacle[] obstacles = new Obstacle[NUM_OBSTACLES];
        level = new Level(player, enemy, GROUND_LEVEL, START_LEVEL, obstacles, NUM_OBSTACLES, frame, this);
        startScoring();
        startTimeKeeping();
        
        displayScore=ds;
        displayTime=dt;
        
        initiateMovers(obstacles); // Thread move background,obstacle,etc.

        // Space bar pressed, begin jump
        Action jumpListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent aEvent)
            {
                level.jump(player);
            }
        };

        // P key pressed, pauses game
        Action pauseListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent bEvent)
            {
                if (Move.pause){
                    Move.pause = false;
                } else {
                    Move.pause = true;
                }

            }
        };

        // Key binding for space bar
        KeyStroke space = KeyStroke.getKeyStroke(' ');
        InputMap iMap = this.getInputMap();
        iMap.put(space, "jump");
        ActionMap aMap = this.getActionMap();
        aMap.put("jump", jumpListener);

        // Key binding for p key
        KeyStroke p = KeyStroke.getKeyStroke('p');
        InputMap jMap = this.getInputMap();
        jMap.put(p, "pause");
        ActionMap hMap = this.getActionMap();
        hMap.put("pause", pauseListener);

    }
    /**
     * Sets the value of the variable displayTime
     *
     * @param t Value that displayTime will be set to.
     */
    public void setDisplayTime(boolean t)
    {
        displayTime=t;
    }
    /**
     * Sets the value of the variable displayScore.
     *
     * @param s Value that displayScore will be set to.
     */
    public void setDisplayScore(boolean s)
    {
        displayScore=s;
    }
    /**
     * Creates a new thread that keeps the time played.
     */
    public void startTimeKeeping()
    {
        timekeeper = new TimeKeeper(player);
        tkThread = new Thread(timekeeper);
        tkThread.start();
    }
   /**
    * Stops the timeKeeping thread.
    */
    public void stopTimeKeeping()
    {
        tkThread.stop();
    }
    /**
     * Returns the value of the player variable.
     *
     * @return value of player.
     */
    public Player getPlayer()
    {
        return player;
    }
    /**
     * Creates a new thread that keeps the score.
     */
    public void startScoring()
    {
        scoretracking = new ScoreTracking(player);
        scoretracking.start();
    }
    /**
     * Creates a new thread that initiates the objects to be moved.
     *
     * @param obstacles array containing integers associated with different
     * obstacles.
     */
    private void initiateMovers(Obstacle[] obstacles)
    {
        Move move = new Move(level, player, enemy, obstacles, NUM_OBSTACLES);
        mThread=new Thread(move);
        mThread.start();
    }
    /**
     * Stops the movers thread.
     */
    public void stopMovers()
    {
        mThread.stop();
    }
    /**
     * Returns the value of the variable level.
     *
     * @return value of level.
     */
    public Level getLevel()
    {
        return level;
    }
    // Redraw screen every 15ms
    public void run()
    {
        try
        {
            while (true)
            {
                repaint();
                // Fixes performance problem
                Thread.sleep(15);
            }
        }
        catch (InterruptedException e)
        {
            // Ignore, just redraw next time
        }
    }


    BufferedImage img;
    // Pass drawing responsibility to Level class
    @Override public void paintComponent(Graphics g)
    {
        Graphics2D gr;
        gr = (Graphics2D) g;
        super.paintComponent(g);
        level.draw(g);
        if (displayScore)
            scoretracking.displayScore(g);
        if (displayTime)
            timekeeper.displayTime(g);
       // this.requestFocus();
    }
}
