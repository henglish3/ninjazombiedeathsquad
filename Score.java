package ninjazombiedeathsquad;
import java.io.*;

/**
 *
 * @author Mark Daniel Gibbons
 */
public class Score implements Serializable {
    String name;
    int score;
    Score()
    {
        
    }
    Score(String name, int score)
    {
        this.name=name;
        this.score=score;
    }
    void setName(String name)
    {
        this.name=name;
    }
    void setScore(int score)
    {
        this.score=score;
    }
    String getName()
    {
        return name;
    }
    int getScore()
    {
        return score;
    }
}
