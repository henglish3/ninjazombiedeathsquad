
package ninjazombiedeathsquad;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Cody
 * Character Class
 * Has all getters and setters associated with Character.
 * Contains walking method to create running effect.
 * 
 * Public methods:
 * Character(int x, int y, int width, int type)
 *  create Character with proper attributes
 * void setJFile(int type)
 *  get proper image for the type of character
 * int getJumpCount()
 *  returns value of jumpCount
 * int setJumpCount(int count)
 *  set jumpCount to value given
 * BufferedImage getImg()
 *  return the jumping image if character is jumping
 * boolean isJumping()
 *  returns true if jumpState is more than 1
 * boolean isLegOut()
 *  returns true if jumpState is 1
 * boolean isWalking()
 *  returns true if jumpState is less than 2
 * void setJumpState(int n)
 *  sets jumpSteate to value n
 * void setWalkSpeed(int n)
 *  sets walkSpeed to value n
 * int getWalkSpeed()
 *  returns value of walkSpeed
 * void incWalkSpeed(int n)
 *  increments walkSpeed by value n
 * int getWalkSpeedInc()
 *  returns value of walkSpeedInc
 * void walking(final Player p)
 *  sets Player p's leg to out if leg is currently in
 *  sets Player p's leg to in if leg is currently out
 *  sets Player p's leg to out if player is currently jumping
 *  handles walking animation
 */
public class Character extends DrawableObject {
private int jumpState;
private BufferedImage jumpingImg;
int jumpCount = 0;
int jumpSpot = 0;

Timer walker;
Timer walkInc;
ActionListener timerListener;
int walkSpeed = 250;
int walkSpeedInc = 2;
final int WALK_SPEED = 1000;
final int WALK_SPEED_MIN = 100;

/**
 *Give the attributes of the character to be drawn.
 *
 * @param x x value for location of Character
 * @param y y value for location of Character
 * @param width width of the Character
 * @param type  Type of picture to be drawn.
 */
 public Character(int x, int y, int width, int type){

      super(x,y,width,type);
      setJFile(type);


   }

 /**
 * Sets the correct image for the character.
 *
 * @param type  The number associated with the image
 * .gif required
 */
 public void setJFile(int type){
     String jFileName;

     switch(type){
         case 0: jFileName = "playerj.gif";break;
         case 1: jFileName = "player2j.gif";break;
         case 2: jFileName = "zombiedanielj.gif";break;
         case 3: jFileName = "zombieharrisonj.gif";break;
         case 4: jFileName = "zombiecodyj.gif";break;
         case 5: jFileName = "zombiebrianj.gif";break;
         case 6: jFileName = "zombiecourtneyj.gif";break;
         default: jFileName = "playerj.gif"; break;
     }
     /*try {
           jumpingImg = ImageIO.read(new File(jFileName));
       } catch (IOException e) {}*/
     try{
        jumpingImg = javax.imageio.ImageIO.read(new java.net.URL
                    (getClass().getResource(jFileName), jFileName));
     }
     catch (Exception e) { /*handled in paintComponent()*/ }
 }

 /**
 *Return the value of the variable jumpCount.
 *
 * @return value of jumpCount.
 */
public int getJumpCount()
{
    return jumpCount;
}

/**
 * Set the value of the variable jumpCount.
 *
 * @param count Value that jumpCount will be set to.
 */
public void setJumpCount(int count)
{
    jumpCount = count;
}

/**
 *Return the value of the variable jumpShot.
 *
 * @return integer value of jumpSpot.
 */
public int getJumpSpot()
{
    return jumpSpot;
}

/**
 * Set the value of the variable jumpSpot.
 *
 * @param spot The integer value jumpSpot will set to.
 */
public void setJumpSpot(int spot)
{
    jumpSpot = spot;
}

/**
 *Return the jumpingImg if the character is jumping, else return the original.
 *
 * @return  Return jumpingImg if character is jumping
 * else return super.getIimg()
 */
@Override public BufferedImage getImg(){
    if(jumpState >= 1){
        return jumpingImg;
    }
    else
    return super.getImg();
}

/**
 *Return true if jumping.
 *
 * @return  True if jumpState is 2, else false.
 */
public boolean isJumping(){
    if (jumpState == 2)
        return true;
    return false;

}

/**
 *Return true if jumpState is 1, else return false.
 *
 * @return True if jumpState is 1, else false.
 */
public boolean isLegOut()
{
    if (jumpState == 1)
        return true;
    return false;
}

/**
 *Return true if jumpState is less than 2, else return false.
 *
 * @return  True if jumpState is less than 2, else false.
 */
public boolean isWalking(){
    if (jumpState < 2)
        return true;
    else
        return false;
}

/**
 *Set the value of the variable jumpState.
 *
 * @param n Value to set jumpState to.
 */
public void setJumpState(int n)
{
    jumpState=n;
}

/**
 *Set the value of the variable walkSpeed.
 *
 * @param n Value to set walkSpeed to.
 */
public void setWalkSpeed(int n)
{
    walkSpeed = n;
}

/**
 *Return the value of the variable walkSpeed.
 *
 * @return Return value of walkSpeede.
 */
public int getWalkSpeed()
{
    return walkSpeed;
}

/**
 *Decrement the value of walkSpeed which increases the visual walk speed.
 *
 * @param n Value to decrement walkSpeed by. Effectively increases
 * the visual walk speed.
 */
public void incWalkSpeed(int n)
{
    walkSpeed = walkSpeed - n;
}

/**
 *Return the value of the variable walkSpeed Inc.
 *
 * @return  Value of walkSpeedInc.
 */
public int getWalkSpeedInc()
{
    return walkSpeedInc;
}

/**
 *Creates the walking animation for the given player.
 *
 * @param p The player to create walking effect for.
 */
//method to control walking animation of the enemy and character
public void walking(final Player p){
        timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //test to see if the player is currently jumping
                if(jumpState != 2){
                    //if the player is not jumping this if else statement will
                    //change the character from leg out to leg in position
                    if(jumpState == 1){
                        //sets the image to have the leg in
                        jumpState = 0;
                    }
                    else{
                        //sets the image to have the leg out
                        jumpState = 1;
                    }

                   }

                }
            };
        //creates the timer for continous animation of walkListener
        walker = new Timer(getWalkSpeed(), timerListener);
        //starts timer for walk animation
        walker.start();
        ActionListener walkIncListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (getWalkSpeed() > WALK_SPEED_MIN)
                {
                    System.out.println("1");
                    walker.stop();
                    incWalkSpeed(getWalkSpeedInc());
                    walker = new Timer(getWalkSpeed(), timerListener);
                    walker.start();
                }
                else
                {
                    walkInc.stop();
                    System.out.println("2");
                }

            }
        };
        walkInc = new Timer(WALK_SPEED, walkIncListener);
        //starts timer for walk animation
        walker.start();
        ActionListener dead = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(p.isAlive() == false){
                walker.stop();
                }
            }
        };
        Timer kill = new Timer(100, dead);
        kill.start();

    }
}


