/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ninjazombiedeathsquad;

import java.awt.*;
import javax.swing.*;
import java.awt.font.*;
/**
 *
 * @author henglish1
 * ScoreTracking class
 * tracks and displays current score
 *
 * Public methods:
 * void displayScore(Graphics g)
 *  grabs the graphics and such to draw the score on the screen
 * void run()
 *  creates a thread  to  handle print the score
 *
 */
public class ScoreTracking implements Runnable{
   static long count; 
   private Player player;
   private  Graphics2D graph;
   private static final boolean debug = false;
   private volatile Thread scThread;


   /**
    * sets the local character variable
    * @param bob
    */
public ScoreTracking(Player bob){
    player = bob;
    
}
/**
 * display the current score
 *
 * @param g the current graphics in order to drive
 */
public void displayScore(Graphics g)
{
    // creates a 2D graphics
    graph = (Graphics2D)g;

    try {
            //sets the font for the score
            FontRenderContext frc = graph.getFontRenderContext();
            Font f = new Font("chiller", Font.BOLD, 48);
            TextLayout tl = new TextLayout(count+"", f, frc);
            graph.setColor(Color.black);
            //draws the string
            tl.draw(graph, 0, 48);
            } catch (Exception e) {
            }
}
 public void start() {
        scThread = new Thread(this);
        scThread.start();
    }

    public void stop() {
       scThread = null;
    }


/**
 * creates a thread  to  handle print the score
 */
public void run(){
     Thread thisThread = Thread.currentThread();
        while (scThread == thisThread) {
            try {
                if (debug)
                    System.out.println("Start Score Tracking");
                //creates a count variable
                count = 0;
                //loop that adds 1 to the count per iteration if player is alive
                while(true){
                    ++count;

                    if(player.isAlive() == false){
                        break;
                    }
                    //sleeps the thread
                    try{
                        thisThread.sleep(15);
                    }catch(InterruptedException e){
                    }
                    //checks if game is paused, freezes score if true.
                    while(Move.pause){
                        if(!Move.pause){
                            break;
                        }
                    }

                }
                //creates a highscore
                HighScore hs = new HighScore();
                if (debug)
                    System.out.println("Final score:"+count);
                //creates popup box if a score makes  it to the highscore
                if (hs.isHighScore(count) == true && player.isAlive() == false)
                {
                    // use addHighScore method here after you get name
                    // that method takes a name and score
                    JFrame frame = new JFrame();
                    String name = (String) JOptionPane.showInputDialog(frame,
                            "Please enter your name?", "You got a high score!",
                            JOptionPane.PLAIN_MESSAGE);
                    hs.addScore(name, (int)count);
                    if (debug)
                        System.out.println("New high score!");


                }
                if (player.isAlive() == false)
                    stop();
               } catch (Exception e){
            }
            //repaint();
        }
}
}
