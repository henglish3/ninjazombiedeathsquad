/**
 * @author Cody
 * Level class
 *  Handles all the logic for creating obstacles, jumping, death, and drawing.
 *
 * Public methods:
 * void jump(final Character toon)
 *  makes the character jump if it is not above the max allowed jumps
 * void kill()
 *  kills the player if a death situation has been reached
 * void draw(Graphics g)
 *  draws everything
 *
 * Private methods:
 * void makeObstacles()
 *  creates the background and obstacle objects
 */
package ninjazombiedeathsquad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.util.*;

public class Level {
    // Debug mode

    private static boolean debug = false;
    DrawingPanel canvas;
    Player player;
    Character enemy;
    Obstacle[] obstacles;
    Thread jThread;
    final Sound endGameSound = new Sound();



   /**
    * Value to be used for the level which will be considered the ground.
    */
    public final int GROUND_LEVEL;
    /**
     * Value to be used for the level which will be considered the start spot.
     */
    public final int START_LEVEL;
    /**
     * Value to be used for the number of obstacles available.
     */
    private final int NUM_OBSTACLES;
    /**
     * Value to be used as a limit for the number of jumps allowed before landing.
     */
    public final int JUMP_LIMIT = 2;
    public static JFrame gameFrame;

    Level(Player p, Character e, int gl, int sl, Obstacle[] obstacles, int NUM_OBSTACLES, JFrame frame, DrawingPanel c) {
        player = p;
        gameFrame=frame;
        enemy = e;
        GROUND_LEVEL = gl;
        START_LEVEL =sl;
        this.obstacles = obstacles;
        canvas = c;

        //makes characters walk
        player.walking(player);
        enemy.walking(player);

        makeObstacles();
        this.NUM_OBSTACLES=NUM_OBSTACLES;
       
    }

