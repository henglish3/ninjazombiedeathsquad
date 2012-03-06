/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjazombiedeathsquad;

import java.awt.*;
import java.awt.font.*;

/**
 *
 * @author mgibbons1
 */
public class TimeKeeper implements Runnable {

    Graphics2D graph;
    long startTime;
    long time;
    String formattedTime;
    private Player player;

    /**
     * Instantiates a TimeKeeper object which will keep track of when the game started.
     * @param player {@link Player} object containing the player that the user is playing.
     */
    TimeKeeper(Player player) {
        startTime = getSystemTimeMillis();
        this.player = player;
        formattedTime= "0";
    }

    /**
     * Calculates how long it has been since the object
     * was insantiated.
     */
    void calculateTime() {
        time=getSystemTimeMillis()-startTime;
        double timeInSeconds = time/1000.0;
        double minutes = timeInSeconds/60;
        int flatMinutes = (int)minutes;
        double minuteRemainder = minutes-flatMinutes;
        int seconds = (int)(minuteRemainder*60);
        String minuteString = flatMinutes+" minutes ";
        String secondString = seconds+" seconds";
        formattedTime=minuteString+"\n"+secondString;
    }

    /**
     * Displays the current time which has already been
     * calculated in the bottom left corner of the screen.
     */
    void displayTime() {
        try {
            //sets the font for the game over
            FontRenderContext frc = graph.getFontRenderContext();
            Font f = new Font("chiller", Font.BOLD, 48);
            TextLayout tl = new TextLayout(formattedTime, f, frc);
            graph.setColor(Color.black);
            //draws the string
            tl.draw(graph, 0, 530);
        } catch (Exception e) {
        }
    }

    /**
     * Repeats until the player dies in a thread that will
     * calculate and display the time.
     */
    public void run() {
        while (true) {
            calculateTime();
            displayTime();
            if (player.isAlive() == false) {
                break;
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
            }
            //checks if game is paused, freezes time if paused.
            while (Move.pause){
                if (!Move.pause){
                    break;
                }
            }
        }
    }

    /**
     * Instantiates a TimeKeeper object which will keep track of when the game started.
     * @param player {@link Player} object containing the player that the user is playing.
     * @param time Starting time for TimeKeeper.
     */
    TimeKeeper(Player player, long time) {
        this.time = time;
        this.player = player;
        formattedTime= "0";
    }

    /**
     * Gets the latest calculated time difference.
     * @return Returns the latest calculated time difference.
     */
    long getTime() {
        return time;
    }

    /**
     * Gets the current system time in milliseconds.
     * @return Returns the current system time in milliseconds.
     */
    long getSystemTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Sets the time difference.
     * @param time The time to set the difference to.
     */
    void setTime(long time) {
        this.time = time;
    }

    /**
     * Increases the time difference by 1.
     */
    void incTime() {
        time++;
    }

    /**
     * Saves the graphics context and calls displayTime()
     * to re-display the time.
     *
     * @param g Current graphics context
     */
    public void displayTime(Graphics g) {
        graph = (Graphics2D) g;
        displayTime();
    }
}
