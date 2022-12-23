import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A JFrame to contain the JPanel which contains the graphics of the game. It creates the
 * JPanel (GraphicsGenerator).
 *
 * @author  Lucas Kerslow & Julia Wang
 * @version 2021-05-11
 */

public class GameFrame extends JFrame {

    private JFrame gameFrame;
    private int gameWidth;
    private int gameHeight;

    public GameFrame(int width, int height) {

        gameWidth = width;
        gameHeight = height;

        try {
            setIconImage(ImageIO.read(new File("../../lib/kormit2.jpg")));
        } catch(IOException noKormit) {
            System.err.println("No Kormit :(");
        }
        GraphicsGenerator gameGraphics = new GraphicsGenerator(gameWidth, gameHeight);
        this.add(gameGraphics);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("KTH Game");
        this.setResizable(false);
        this.pack();
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        //this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setVisible(true);
    }
}
