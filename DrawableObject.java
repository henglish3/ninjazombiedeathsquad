
package ninjazombiedeathsquad;
import java.awt.image.*;

/**
 * @author Cody Kraatz
 * DrawableObject class
 * Handles drawing the different objects.
 * Gets the location and type of image to be drawn.
 *
 * Public methods:
 * DrawableObject(int hor, int vert, int fat, int type)
 *  Sets the x and y values of the images top left corner,
 *  sets the width of the image and witch image is to be drawn
 * String getFile(int type)
 *  returns the name of the .gif file associated with the type given
 * BufferedImage getImg()
 *  returns img
 * void move(int hor, int vert)
 *  moves the image to the given spot
 * void setX(int val)
 *  sets the x coordinate
 * void setY(int val)
 *  sets the y coordinate
 * int getX()
 *  returns the x coordinate
 * int getY()
 *  returns the y coordinate
 * int getTopeEdge()
 *  returns y value of top of the picture
 * int getBottomeEdge()
 *  returns y value of the bottom of the picture
 * int getLeftEdge()
 *  returns x value of the left side of the picture
 * int getRightEdge()
 *  returns the x value of the right side of the picture
 *
 */


public class DrawableObject {
private double x;
private double y;
private double width;
private double height;
private BufferedImage img;

/**
 * Give information for the object to be drawn.
 *
 * @param hor Horizontal location for the object.
 * @param vert Vertical location for the object.
 * @param fat Width of the object.
 * @param type  Picture type of the object.
 */
  public DrawableObject(int hor, int vert, int fat,int type){
       x= hor;
       y = vert;
       width = fat;

       try{
          img = javax.imageio.ImageIO.read(new java.net.URL
                  (getClass().getResource(getFile(type)), getFile(type)));
       }
       catch (Exception e) { /*handled in paintComponent()*/ }
       height=img.getHeight();
   }
  /**
   * Returns the name of the file to be drawn.
   *
   * @param type Number associated with the picture to be drawn.
   * @return Name of the file to be drawn.
   */
  public String getFile(int type){
     switch(type){
         case 0: return "players.gif";
         case 1: return "player2s.gif";
         case 2: return "zombiedaniels.gif";
         case 3: return "zombieharrisons.gif";
         case 4: return "zombiecodys.gif";
         case 5: return "zombiebrians.gif";
         case 6: return "zombiecourtneys.gif";
         case 7: return "tree.gif";
         case 8: return "blankPaper.jpg";
         case 9: return "bloodspat.gif";
         case 10: return "pitfall.gif";
         case 11: return "mathobs1.gif";
         case 12: return "mathobs2.gif";
         default: return "players.gif";
     }
 }
/**
 * Returns img.
 *
 * @return BufferedImage img
 */
public BufferedImage getImg(){
    return img;
}
/**
 * Sets the x and y values. Moves the object.
 *
 * @param hor x value of the object
 * @param vert y value of the object
 */
public void move(int hor, int vert){
    x = hor;
    y = vert;
}
/**
 * Set the x value of the image.
 *
 * @param val Value for the x location.
 */
 public void setX(int val)
 {
     x=(double)val;
 }
 /**
  * Set the y value of the image.
  *
  * @param val Value for the y location.
  */
  public void setY(int val)
 {
     y=(double)val;
 }
  /**
   * Returns the x location.
   *
   * @return x value of the image.
   */
public int getX(){
    return (int)x;
}
/**
 * Returns the y location.
 *
 * @return y value of the image.
 */
public int getY(){
    return (int)y;
}
/**
 * Returns the y value of the top of the image.
 *
 * @return y value of the top of the image.
 */
public int getTopEdge(){
    return (int)(y);
}
/**
 * Returns the y value of the bottom of the image.
 *
 * @return y value of the bottom of the image.
 */
public int getBottomEdge(){
    return (int)(y + height);
}
/**
 * Returns the x value of the left edge of the image.
 *
 * @return x value of the left edge of the image.
 */
public int getLeftEdge(){
    return (int)x;
}
/**
 * Returns the x value of the right edge of the image.
 *
 * @return x value of the right edge of the image.
 */
public int getRightEdge(){
    return (int)(x + width);
    }
}
