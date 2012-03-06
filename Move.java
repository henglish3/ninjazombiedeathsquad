/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ninjazombiedeathsquad;


/**
 *
 * @author henglish1
 * Move class
 * Automatically moves different objects across the screen such as the
 * backgrounds and obstacles.
 *
 * Public methods:
 * void setPace(int n)
 *  sets the pace that obstacles move
 * int getPace()
 *  returns the current pace
 * void incPace()
 *  Add 2 to the pace
 * void run()
 *  creates a thread to move the obstacles and calls the move functions
 * void backGround(Obstacle o int n)
 *  loops the background
 * void moveObstacle()
 *  handles the moving of obstacles randomly
 * void obstacleMove(Obstacle o)
 *  handles collison detection of the obstacles
 */
public class Move implements Runnable {
    //dictates where speed of game
    private static int pace = 1000; // higher is faster
    private  int trackPace = 700; // spaces out the obstacles, increases with pace
    private static final int OBS_MOVE_WAIT_RATE = 50; // lower is faster
    // number of pixels before which enemy jumps for death obstacle
    private static final boolean debug = false;
    public static boolean pause = false;
    // creates all variables for this class
    Player player;
    Character enemy;
    Obstacle[] obstacles;
    Level level;
    private final int NUM_OBSTACLES;


    /**
     *  creates an instance of move
     *
     * @param level
     * @param player
     * @param enemy
     * @param obstacles
     * @param NUM_OBSTACLES
     */
    Move(Level level, Player player, Character enemy, Obstacle[] obstacles, int NUM_OBSTACLES)
    {
        this.player = player;
        this.enemy = enemy;
        this.obstacles = obstacles;
        this.level = level;
        this.NUM_OBSTACLES=NUM_OBSTACLES;
        
    }
    /**
     * sets the pace for the game.. default is 1000
     * @param newPace
     */
    public static void setPace(int newPace)
    {
        pace = newPace;
    }
    /**
     *
     * @return the pace
     */
    public static int getPace()
    {
        return pace;
    }
    /**
     * increases the pass by 2
     */
    public void incPace()
    {
        pace += 2;
    }
    /**
     * created the thread and called the different functions
     */
    public void run()
    {
        try
        {
            // infinite loop to keep this move running
            while (true)
            {
                //calls the move background for each of the three backgrounds
                for (int i=0; i < 3; i++)
                    backGround(obstacles[i], 1);
                //call move obstacles
                moveObstacles();
                //increases the pace every loop
                incPace();
                //allows the thread to sleep
                Thread.sleep(OBS_MOVE_WAIT_RATE);
                //tests if pause button is pressed, freezes background and obstacles.
                while (pause){
                    if (!pause){
                        break;
                    }
                }
            }
        }
        catch (InterruptedException e)
        {

        }
    }
    /**
     * creates a method that will loop a background in accordance with order
     * @param bg
     * @param bgNum
     */
    private void backGround(final Obstacle bg, final int bgNum)
    {
    //dicates at which point background will start back over at the
    //beginning
    final int endLocation = bgNum * 1048;

    //creates a constant animation for the background
        if (player.isAlive() == true){
            bg.move(bg.getX()-(pace/100), bg.getY());
            //resets background back to beginning if hits ending
            if(bg.getX() <= -1048){
                    bg.move(endLocation, bg.getY());
            }
        }
    }

    /**
     *method that handles obstacle placement.
     */
    private void moveObstacles(){
        //initiates the four obstacles
        obstacleMove(obstacles[3]);
        obstacleMove(obstacles[4]);
        obstacleMove(obstacles[5]);
        obstacleMove(obstacles[6]);

       
        /*
         * increases trackPace, which is the int that spaces out
         * the obstacles based on the pace. As the pace hits the
         * values in the if / elses, it increase trackPace. Once
         * pace has surpassed 5500, trackPace is increased every
         * time pace's modulo result is 0.
        */
        if (pace == 2000){
           trackPace = trackPace+100;
        } else if (pace == 2500){
            trackPace = trackPace+100;
        } else if (pace == 3000){
            trackPace = trackPace+100;
        } else if (pace == 3500){
            trackPace = trackPace+100;
        } else if (pace == 4000){
            trackPace = trackPace+100;
        } else if (pace == 4500){
            trackPace = trackPace+150;
        } else if (pace == 5000){
            trackPace = trackPace+150;
        } else if (pace == 5500){
            trackPace = trackPace+200;
        } else if (pace > 5500){
            if (pace % 200 == 0){
            trackPace = trackPace+50;
            }
        }


       /*
        * moves the obstacle along the game board based on the
        * trackPace integer. trackPace's value is the distance
        * between an obstacle, and the one after it. 
        */
       if (obstacles[3].getX() < -500){
            obstacles[3].move(trackPace+obstacles[6].getX(), obstacles[3].getY());
        }

        if (obstacles[4].getX() < -500){
            obstacles[4].move(trackPace+obstacles[3].getX(), obstacles[4].getY());
        }

        if (obstacles[5].getX() < -500){
            obstacles[5].move(trackPace+obstacles[4].getX(), obstacles[5].getY());
        }

        if (obstacles[6].getX() < -500){
            obstacles[6].move(trackPace+obstacles[5].getX(), obstacles[6].getY());
        }
    }

    /**
     * handles collison detection
     * @param obs the obstacle to be detected.
     */
    private void obstacleMove(final Obstacle obs){
    if (player.isAlive() == false)
        return;
    
       // Check for auto kill obstacle
        if (obs.getType() == 0)
        {
            // Enemy auto jumps
            if (enemy.getRightEdge() >= obs.getLeftEdge()-(20+
                    (pace/1000)) && enemy.getLeftEdge()
                    <= obs.getRightEdge())
                if ((!enemy.isJumping()) && (player.isAlive()))
                    level.jump(enemy);

            // Check for player hit
            if (player.getRightEdge() >= obs.getLeftEdge()-50 &&
                    player.getLeftEdge() <= obs.getLeftEdge() &&
                     player.getBottomEdge() == level.GROUND_LEVEL)
                obs.setHit(true);

            // Kill player if he was hit
            if (obs.isHit())
                level.kill();
                
        }
        // Check for slow down obstacle
        if (obs.getType() == 1)
        {
             if(debug){
                System.out.println("The player's right edge is:"+player.getRightEdge());
                System.out.println("The obstacle's left edge is:"+obs.getLeftEdge());
             }

            // Enemy auto jumps
            if (enemy.getRightEdge() >= obs.getLeftEdge()-(20+
                    (pace/1000)) && enemy.getLeftEdge() <= obs.getRightEdge())
                if ((!enemy.isJumping()) && (player.isAlive()))
                    level.jump(enemy);

            // Check for player hit
            if (player.getRightEdge() >= obs.getLeftEdge()-50 &&
                    player.getLeftEdge() <= obs.getLeftEdge() &&
                     player.getBottomEdge() >= obs.getTopEdge())
                obs.setHit(true);

            // Kill player if he was hit
            if(obs.isHit()){
               // level.kill();
               player.move(player.getX()-2, player.getY());
             }
            
            
            
            if(enemy.getX()>player.getX()){
                level.kill();
            }

        }

        //moves the back ground to start location
        obs.move(obs.getX()-(pace/100), obs.getY());

        if(obs.isHit()){
            if (debug)
                System.out.println("You've been hit");
            obs.setHit(false);
        }
    }
}
