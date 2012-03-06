/* Ninja Zombie Death Squad
 *
 * Mark Daniel Gibbons
 * Harrison English
 */
package ninjazombiedeathsquad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Creates a new game frame and puts a
 * DrawingPanel inside of it. Instantiates a Level
 * class and links the DrawingPanel and Level classes
 * to properly draw the DrawableObject instances.
 */
public class NinjaZombieDeathSquad {
    Thread dpThread;
    JFrame frame;
    DrawingPanel dp;
    /* TODO: Move all debug buttons to DrawingPanel so that
     * the display score and display time state buttons work properly.
     */
    boolean showButtons;
    boolean displayScore=true;
    boolean displayTime=true;

    NinjaZombieDeathSquad() {
        // Outer frame
        frame = new JFrame();
        frame.setSize(1024, 590);

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initDrawableArea();



        frame.setVisible(true);
    }

    private void initDrawableArea() {
        // Put drawable area in content pane
        DrawingPanel drawableArea = new DrawingPanel(displayScore, displayTime, frame);
        dp = drawableArea;
        drawableArea.setBackground(Color.WHITE);
        Container contentPane = frame.getContentPane();
        contentPane.add(drawableArea);
        dpThread = new Thread(drawableArea);
        dpThread.start();
        addButtons();
    }

    private void addButtons() {
        Action catchUpListener = new AbstractAction() {

            public void actionPerformed(ActionEvent aEvent) {
                dp.getLevel().kill();
                dp.requestFocus();
            }
        };
        Action musicListener = new AbstractAction() {

            public void actionPerformed(ActionEvent aEvent) {
                // TODO: Change Music state
                if(!dp.getPlayer().isAlive()){
                    new NinjaZombieDeathSquad();
                }
                dp.requestFocus();
            }
        };
        Action soundFXlistener = new AbstractAction() {
        
            public void actionPerformed(ActionEvent aEvent){

                dp.requestFocus(); 
            }
        };

        Action jumpListener = new AbstractAction() {

            public void actionPerformed(ActionEvent aEvent) {
                dp.getLevel().jump(dp.getPlayer());
                dp.requestFocus();
            }
        };

        Action scoreStateListener = new AbstractAction() {

            public void actionPerformed(ActionEvent aEvent) {
                if (displayScore)
                {
                    displayScore=false;
                    dp.setDisplayScore(false);
                }
                else
                {
                    displayScore=true;
                    dp.setDisplayScore(true);
                }
                dp.requestFocus();
            }
        };

        Action timeStateListener = new AbstractAction() {

            public void actionPerformed(ActionEvent aEvent) {
                if (displayTime)
                {
                    displayTime=false;
                    dp.setDisplayTime(false);
                }
                else
                {
                    displayTime=true;
                    dp.setDisplayTime(true);
                }
                dp.requestFocus();
            }
           };

        // Enemy catch up button
        final JButton catchUpButton = new JButton("Kill Player");
        catchUpButton.addActionListener(catchUpListener);

        // Music button
        final JButton musicButton = new JButton("Change Music State");
        musicButton.addActionListener(musicListener);

        // Sound FX button
        final JButton soundFXbutton = new JButton("Change Sound FX State");
        soundFXbutton.addActionListener(soundFXlistener);

        // Jump Button
        final JButton jumpButton = new JButton("Jump");
        jumpButton.addActionListener(jumpListener);

        // Jump Button
        final JButton scoreStateButton = new JButton("Change score display");
        scoreStateButton.addActionListener(scoreStateListener);

        // Jump Button
        final JButton timeStateButton = new JButton("Change time display state");
        timeStateButton.addActionListener(timeStateListener);


        showButtons = false;
        catchUpButton.setVisible(false);
        musicButton.setVisible(false);
        soundFXbutton.setVisible(false);
        jumpButton.setVisible(false);
        scoreStateButton.setVisible(false);
        timeStateButton.setVisible(false);
        
        displayScore=true;
        displayTime=true;
        
        Action debugListener = new AbstractAction() {

            public void actionPerformed(ActionEvent aEvent) {
                if (showButtons) {
                    catchUpButton.setVisible(false);
                    musicButton.setVisible(false);
                    soundFXbutton.setVisible(false);
                    jumpButton.setVisible(false);
                    scoreStateButton.setVisible(false);
                    timeStateButton.setVisible(false);
                    showButtons = false;
                } else {
                    catchUpButton.setVisible(true);
                    musicButton.setVisible(true);
                    soundFXbutton.setVisible(true);
                    jumpButton.setVisible(true);
                    scoreStateButton.setVisible(true);
                    timeStateButton.setVisible(true);
                    showButtons = true;
                }
               // dp.requestFocus();
            }
        };


        dp.setLayout(null);

        // Add buttons
        dp.add(catchUpButton);
        dp.add(soundFXbutton);
        dp.add(musicButton);
        dp.add(timeStateButton);
        dp.add(scoreStateButton);
        dp.add(jumpButton);

        // Position buttons
        Insets i = dp.getInsets();
        Dimension s = catchUpButton.getPreferredSize();
        catchUpButton.setBounds(100 + i.left, 10 + i.top, s.width, s.height);

        s = jumpButton.getPreferredSize();
        jumpButton.setBounds(100 + i.left, 50 + i.top, s.width, s.height);

        s = musicButton.getPreferredSize();
        musicButton.setBounds(210 + i.left, 10 + i.top, s.width, s.height);

        s = soundFXbutton.getPreferredSize();
        soundFXbutton.setBounds(210 + i.left, 50 + i.top, s.width, s.height);

        s = scoreStateButton.getPreferredSize();
        scoreStateButton.setBounds(410 + i.left, 10 + i.top, s.width, s.height);

        s = timeStateButton.getPreferredSize();
        timeStateButton.setBounds(410 + i.left, 50 + i.top, s.width, s.height);

        //creates the menu bar
        JMenuBar mainBar = new JMenuBar();

        //create different the different menus.
        JMenu file = new JMenu("File");
        JMenu options = new JMenu("Options");
        JMenu music = new JMenu("Music");
        JMenu toggleMusic = new JMenu("Toggle Music");
        JMenu changeMusic = new JMenu("Change Music");

        //creates menu items used in the menus
        JMenuItem quit = new JMenuItem("Quit Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem returnMenu = new JMenuItem("Return to Main Menu");
        JCheckBoxMenuItem debugMode = new JCheckBoxMenuItem("Debug Mode");

        //action listener for quitting game.
        Action quitGameListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent aEvent)
            {
             //exits the program completely.
             System.exit(1);
            }
        };

