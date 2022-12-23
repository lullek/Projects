import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class NPC extends Sprite {
    private String dialogue; // Or action
    private boolean talking;

    public NPC(int x, int y, int speed) {
        super(x, y, speed);
        talking = false;
    }

    /**
     * Potentially movement of some kind
     * @param animationTick
     * @param room
     */
    public void update(int animationTick, Room room) {
        // TO-DO
        // Makes it able to move and do some action while standby
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public void talking() {
        if(!talking) {
            talking = true;
        } else {
            talking = false;
        }
    }

    /**
     * Supposed to draw a speech bubble and some accompanying dialogue
     * @param g The Graphics to draw on
     */
    public void drawDialogue(Graphics g) {
        if(talking) {
            int talkingX = x-(walkingSpeed*2);
            int talkingY = y-walkingSpeed;
            Image img;

            try {
                img = ImageIO.read(new File("../../lib/UI/speech.png"));
                g.drawImage(img, x-(walkingSpeed*2), y-walkingSpeed, null);
                g.setColor(Color.black);
                g.setFont(new Font("Calibri", Font.PLAIN, 10));
                g.drawString(dialogue, talkingX+(walkingSpeed/4), talkingY+walkingSpeed);
            } catch (Exception imgErr) {
                System.err.println("Stop! The NPC doesn't seem to want to pop up.");
                g.setColor(Color.white);
                g.fillOval(x-walkingSpeed, y-walkingSpeed, walkingSpeed*2, walkingSpeed);
            }
        }
    }
}
