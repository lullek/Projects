import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Animates/Stores animation frames for sprites. Also stores their location and facing direction,
 * dividng the frames between these directions with a HashMap.
 * 
 * @author  Lucas Kerslow & Julia Wang
 * @version 2021-05-12
 */
public class Sprite {
    protected int x;
    protected int y;
    protected int previousX;
    protected int previousY;
    protected int walkingSpeed;
    protected int facingDirection;
    protected HashMap<Integer, ArrayList<Image>> frames;
    protected int i;

    // Constructor
    public Sprite(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        walkingSpeed = speed;
        facingDirection = 40;
        frames = new HashMap<>();
        for (int i = 37; i <= 40; i ++) {
            frames.put(i, new ArrayList<>()); // Change to normal array if you want to save processing power or space
        }
        i = 0;
    }

    /** Setters */

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addFrame(Image i, int direction) {
        frames.get(direction).add(i);
    }

    public void addFrames(HashMap<Integer, ArrayList<Image>> frames) {
        this.frames = frames;
    }

    /** Getters */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return facingDirection;
    }

    public Image getSprite() {
        if(frames.size() == 0) {
            return null;
        } else {
            return frames.get(facingDirection).get(i);
        }
    }

    public int getPrevX() {
        return previousX;
    }

    public int getPrevY() {
        return previousY;
    }
}