        //action listener for starting a new game.
        Action newGameListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent aEvent)
            {
             //sets pause variable to false so game is unpaused on restart.
             Move.pause = false;
             //disposes current gameFrame
             Level.gameFrame.dispose();
             //sets pace back to 1000 for new game.
             Move.setPace(1000);
             //calls new game object.
             new NinjaZombieDeathSquad();
            }
        };

        //action listener for going back to the menu.
        Action returnMenuListener = new AbstractAction()
        {
            public void actionPerformed(ActionEvent aEvent)
            {
             //sets pause variable back to false.
             Move.pause = false;
             //disposes current gameFrame
             Level.gameFrame.dispose();
             //reset pace to 1000
             Move.setPace(1000);
             //start menu back up
             new Menu();
            }
        };
        

        //attaches items to appropriate listeners.
        debugMode.addActionListener(debugListener);
        quit.addActionListener(quitGameListener);
        newGame.addActionListener(newGameListener);
        returnMenu.addActionListener(returnMenuListener);

        //create radiobutton menu items for music.
        final JRadioButtonMenuItem enemy1Music = new JRadioButtonMenuItem("Enemy 1 Music");
        final JRadioButtonMenuItem enemy2Music = new JRadioButtonMenuItem("Enemy 2 Music");
        final JRadioButtonMenuItem enemy3Music = new JRadioButtonMenuItem("Enemy 3 Music");
        final JRadioButtonMenuItem enemy4Music = new JRadioButtonMenuItem("Enemy 4 Music");
        final JRadioButtonMenuItem enemy5Music = new JRadioButtonMenuItem("Enemy 5 Music");
      
        //create action listener for switching music playing
        Action switchMusic = new AbstractAction()
        {
            public void actionPerformed(ActionEvent aEvent)
            {
                //block for enemy1
                if (aEvent.getSource().equals(enemy1Music)){
                     Menu.enemyAMusic.enemy1Sound();
                     Menu.enemyBMusic.enemy2SoundStop();
                     Menu.enemyCMusic.enemy3SoundStop();
                     Menu.enemyDMusic.enemy4SoundStop();
                     Menu.enemyEMusic.enemy5SoundStop();
                //block for enemy2
                } else if (aEvent.getSource().equals(enemy2Music)){
                     Menu.enemyBMusic.enemy2Sound();
                     Menu.enemyAMusic.enemy1SoundStop();
                     Menu.enemyCMusic.enemy3SoundStop();
                     Menu.enemyDMusic.enemy4SoundStop();
                     Menu.enemyEMusic.enemy5SoundStop();
               //block for enemy3
                } else if (aEvent.getSource().equals(enemy3Music)){
                     Menu.enemyCMusic.enemy3Sound();
                     Menu.enemyAMusic.enemy1SoundStop();
                     Menu.enemyBMusic.enemy2SoundStop();
                     Menu.enemyDMusic.enemy4SoundStop();
                     Menu.enemyEMusic.enemy5SoundStop();
                //block for enemy4
                } else if (aEvent.getSource().equals(enemy4Music)){
                     Menu.enemyDMusic.enemy4Sound();
                     Menu.enemyAMusic.enemy1SoundStop();
                     Menu.enemyBMusic.enemy2SoundStop();
                     Menu.enemyCMusic.enemy3SoundStop();
                     Menu.enemyEMusic.enemy5SoundStop();
                //block for enemy5
                } else if (aEvent.getSource().equals(enemy5Music)){
                     Menu.enemyEMusic.enemy5Sound();
                     Menu.enemyAMusic.enemy1SoundStop();
                     Menu.enemyBMusic.enemy2SoundStop();
                     Menu.enemyCMusic.enemy3SoundStop();
                     Menu.enemyDMusic.enemy4SoundStop();
                }
            }
                    
        };

        //attach enemy menu items to swithMusic action listener
        enemy1Music.addActionListener(switchMusic);
        enemy2Music.addActionListener(switchMusic);
        enemy3Music.addActionListener(switchMusic);
        enemy4Music.addActionListener(switchMusic);
        enemy5Music.addActionListener(switchMusic);

        //create radiobuttons for music mute and unmute
        final JRadioButtonMenuItem muteMusic = new JRadioButtonMenuItem("Quit Music");
        final JRadioButtonMenuItem resumeMusic = new JRadioButtonMenuItem("Start Music");

        //actionListener for stopping and starting music.
        Action stopMusic = new AbstractAction()
        {
          public void actionPerformed(ActionEvent aEvent)
          {
             //block for mute item
             if(aEvent.getSource().equals(muteMusic)){
               Menu.enemyAMusic.enemy1SoundStop();
               Menu.enemyBMusic.enemy2SoundStop();
               Menu.enemyCMusic.enemy3SoundStop();
               Menu.enemyDMusic.enemy4SoundStop();
               Menu.enemyEMusic.enemy5SoundStop();
              }
             //block for resume item
            else {
               Menu.enemyAMusic.enemy1Sound();
               Menu.enemyBMusic.enemy2SoundStop();
               Menu.enemyCMusic.enemy3SoundStop();
               Menu.enemyDMusic.enemy4SoundStop();
               Menu.enemyEMusic.enemy5SoundStop();
            }
               
          }
        };

        //attaches muteMusic to stopMusic action listener
        muteMusic.addActionListener(stopMusic);
        resumeMusic.addActionListener(stopMusic);

        //creates button group for music toggling
        ButtonGroup muteToggle = new ButtonGroup();
        muteToggle.add(muteMusic);
        muteToggle.add(resumeMusic);

        //creates button group for music selection.
        ButtonGroup musicToggle = new ButtonGroup();
        musicToggle.add(enemy1Music);
        musicToggle.add(enemy2Music);
        musicToggle.add(enemy3Music);
        musicToggle.add(enemy4Music);
        musicToggle.add(enemy5Music);

        //attach appropriate items to toggleMusic button group
        toggleMusic.add(muteMusic);
        toggleMusic.add(resumeMusic);

        //attach appropriate items to changeMusic button group
        changeMusic.add(enemy1Music);
        changeMusic.add(enemy2Music);
        changeMusic.add(enemy3Music);
        changeMusic.add(enemy4Music);
        changeMusic.add(enemy5Music);

        //adds menus to music Menu
        music.add(toggleMusic);
        music.add(changeMusic);

        //adds menus to the File menu
        file.add(quit);
        file.add(newGame);
        file.add(returnMenu);
        file.add(debugMode);

        //add music menu
        options.add(music);

        //adds the menu to the mainBar
        mainBar.add(file);
        mainBar.add(options);

        //sets the mainBar as the frames menubar.
        frame.setJMenuBar(mainBar);

    }

    /* Main method for program
     * Creates the menu.
     */
    public static void main(String[] args) {
        //  new NinjaZombieDeathSquad();
        try{
            new Menu();
        }
        catch (Exception e)
        {

        }
    }
}
