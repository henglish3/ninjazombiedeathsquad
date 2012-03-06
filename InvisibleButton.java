package ninjazombiedeathsquad;

import java.awt.Dimension;
import javax.swing.*;


/**
 *
 * @author brianrobbins
 *
 * Extension of the JButton component to allow for an 'invisible'
 * button.
 *
 * public method overrides
 * isBorderPainted()
 *  overriden to return false that way no border is painted
 *  around the JButton.
 * getText()
 *  overriden to return a blank string. So no text is displayed
 *  onto the JButton.
 * isContentAreaFilled()
 *  overriden to return false that way the area within the
 *  JButton is not filled in, allowing an 'invisible' look.
 * getPreferredSize()
 *  overridden to allow a generic 100 by 50 JButton.
 *
 */
public class InvisibleButton extends JButton {

    /**
     *
     * @return a boolean equal to false.
     */
    @Override
        public boolean isBorderPainted() {
            return false;
        }
     /**
      *
      * @return a String equal to nothing.
      */
        @Override
        public String getText() {
            return "";
        }

     /**
      *
      * @return a boolean of the value false.
      */
        @Override
        public boolean isContentAreaFilled() {
            return false;
        }
      /**
       *
       * @return a dimension type of (100,50);
       */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100,50);
        }

}
