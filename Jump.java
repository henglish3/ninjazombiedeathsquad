 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ninjazombiedeathsquad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.font.*;

/**
 * @author Cody
 * Creates listeners that will make the character jump.
 * Uses jumpUpListener and jumpUpTimer to make the character
 * move up.
 * Uses jumpUpListener and jumpEndTimer to test for the character
 * reaching maximum height, at which time it will stop the
 * jumpUpTimer, start the landingListener, and stop itself.
 * Uses landingListener and landingTimer to make the character
 * go down to ground level.
 *
 */


public class Jump implements Runnable {
    private static boolean debug = false;

    //Declerations for jump method. Cody.
    ActionListener jumpEndListener;
    //Will test for stop jump.
    Timer jumpEndTimer;
    ActionListener jumpUpListener;
    //Will handle character jump.
    Timer jumpUpTimer;
    ActionListener landingListener;
    //Will handle character landing.
    Timer landingTimer;

    final int JUMP_DISTANCE = 2;
    final int JUMP_DELAY = 12/(Move.getPace()/1000);
    final int JUMP_HEIGHT = 100;
    final int START_LEVEL;
    final int TIME_BTWN_JUMPS = 200;

    private Character toon;

    Jump(int sl, Character toon)
    {
        START_LEVEL=sl;
        this.toon=toon;
    }
    public void run()
    {
        long lastJump=0;

       //For jump up.
        jumpUpListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                   //For debugging.
               if (debug)
                    System.out.println("Jumping"); 
                //Draw Player JUMP_DISTANCE pixel higher.
               toon.setY(toon.getY() - JUMP_DISTANCE);
            }
        };

        // When jump time is over.
        jumpEndListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Test if character is jumping and above jump height.
                //If so, stop the jump, and start the landing.
                if (toon.getY() < toon.getJumpSpot() - JUMP_HEIGHT)
                {
                    //For debugging.
                    if (debug)
                        System.out.println("End Jump");
                    jumpUpTimer.setRepeats(false);
                    landingTimer.start();
                    jumpEndTimer.setRepeats(false);
                }
            }
        };

        // Will make character land.
        landingListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Check if player has made it to ground level yet.
                if (toon.getY() < START_LEVEL)
                {
                    //Move player down JUMP_DISTANCe pixel
                    toon.setY(toon.getY() + JUMP_DISTANCE);
                    //For debugging.
                      if (debug)  
                        System.out.println("Landing");
                }
                //Will stop landing once player is to ground level.
                //If so, stop the landing.
                else
                {
                    toon.setJumpCount(0);
                    //For debugging.
                    if (debug)
                        System.out.println("Landed");          
                    toon.setJumpState(0);
                    landingTimer.setRepeats(false);
                }
            }
        };

            //Instantiate the timers.
            //Set timers to repeat.
            jumpUpTimer = new Timer(JUMP_DELAY, jumpUpListener);
            landingTimer = new Timer(JUMP_DELAY, landingListener);
            jumpEndTimer = new Timer(JUMP_DELAY, jumpEndListener);
            toon.setJumpState(2);
            jumpUpTimer.setRepeats(true);
            jumpEndTimer.setRepeats(true);
            landingTimer.setRepeats(true);
            
            //Start timers.
            jumpUpTimer.start();
            //For debugging.
            if (debug)
                System.out.println("jumpUpTimer started");
            jumpEndTimer.start();
            //For debugging.
            if (debug)
                System.out.println("endJumpTimer started");

    }
}
