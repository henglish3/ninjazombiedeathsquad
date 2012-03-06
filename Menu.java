/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ninjazombiedeathsquad;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font.*;
import java.awt.GraphicsEnvironment;
import java.awt.*;

import java.awt.font.*;


/**
 *
 * @author brianrobbins
 * Creates the game's menus which are drawn onto the global
 * menuFrame JFrame object. As user navigates through the
 * menu, the different menu panels are shown
 * and hidden corresponding to user actions.
 */
public class Menu  {
    //global frame that switches between menus
    JFrame menuFrame;
    //static integers used to determine which player and enemy is chosen by the user
    //to draw onto the level.
    //playerChosen is defaulted to 0.
    public static int playerChosen = 0;
    //enemyChosen is defaulted to 3.
    public static int enemyChosen = 3;
    private static final boolean debug = true;

    //varibale declaration for sound
    final static Sound enemyAMusic = new Sound();
    final static Sound enemyBMusic = new Sound();
    final static Sound enemyCMusic = new Sound();
    final static Sound enemyDMusic = new Sound();
    final static Sound enemyEMusic = new Sound();


    /***
     * Creates the Menu Object which contains all the panels
     * that display all the different menus that the user
     * will navigate through.
     */
    Menu(){

        //sets up frame
        menuFrame = new JFrame();
        menuFrame.setSize(640,500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creates menu toolbar for menuFrame.
        JMenuBar mainBar = new JMenuBar();
        //creates appropriate menu and menu items.
        JMenu help = new JMenu("Help");
        JMenuItem instructions = new JMenuItem("How to Play");
        //action listener for selecting instructions.
        Action helpListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent aEvent)
            {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame,
                "Controls to the "
                        + "NinjaZombieDeathSquad game are quite simple.\n "
                        + " :: 'space bar' Key :: \nCauses player to jump to avoid"
                        + " obstacles and pitfalls.  \n" +
                        "Pressing it twice in succession causes a double jump. \n"
                        + " :: 'p' Key :: \n Toggles the pause mode on and off.\n", 
                        "Game Help!", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        //attaching listener
        instructions.addActionListener(helpListener);
        //adding the instruction item to help.
        help.add(instructions);
        //addes help item to mainBar
        mainBar.add(help);
        //sets menuFrame's menu bar to mainBar
        menuFrame.setJMenuBar(mainBar);


        //mainMenu panel
        final MenuPanel mainMenu = new MenuPanel("NZDS(Menu).jpg");
        //invisible buttons declared
        final InvisibleButton play = new InvisibleButton();
        final InvisibleButton chooseEnemy = new InvisibleButton();
        final InvisibleButton choosePlayer = new InvisibleButton();
        final InvisibleButton highScores = new InvisibleButton();
        final InvisibleButton quit = new InvisibleButton();

      

        //Action listerner for the different menu buttons
        ActionListener menuButtons = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //block for play button
                if (e.getSource().equals(play)){
                    Move.setPace(1000);
                    new NinjaZombieDeathSquad();
                    menuFrame.dispose();

                //block for choose enemy
                } else if (e.getSource().equals(chooseEnemy)){

                  /* Character enemy1 = new Character();
                    try{draw}
                    catch(Exception p){}*/
                     mainMenu.setVisible(false);

                     //makes scorewMenu panel
                     final MenuPanel enemyMenu = new MenuPanel("ChooseEnemy.jpg");
                     final JButton back = new JButton("Back");
                     final InvisibleButton enemyA = new InvisibleButton();
                     final InvisibleButton enemyB = new InvisibleButton();
                     final InvisibleButton enemyC = new InvisibleButton();
                     final InvisibleButton enemyD = new InvisibleButton();
                     final InvisibleButton enemyE = new InvisibleButton();



                     //add buttons
                     enemyMenu.add(back);
                     enemyMenu.add(enemyA);
                     enemyMenu.add(enemyB);
                     enemyMenu.add(enemyC);
                     enemyMenu.add(enemyD);
                     enemyMenu.add(enemyE);
                     enemyMenu.setLayout(null);
                     back.setBounds(300,300, 75, 50);
                     enemyA.setBounds(5, 10, 200, 200);
                     enemyB.setBounds(215, 10, 200, 200);
                     enemyC.setBounds(425, 10, 200, 200);
                     enemyD.setBounds(5, 260, 200, 200);
                     enemyE.setBounds(425, 260, 200, 200);

                     //action listener for back button and enemy buttons.
                     ActionListener highButtons = new ActionListener()
                         {
                           public void actionPerformed(ActionEvent e)
                            {
                             //back button block hides enemyMenu, shows mainMenu.
                             if(e.getSource().equals(back)){
                                 enemyMenu.setVisible(false);
                                 mainMenu.setVisible(true);

                                }
                             //enemyA button block
                             else if(e.getSource().equals(enemyA)){

                                if (debug)
                                    System.out.println("Enemy 1 Chosen");
                               //sets enemyChosen to 2.
                               enemyChosen = 2;
                               //makes enemyA appear to be in a jump state
                               //while setting the rest of the enemy to a nonjump
                               //state
                               enemyMenu.enemyA1.setJumpState(1);
                               enemyMenu.enemyB1.setJumpState(0);
                               enemyMenu.enemyC1.setJumpState(0);
                               enemyMenu.enemyD1.setJumpState(0);
                               enemyMenu.enemyE1.setJumpState(0);

                             //initiate enemyA game sound.
                               enemyAMusic.enemy1Sound();
                               enemyBMusic.enemy2SoundStop();
                               enemyCMusic.enemy3SoundStop();
                               enemyDMusic.enemy4SoundStop();
                               enemyEMusic.enemy5SoundStop();
                                            
                              //repaints the menu to draw the appropriate enemy
                              //pictures.
                               enemyMenu.repaint();
                                }

                             //enemyB button block
                             else if(e.getSource().equals(enemyB)){
                                 if (debug)
                                    System.out.println("Enemy 2 Chosen");

                               //sets enemyChosen to 3.
                               enemyChosen = 3;
                               //makes enemyB appear to be in a jump state
                               //while setting the rest of the enemy to a nonjump
                               //state
                               enemyMenu.enemyA1.setJumpState(0);
                               enemyMenu.enemyB1.setJumpState(1);
                               enemyMenu.enemyC1.setJumpState(0);
                               enemyMenu.enemyD1.setJumpState(0);
                               enemyMenu.enemyE1.setJumpState(0);

                               //initiate enemyB game sound.
                               enemyBMusic.enemy2Sound();
                               enemyAMusic.enemy1SoundStop();
                               enemyCMusic.enemy3SoundStop();
                               enemyDMusic.enemy4SoundStop();
                               enemyEMusic.enemy5SoundStop();

                               //repaints the menu panel to draw the appropriate
                               //enemy pictures.
                               enemyMenu.repaint();
                                }

                             //enemyC button block
                             else if(e.getSource().equals(enemyC)){
                                 if (debug)
                                    System.out.println("Enemy 3 Chosen");
                               //sets enemyChosen to 4.
                                enemyChosen = 4;
                               //makes enemyC appear to be in a jump state
                               //while setting the rest of the enemy to a nonjump
                               //state
                               enemyMenu.enemyA1.setJumpState(0);
                               enemyMenu.enemyB1.setJumpState(0);
                               enemyMenu.enemyC1.setJumpState(1);
                               enemyMenu.enemyD1.setJumpState(0);
                               enemyMenu.enemyE1.setJumpState(0);

                               //initiate enemyC game sound.
                               enemyCMusic.enemy3Sound();
                               enemyAMusic.enemy1SoundStop();
                               enemyBMusic.enemy2SoundStop();
                               enemyDMusic.enemy4SoundStop();
                               enemyEMusic.enemy5SoundStop();
                              
                               //repaints the menu panel to draw the appropriate
                               //enemy pictures.
                               enemyMenu.repaint();
                                }

                             else if(e.getSource().equals(enemyD)){
                                 if (debug)
                                    System.out.println("Enemy 4 Chosen");
                               //sets enemyChosen to 5.
                                enemyChosen = 5;
                               //makes enemyD appear to be in a jump state
                               //while setting the rest of the enemy to a nonjump
                               //state
                               enemyMenu.enemyA1.setJumpState(0);
                               enemyMenu.enemyB1.setJumpState(0);
                               enemyMenu.enemyC1.setJumpState(0);
                               enemyMenu.enemyD1.setJumpState(1);
                               enemyMenu.enemyE1.setJumpState(0);

                               //initiate enemyD game sound.

                               enemyDMusic.enemy4Sound();
                               enemyAMusic.enemy1SoundStop();
                               enemyBMusic.enemy2SoundStop();
                               enemyCMusic.enemy3SoundStop();
                               enemyEMusic.enemy5SoundStop();
                               

                               //repaints the menu panel to draw the appropriate
                               //enemy pictures.
                               enemyMenu.repaint();
                                }

                             //enemyE button block
                             else if(e.getSource().equals(enemyE)){
                                if (debug)
                                    System.out.println("Enemy 5 Chosen");
                                //sets enemyChosen to 6.
                                enemyChosen = 6;
                               //makes enemyE appear to be in a jump state
                               //while setting the rest of the enemy to a nonjump
                               //state
                                enemyMenu.enemyA1.setJumpState(0);
                                enemyMenu.enemyB1.setJumpState(0);
                                enemyMenu.enemyC1.setJumpState(0);
                                enemyMenu.enemyD1.setJumpState(0);
                                enemyMenu.enemyE1.setJumpState(1);

                                //initiate enemyE game sound.
                               enemyEMusic.enemy5Sound();
                               enemyAMusic.enemy1SoundStop();
                               enemyBMusic.enemy2SoundStop();
                               enemyCMusic.enemy3SoundStop();
                               enemyDMusic.enemy4SoundStop();
                               


                               //repaints the menu panel to draw the appropriate
                               //enemy pictures.
                                enemyMenu.repaint();
                                }
                           }
                         };
                    //attackes the highButtons actionListener to the buttons
                    back.addActionListener(highButtons);
                    enemyA.addActionListener(highButtons);
                    enemyB.addActionListener(highButtons);
                    enemyC.addActionListener(highButtons);
                    enemyD.addActionListener(highButtons);
                    enemyE.addActionListener(highButtons);

                    menuFrame.add(enemyMenu);
                    if (debug)
                    System.out.println("Choose Enemy !!");



                //block for choose player
                } else if (e.getSource().equals(choosePlayer)){
                    //hide mainMenu
                    mainMenu.setVisible(false);
                    //create a playerMenu with playerscreen image
                    final MenuPanel playerMenu = new MenuPanel("PlayerScreen.jpg");
                    final InvisibleButton male = new InvisibleButton();
                    final InvisibleButton female = new InvisibleButton();

                    //back button to go back to main menu.
                    final JButton back = new JButton("Back");

                    //adding button to panel
                    playerMenu.add(back);
                    playerMenu.add(male);
                    playerMenu.add(female);
                    playerMenu.setLayout(null);
                    back.setBounds(550, 400, 75, 50);
                    male.setBounds(5, 250, 205, 450);
                    female.setBounds(190, 250, 390, 450);

                    //action listener for back button and choosing player buttons
                    ActionListener playerButtons = new ActionListener()
                         {
                           public void actionPerformed(ActionEvent e)
                            {
                             //block for back button
                             if(e.getSource().equals(back)){
                                 playerMenu.setVisible(false);
                                 mainMenu.setVisible(true);

                                }
                             //block for male button
                             else if(e.getSource().equals(male)){
                                if (debug)
                                 System.out.println("male chosen");
                                playerChosen =0;
                                playerMenu.male.setJumpState(1);
                                playerMenu.female.setJumpState(0);
                                playerMenu.repaint();
                             }
                             //block for female button
                             else if (e.getSource().equals(female)){
                                 if (debug)
                                    System.out.println("female chosen");
                                 playerChosen = 1;
                                 playerMenu.male.setJumpState(0);
                                 playerMenu.female.setJumpState(1);
                                 playerMenu.repaint();
                             }

                            }
                         };

                    //attaching action listener to buttons.
                    back.addActionListener(playerButtons);
                    male.addActionListener(playerButtons);
                    female.addActionListener(playerButtons);


                    menuFrame.add(playerMenu);
                    if (debug)
                    System.out.println("Choose Player !!");

                //block for high score button
                }else if (e.getSource().equals(highScores)){
                     //hide mainMenu
                     mainMenu.setVisible(false);
                     //makes scorewMenu panel
                     final MenuPanel scoresMenu = new MenuPanel("HighScores.jpg");
                     final JButton back = new JButton("Back");
                     //add buttons
                     scoresMenu.add(back);
                     scoresMenu.setLayout(null);
                     back.setBounds(350,300, 75, 50);


                     //action listener for back button.
                     ActionListener highButtons = new ActionListener()
                         {
                           public void actionPerformed(ActionEvent e)
                            {
                             if(e.getSource().equals(back)){
                                 scoresMenu.setVisible(false);
                                 mainMenu.setVisible(true);

                                }
                            }
                         };
                     //attaching action listener to back button
                     back.addActionListener(highButtons);

                     menuFrame.add(scoresMenu);
                     if (debug)
                     System.out.println("high scores !!");

                //block for quit button
                } else if (e.getSource().equals(quit)){
                    if (debug)
                    System.out.println("quit !!");
                    System.exit(1);
                }
            }
        };

        //attaching menuButtons actionlistener to all the buttons
        play.addActionListener(menuButtons);
        chooseEnemy.addActionListener(menuButtons);
        choosePlayer.addActionListener(menuButtons);
        highScores.addActionListener(menuButtons);
        quit.addActionListener(menuButtons);

        //adding buttons to panel
        mainMenu.add(play);
        mainMenu.add(chooseEnemy);
        mainMenu.add(choosePlayer);
        mainMenu.add(quit);
        mainMenu.add(highScores);

        mainMenu.setLayout(null);
        //placing buttons
        play.setBounds(260, 330, 120, 80);
        choosePlayer.setBounds(30, 290, 190, 50);
        chooseEnemy.setBounds(430, 290, 190, 50);
        highScores.setBounds(30, 355, 190, 50);
        quit.setBounds(430, 355, 190, 50);

        //adds to menuFrame
        menuFrame.add(mainMenu);
        menuFrame.setResizable(false);
        menuFrame.setVisible(true);


    }
}
