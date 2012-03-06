package ninjazombiedeathsquad;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.media.*;
import java.io.*;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author Courtney
 */
public class Sound {
/*
    javax.media.Player enemy1Music;
    javax.media.Player enemy2Music;
    javax.media.Player enemy3Music;
    javax.media.Player enemy4Music;
    javax.media.Player enemy5Music;
    javax.media.Player endGame;

    boolean enemy1Audio = false;
    boolean enemy2Audio = false;
    boolean enemy3Audio = false;
    boolean enemy4Audio = false;
    boolean enemy5Audio = false;
*/

    /**
     * methods for calling enemy1 music, when this method is called it
     * reads the .mp3 file given and changes the file into the java media
     * player Manager method which converts the file into playable.
     */
    public void enemy1Sound() {
/*
        try {
            File musicAudio = new File(new URI(new java.net.URL(getClass().getResource("player4.mp3"), "player4.mp3").toString()));
            MediaLocator clip = new MediaLocator(musicAudio.toURL());
            enemy1Music = Manager.createPlayer(clip);
            enemy1Start();

        } catch (MalformedURLException e) {
            System.err.println("Error");
        } catch (IOException e) {
            System.err.println("Error");
        } catch (NoPlayerException e) {
            System.err.println("Error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
*/
    }
    /**
     * Starts enemyA music. During this time it checks the boolean value
     *of enemy1Start and if it is equal to false the music does nothing
     * if the value is true then the music stops playing.
     */
    void enemy1Start() {
        /*
        if(enemy1Audio == false)
        {
            enemy1Music.start();
            enemy1Audio=true;
        }
        System.out.println("music for enemy 1 playing");
         */
    }
    /**
     * Method for stopping the enemy1 music
     */
    void enemy1Stop() {
        //enemy1Music.stop();
    }
    /**
     * Method for stopping the music of enemy1.
     */

    /**method is used to stop the music for enemyA. It has a boolean
     *value to check the state of the sound playing, it it is true
     *then the music is stopped for enemyA */
    public void enemy1SoundStop() {
        /*
        if(enemy1Audio==true){
          enemy1Music.stop();
          enemy1Audio=false;
          System.out.println("Enemy 1 music has stopped");
        }
       */
    }

    /**
     * methods for calling enemy2 music,when this method is called it
     * reads the .mp3 file given and changes the file into the java media
     * player Manager method which converts the file into playable.
     */
    public void enemy2Sound() {
        /*
        try {
            File enemy2Sound = new File(new URI(new java.net.URL(getClass().getResource("player2.mp3"), "player2.mp3").toString()));
            MediaLocator clip = new MediaLocator(enemy2Sound.toURL());
            enemy2Music = Manager.createPlayer(clip);
            enemy2Start();

        } catch (MalformedURLException e) {
            System.err.println("Error");
        } catch (IOException e) {
            System.err.println("Error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         *
         */
    }
    /**
     * Starts enemyB music. During this time it checks the boolean value
     *of enemy1Start and if it is equal to false the music does nothing
     * if the value is true then the music stops playing.
     */
    void enemy2Start() {
        /*
        if(enemy2Audio == false){
            enemy2Music.start();
            enemy2Audio=true;
            System.out.println("Music for enemy 2 playing");
        }
         */
    }
     /**
     * Method for stopping the enemy2 music
     */
    void enemy2Stop() {
        //enemy2Music.stop();
    }
    /**method is used to stop the music for enemyB. It has a boolean
    *value to check the state of the sound playing, it it is true
    *then the music is stopped for enemyA
    */
    public void enemy2SoundStop() {
        /*
        if(enemy2Audio==true){
            enemy2Music.stop();
            enemy2Audio=false;
            System.out.println("Enemy 2 music has stopped");
         }
         */
    }
    /**
     * methods for calling enemy3 music, when this method is called it
     * reads the .mp3 file given and changes the file into the java media
     * player Manager method which converts the file into playable.
     */
    public void enemy3Sound() {
        /*
        try {
            File enemy3Sound = new File(new URI(new java.net.URL(getClass().getResource("player3.mp3"), "player3.mp3").toString()));
            MediaLocator clip = new MediaLocator(enemy3Sound.toURL());
            enemy3Music = Manager.createPlayer(clip);
            enemy3Start();

        } catch (MalformedURLException e) {
            System.err.println("Error");
        } catch (IOException e) {
            System.err.println("Error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         */
    }
     /**
     * Starts enemyC music. During this time it checks the boolean value
     *of enemy1Start and if it is equal to false the music does nothing
     * if the value is true then the music stops playing.
     */
    void enemy3Start() {
        /*
        if(enemy3Audio == false)
        {
            enemy3Music.start();
            enemy3Audio=true;
            System.out.println("Music for player 3 playing");
        }
         *
         */
    }
     /**
     * Method for stopping the enemy3 music
     */
    void enemy3Stop() {
        /*
        enemy3Music.stop();
         *
         */
    }
    /**method is used to stop the music for enemyC. It has a boolean
    *value to check the state of the sound playing, it it is true
    *then the music is stopped for enemyA
    */
    public void enemy3SoundStop() {
        /*
        if(enemy3Audio==true){
            enemy3Music.stop();
            enemy3Audio=false;
            System.out.println("Enemy 3 music has stopped");
        }
         *
         */
    }
     /**
     * methods for calling enemy4 music, when this method is called it
     * reads the .mp3 file given and changes the file into the java media
     * player Manager method which converts the file into playable.
     */

