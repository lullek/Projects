import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.Image;

/**
 * Stores information about the two playable characters, Julia and Lucas
 * 
 * @author  Julia Wang
 * @version 2021-05-11
 */
public enum Character {
    Julia("Julia", new String[]{"../../lib/Julia/Julia_front1.png", "../../lib/Julia/Julia_front2.png", "../../lib/Julia/Julia_front1.png", "../../lib/Julia/Julia_front3.png"},
    new String[]{"../../lib/Julia/Julia_back.png"},
    new String[]{"../../lib/Julia/Julia_right.png"},
    new String[]{"../../lib/Julia/Julia_left.png"},
    "../../lib/Projectile/orangeball.png"),
    Lucas("Lucas", new String[]{"../../lib/Lucas/Lucas_front1.png", "../../lib/Lucas/Lucas_front2.png", "../../lib/Lucas/Lucas_front1.png", "../../lib/Lucas/Lucas_front3.png"},
    new String[]{"../../lib/Lucas/Lucas_back.png"},
    new String[]{"../../lib/Lucas/Lucas_right.png"},
    new String[]{"../../lib/Lucas/Lucas_left.png"},
    "../../lib/Projectile/greenball.png");

    private String name;
    private HashMap<Integer, ArrayList<Image>> images;
    private Image projImg;

    private Character(String n, String[] front, String[] back, String[] right, String[] left, String projectile) {
        name = n;
        images = new HashMap<>();
        for (int i = 37; i <= 40; i ++) {
            images.put(i, new ArrayList<>());
        }
        try {
            for(String path : left) {  
                images.get(37).add(ImageIO.read(new File (path)));
                projImg = ImageIO.read(new File(projectile));
        }
            for(String path : back) {  
                images.get(38).add(ImageIO.read(new File (path)));
                projImg = ImageIO.read(new File(projectile));

        }
            for(String path : right) {  
                images.get(39).add(ImageIO.read(new File (path)));
                projImg = ImageIO.read(new File(projectile));
        }
            for(String path : front) {  
                images.get(40).add(ImageIO.read(new File (path)));
                projImg = ImageIO.read(new File(projectile));
        }
        } catch (IOException imgErr) {
            System.err.println("Sprites couldn't be loaded");
        }
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, ArrayList<Image>> getImages() {
        return images;
    }

    public Image getProjectile() {
        return projImg;
    }
}
