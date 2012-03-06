

package ninjazombiedeathsquad;
/**
 *
 * @author harrisonenglish
 *
 * Obstacle class
 * carries all the stats of the obstacle
 *
 * Public methods:
 * boolean isHit()
 *  tells if a character is hit by an obstacle
 * void setHit()
 *  sets the hit boolean to true
 * int getType()
 *  returns the type of obstacle
 * int setType(int n)
 * sets which type of obstacle
 */
public class Obstacle extends DrawableObject {
    private boolean hit = false;

    //type 0 is a auto-kill obstacle
    //type 1 is a slow down obstacle
    //type 2 is not an obstacle, it's used for backgrounds, etc.
    private int oType;

    /**
     * creates an instance of the obstacle
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param type
     * @param oType
     */
    Obstacle(int x, int y, int width, int height, int type, int oType)
    {     
      super(x,y,width,type);
      this.oType = oType;
    }

    /**
     * retuns true if an obstacle hits a person.
     * @return hit
     */
    public boolean isHit(){
        return hit;
    }
    /**
     * sets the hit variable
     * @param bang
     */
    public void setHit(boolean bang){
        hit = bang;
    }
    /**
     * returns the type of obstacle
     * @return oType the obstacle type
     */
    public int getType(){
        return oType;
    }
    /**
     * allows the obstace type to be set
     * @param how the type of obstacle
     */
    public void setType(int how){
        oType = how;
    }

}
