import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class ScorePanel extends JPanel {

    private Timer scoreTimer;
    int score = 0;
    int screenWidth;
    int gameSpeed = 50;
    JLabel scoreText;


    public ScorePanel(int width) {

        screenWidth = width;



        //this.setPreferredSize(new Dimension(screenWidth, 100));
        this.setBackground(Color.black);
        this.setBounds(0, 0, screenWidth, 50);
        this.setLayout(new BorderLayout());

        Border border = BorderFactory.createLineBorder(Color.blue, 3);
        this.setBorder(border);

        scoreText = new JLabel();
        scoreText.setText(" Score: " + score);
        scoreText.setHorizontalAlignment(JLabel.LEFT);
        scoreText.setForeground(Color.red);
        scoreText.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        this.add(scoreText);

    }

    public void updateScore(int newScore) {

        score = newScore;
        scoreText.setText(" Score: " + score);

    }


}