    // create obstacles here
    private void makeObstacles()
    {
        /* *******************IMPORTANT NOTE ***********************
         * If you change the number of obstacles by adding or removing
         * any of them below, be sure to change the value NUM_OBSTACLES
         * in DrawingPanel.java to however many obstacles need to be drawn
         * including the backgrounds.
         * Example: 3 backgrounds + 5 pitfalls -> NUM_OBSTACLES = 8
         */
        // background
        obstacles[0] = new Obstacle(0, -54, 50, 50, 8, 2);
        obstacles[1] = new Obstacle(1042, -54, 50, 50, 8, 2);
        obstacles[2] = new Obstacle(2090, -54, 50, 50, 8, 2);
        
        //set up a random object to select a random number.
         Random randomize = new Random();
         //sets the random number equal to obstacleChoice
        int obstacleChoice = randomize.nextInt(5);
        //picks a random group of obstacles to create so that objects are in
        //different orders when the game starts.
        switch (obstacleChoice){
            case 0:
                obstacles[3] = new Obstacle(1048, 315, 100, 66, 11, 1);
                obstacles[4] = new Obstacle(1048*2, 375, 100, 66, 10, 0);
                obstacles[5] = new Obstacle(1048*3, 240, 100, 66, 7, 1);
                obstacles[6] = new Obstacle(1048*4, 315, 100, 66, 12, 1);
                break;
            case 1:
                obstacles[3] = new Obstacle(1048, 315, 100, 66, 12, 1);
                obstacles[4] = new Obstacle(1048*2, 315, 100, 66, 11, 1);
                obstacles[5] = new Obstacle(1048*3, 375, 100, 66, 10, 0);
                obstacles[6] = new Obstacle(1048*4, 240, 100, 66, 7, 1);
                break;
            case 2:
                obstacles[3] = new Obstacle(1048, 375, 100, 66, 10, 0);
                obstacles[4] = new Obstacle(1048*2, 315, 100, 66, 12, 1);
                obstacles[5] = new Obstacle(1048*3, 240, 100, 66, 7, 1);
                obstacles[6] = new Obstacle(1048*4, 315, 100, 66, 11, 1);
                break;
            case 3:
                obstacles[3] = new Obstacle(1048, 315, 100, 66, 12, 1);
                obstacles[4] = new Obstacle(1048*2, 240, 100, 66, 7, 1);
                obstacles[5] = new Obstacle(1048*3, 315, 100, 66, 11, 1);
                obstacles[6] = new Obstacle(1048*4, 375, 100, 66, 10, 0);
                break;
            case 4:
                obstacles[3] = new Obstacle(1048, 250, 100, 66, 7, 1);
                obstacles[4] = new Obstacle(1048*2, 375, 100, 66, 10, 0);
                obstacles[5] = new Obstacle(1048*3, 315, 100, 66, 11, 1);
                obstacles[6] = new Obstacle(1048*4, 315, 100, 66, 12, 1);
                break;
        }
    }
    /**
     * Makes the character jump if it has not reached the max number of jumps.
     *
     * @param toon The character to which the jump will be applied.
     */
    public void jump(final Character toon) {
        if (player.isAlive())
        {
            // don't let player jump more than once at a time
            if (toon==player && player.isJumping() 
                    && player.getJumpCount() >= JUMP_LIMIT)
                return;
            toon.setJumpCount(toon.getJumpCount() + 1);
            toon.setJumpSpot(toon.getY());
            Jump j = new Jump(START_LEVEL, toon);
            //Thread jThread = new Thread(j);
            jThread = new Thread(j);
            if(toon.getJumpCount() == 1)
                jThread.start();
        }
    }
    /**
     * Kills the character. Ends the game.
     */
    public void kill() {
        //terminal output of kill
        if (debug)
            System.out.println("kill!");
        //sets the alive to false
        player.setAlive(false);
        Menu.enemyAMusic.enemy1SoundStop();
        Menu.enemyBMusic.enemy2SoundStop();
        Menu.enemyCMusic.enemy3SoundStop();
        Menu.enemyDMusic.enemy4SoundStop();
        Menu.enemyEMusic.enemy5SoundStop();
        endGameSound.endGameSound();


        //zombie catches player if not already
        ActionListener dead = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //test to see if zombie is already caught player
                if (enemy.getX() <= player.getX()) {
                    //if not animates walking
                    if (enemy.isLegOut() == true) {
                        //set the leg to the int which means leg in
                        enemy.setJumpState(0);
                    } else {
                        //sets the leg to the int which means leg out
                        enemy.setJumpState(1);
                    }
                    //moves zombie to ontop of player
                    enemy.move(enemy.getX() + 10, enemy.getY());
                }
            }
        };
        //creates a timer to walk up to player
        javax.swing.Timer kill = new javax.swing.Timer(100, dead);
        kill.start();
        


    }
    /**
     * Draws everything.
     *
     * @param g What will be drawn.
     */
    //Method to draw everything
    public void draw(Graphics g) {
        //creates 2D graphics
        Graphics2D gr;
        //Converts our current graphics into 2D graphics
        gr = (Graphics2D) g;
        //try block incase wrong parameters were passd like bad image type
        try {
            //draws the background images in order.
            for (int i = 0; i < NUM_OBSTACLES; i++) {
                if (obstacles[i].getX() < 2000)
                    gr.drawImage(obstacles[i].getImg(), obstacles[i].getX(), obstacles[i].getY(), null);
            }

            //sets the color of ground line and draws it
            gr.setColor(Color.BLUE);
            Line2D.Double blueLine = new Line2D.Double(0, 375, 1024, 375);
            gr.draw(blueLine);
            //draws player and enemy
            gr.drawImage(player.getImg(), player.getX(), player.getY(), null);
            gr.drawImage(enemy.getImg(), enemy.getX(), enemy.getY(), null);


            //exception catch block not used because everything should be internal
        } catch (Exception e) {
        }

        //draws the death sequence if player dies.
        if (player.isAlive() == false) {
            //creates the blood splatter.
            Obstacle death = new Obstacle(1048, 375, 50, 50, 9, 2);
            //try block for drawing
            try {
                //draws blood splatter
                gr.drawImage(death.getImg(), 200, 40, null);

                //sets the font for the game over
                FontRenderContext frc = gr.getFontRenderContext();
                Font f = new Font("chiller", Font.BOLD, 48);
                TextLayout tl = new TextLayout("Game Over.", f, frc);
                gr.setColor(Color.black);
                //draws the string
                tl.draw(gr, 400, 250);
            } catch (Exception e) {
            }
            //Draw restart to menu button.
            Action restartListener = new AbstractAction() {
                public void actionPerformed(ActionEvent aEvent) {
                    gameFrame.dispose();
                    new Menu();
                }
            };
            final JButton restartButton = new JButton("Return to Menu.");
            restartButton.addActionListener(restartListener);
            canvas.add(restartButton);

            Insets i = canvas.getInsets();
            Dimension s = restartButton.getPreferredSize();
            restartButton.setBounds(250 + i.left, 250 + i.top, s.width, s.height);
            
            //Draw restart play button.
            Action replayListener = new AbstractAction() {
                public void actionPerformed(ActionEvent aEvent) {
                    gameFrame.dispose();
                    Move.setPace(1000);
                    Move.pause = false;
                    new NinjaZombieDeathSquad();
                }
            };
            final JButton replayButton = new JButton("Replay game.");
            replayButton.addActionListener(replayListener);
            canvas.add(replayButton);
            Dimension r = replayButton.getPreferredSize();
            replayButton.setBounds(700 + i.left, 250 + i.top, r.width, s.height);
        }
    }
}
