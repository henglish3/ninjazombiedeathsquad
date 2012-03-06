package ninjazombiedeathsquad;

import javax.swing.*;
import java.awt.*;
import java.awt.Font.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *
 * MenuPanel class is an extension of the JPanel component
 * that overrides the paintComponent method to directly
 * display a background onto the panel. This allows for an easy
 * way to display background images for the game's menus.
 *
 */

public class MenuPanel extends JPanel{
  //private image variable to set a background image to in order
  //paint the image onto the Panel.
  private Image image;
  //integer used to test weather or not the certain menu's are being shown
  //in order to draw our enemy and player images onto the menu as well as
  //the font images used to display the high scores.
  int tester = 0;
  //player objects used to draw onto the choosePlayer menu for user to pick.
  Player male = new Player(5, 250, 200, 0);
  Player female = new Player(190, 250, 200, 1);
  Player enemyA1 = new Player(5, 10, 200, 2);
  //character objects used to draw onto the chooseEnemy menu for user to pick.
  Character enemyB1 = new Character(215, 10, 200, 3);
  Character enemyC1 = new Character(425, 10, 200, 4);
  Character enemyD1 = new Character(5, 260, 200, 5);
  Character enemyE1 = new Character(425, 260, 200, 6);

  /**
   * Creates The MenuPanel objects, draws appropriate backgrounds and images
   * depending on what Menu is being displayed. Draws directly to the panel.
   * is used to test which menu is being displayed, and draws the 
   * appropriate images on top of the background. menu chosen is kept track of 
   * with the global variable tester. Uses parameter to determine the path 
   * of the image that is being drawn on the background.
   *
   * @param i String variable that holds the directory path of the image being
   * used.
   *
   */

  public MenuPanel(String i)
  {
    //test if the ChooseEnemy menu is being displayed, if so sets tester to 1.
    if (i.equals("ChooseEnemy.jpg")){
        tester = 1;
    //test if the PlayerScreen menu is being displayed, if so sets tester to 2.
    } else if(i.equals("PlayerScreen.jpg")){
        tester = 2;
    //test if the HighScores menu is being displayed, if so sets tester to 3.
    } else if (i.equals("HighScores.jpg")){
        tester = 3;
    }
    //if menu chosen is neither of those, tester is set to 0.
    else{
        tester = 0;
    }
    //takes i parameter and uses it to set image to the image located at the path
    //i holds.
    try
    {
      image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(i), i));
    }
    catch (Exception e) { /*handled in paintComponent()*/ }
    }




  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D gr;
    gr = (Graphics2D) g;
    //draws the image onto the Panel.
    if (image != null)
      g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
    //if tester is set to 1, chooseEnemy menu has been displayed, and enemy objects
    //are drawn onto the panel for user to choose.
    if (tester == 1){
      g.drawImage(enemyA1.getImg(), enemyA1.getX(), enemyA1.getY(), this);
      g.drawImage(enemyB1.getImg(), enemyB1.getX(), enemyB1.getY(), this);
      g.drawImage(enemyC1.getImg(), enemyC1.getX(), enemyC1.getY(), this);
      g.drawImage(enemyD1.getImg(), enemyD1.getX(), enemyD1.getY(), this);
      g.drawImage(enemyE1.getImg(), enemyE1.getX(), enemyE1.getY(), this);
    }
    //if tester is set to 2, PlayerSelect menu has been displayed, and the two
    //player objects are drawn onto the panel for user to choose.
    if (tester ==2)
    {
      g.drawImage(male.getImg(), male.getX(), male.getY(), this);
      g.drawImage(female.getImg(), female.getX(), female.getY(), this);
    }
    if (tester == 3){
                     HighScore hs = new HighScore();
                     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                     ge.getAllFonts();

                FontRenderContext frc = gr.getFontRenderContext();
                int k = 0;
                  for (int i = 0; i < hs.scores.length; i++ ){
                        Font fs = new Font("chiller", Font.BOLD, 24);
                         System.out.print(hs.scores[i].getName());
                         System.out.println(hs.scores[i].getScore());
                         TextLayout tl = new TextLayout(hs.scores[i].getName() + " : " + hs.scores[i].getScore(), fs, frc);
                         gr.setColor(Color.white);
                         //draws the string
                         tl.draw(gr, 20, 100+k);
                         k = k + 30;
                     }
               
             //   TextLayout tl = new TextLayout("THIS IS JUST A TEST", fs, frc);
                

    }
  }

}
