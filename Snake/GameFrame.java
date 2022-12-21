import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JFrame gameFrame;
    private int gameWidth;
    private int gameHeight;

    public GameFrame(int width, int height) {

        gameWidth = width;
        gameHeight = height;

        GraphicsGenerator gameGraphics = new GraphicsGenerator(gameWidth, gameHeight);
        ScorePanel score = gameGraphics.getScorePanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setSize(gameWidth, gameHeight + 70);
        this.add(score);
        this.add(gameGraphics);
        this.setResizable(false);
        this.setVisible(true);
        //this.pack();
    }


}