    public void enemy4Sound() {
        /*
        try {
            File enemy4Sound = new File(new URI(new java.net.URL(getClass().getResource("player4.mp3"), "player4.mp3").toString()));
            MediaLocator clip = new MediaLocator(enemy4Sound.toURL());
            enemy4Music = Manager.createPlayer(clip);
            enemy4Start();

        } catch (MalformedURLException e) {
            System.err.println("Error");
        } catch (IOException e) {
            System.err.println("Error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         *
         */

    }
       /**
     * Starts enemyD music. During this time it checks the boolean value
     *of enemy1Start and if it is equal to false the music does nothing
     * if the value is true then the music stops playing.
     */
    void enemy4Start() {
        /*
        if(enemy4Audio == false)
        {
            enemy4Music.start();
            enemy4Audio=true;
            System.out.println("Music for player 4 playing");
        }
         *
         */
    }
     /**
     * Method for stopping the enemy4 music
     */
    void enemy4Stop() {
        /*
        enemy4Music.stop();
         *
         */
    }
    /**method is used to stop the music for enemyD. It has a boolean
    *value to check the state of the sound playing, it it is true
    *then the music is stopped for enemyA
    */

    public void enemy4SoundStop() {
        /*
        if(enemy4Audio==true){
         enemy4Music.stop();
         enemy4Audio=false;
        System.out.println("Enemy 4 music has stopped");
        }
         *
         */
    }
    /**
     * methods for calling enemy1 music, when this method is called it
     * reads the .mp3 file given and changes the file into the java media
     * player Manager method which converts the file into playable.
     */

    public void enemy5Sound() {
        /*
        try {
            File enemy5Sound = new File(new URI(new java.net.URL(getClass().getResource("player3.mp3"), "player3.mp3").toString()));
            MediaLocator clip = new MediaLocator(enemy5Sound.toURL());
            enemy5Music = Manager.createPlayer(clip);
            enemy5Start();

        } catch (MalformedURLException e) {
            System.err.println("Error");
        } catch (IOException e) {
            System.err.println("Error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         *
         */
    }
        /**
         * Starts enemyA music. During this time it checks the boolean value
        *of enemy1Start and if it is equal to false the music does nothing
        * if the value is true then the music stops playing.
         */
    void enemy5Start() {
        /*
        if(enemy5Audio == false)
        {
            enemy5Music.start();
            enemy5Audio = true;
            System.out.println("Music for player 5 playing");
        }
         *
         */
    }
     /**
     * Method for stopping the enemy3 music
     */
    void enemy5Stop() {
        /*
        enemy5Music.stop();
         *
         */
    }

    /**method is used to stop the music for enemyE. It has a boolean
    *value to check the state of the sound playing, it it is true
    *then the music is stopped for enemyA
    */
    public void enemy5SoundStop() {
        /*
        if(enemy5Audio==true){
            enemy5Music.stop();
            enemy5Audio=false;
            System.out.println("Enemy 5 music has stopped");
        }
         *
         */
    }
/**
     * methods for calling the end game  music, when this method is called it
     * reads the .mp3 file given and changes the file into the java media
     * player Manager method which converts the file into playable.
     */
    public void endGameSound(){
        /*
        try{
        File endGamemusic = new File(new URI(new java.net.URL(getClass().getResource("gameover.mp3"), "gameover.mp3").toString()));
        MediaLocator clip = new MediaLocator(endGamemusic.toURL());
        endGame = Manager.createPlayer(clip);
        endMusicStart();
         } catch (MalformedURLException e) {
            System.err.println("Error");
        } catch (IOException e) {
            System.err.println("Error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         *
         */

        }
     /**
     * Starts the end game  music. 
     */
        public void endMusicStart(){
            /*
            endGame.start();
            System.out.println("End game music is playing");
             *
             */
    }
}
