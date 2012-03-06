

package ninjazombiedeathsquad;

/**
 *
 * @author harrisonenglish
 *
 * Player class
 * carries all the stats of a player
 *
 * Public methods:
 * long getLastJump()
 *  returns the last jump
 * void setJumpCounter()
 * sets the jump counter to the current time
 * boolean isAlive()
 *  tells if a character is alive
 * void setAlive(boolean t)
 *  sets the alive boolean to true
 */
public class Player extends Character {
   //two variables of player
   private boolean alive;
   private long lastJump;

   /**
    * creates an instance of the player
    * @param x
    * @param y
    * @param width
    * @param type
    */
   public Player(int x, int y, int width, int type)
   {
       super(x,y,width,type);
       alive = true;
   }
   /**
    * returns the last jump
    * @return lastJump
    */
   public long getLastJump()
   {
       return lastJump;
   }
   /**
    * sets the lastJump to the current time.
    */
   public void setJumpCounter()
   {
       lastJump = System.currentTimeMillis();
   }
   /**
    * returns true if player is not still alive
    * @return alive
    */
   public boolean isAlive(){
       return alive;
   }
   /**
    * sets the alive variable
    * @param temp the temporary variable 
    */
   public void setAlive(boolean temp){
       alive = temp;
   }

}
