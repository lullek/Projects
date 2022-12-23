import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.Random;
import java.util.ArrayList;

public class GraphicsGenerator extends JPanel implements ActionListener {

    private Timer gameTimer;
    private Random rng;
    private int screenWidth;
    private int screenHeight;
    private int gameSpeed;
    private int unitSize;
    private int snakeSize;
    private int appleX;
    private int appleY;
    private int xVelocity;
    private int yVelocity;
    private int xTemp;
    private int yTemp;
    private boolean running;
    private boolean started;
    private int[] snakeXPos;
    private int[] snakeYPos;
    private int[] highScores;
    private int[] logoXValues;
    private int[] logoYValues;
    private int maxUnits;
    private int keyPressed;
    private int score;
    private ScorePanel scorePanel;


    public GraphicsGenerator(int width, int height) {

        rng = new Random();
        gameSpeed = 50;
        snakeSize = 7;
        unitSize = 20;
        gameTimer = new Timer(gameSpeed, this);
        screenWidth = width;
        screenHeight = height;
        running = false;
        started = false;
        maxUnits = (screenWidth / unitSize) * (screenHeight / unitSize);
        snakeXPos = new int[maxUnits];
        snakeYPos = new int[maxUnits];
        highScores = new int[5];
        logoXValues = new int[62];
        logoYValues = new int[62];
        xVelocity = 1;
        yVelocity = 0;
        score = 0;
        scorePanel = new ScorePanel(screenWidth);


        setUp();

    }

    public void setUp() {

        appleX = unitSize * 18;
        appleY = unitSize * 3;

        int i = 0;
        int parts = snakeSize;

        while (i < snakeSize) {
            snakeXPos[i] = (unitSize * (parts) + unitSize);
            i++;
            parts--;
        }

        i = 0;

        while (i < snakeSize) {
            snakeYPos[i] = unitSize * 3;
            i++;
        }

        this.setBackground(Color.black);
        this.setOpaque(true);
        this.setBounds(0, 50, screenWidth, screenHeight);
        //this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(new Controls());
        setUpLogo();
        //running = true;
        //started = true;
        gameTimer.start();
    }

    public void setUpLogo() {

        logoXValues[0] = 4; //1st row
        logoXValues[1] = 5;
        logoXValues[2] = 6;
        logoXValues[3] = 8;
        logoXValues[4] = 9;
        logoXValues[5] = 10;
        logoXValues[6] = 12;
        logoXValues[7] = 13;
        logoXValues[8] = 14;
        logoXValues[9] = 16;
        logoXValues[10] = 18;
        logoXValues[11] = 20;
        logoXValues[12] = 21;
        logoXValues[13] = 22;
        logoXValues[14] = 4; //2nd row
        logoXValues[15] = 8;
        logoXValues[16] = 10;
        logoXValues[17] = 12;
        logoXValues[18] = 14;
        logoXValues[19] = 16;
        logoXValues[20] = 17;
        logoXValues[21] = 18;
        logoXValues[22] = 20;
        logoXValues[23] = 4; //3rd row
        logoXValues[24] = 5;
        logoXValues[25] = 6;
        logoXValues[26] = 8;
        logoXValues[27] = 10;
        logoXValues[28] = 12;
        logoXValues[29] = 13;
        logoXValues[30] = 14;
        logoXValues[31] = 16;
        logoXValues[32] = 17;
        logoXValues[33] = 20;
        logoXValues[34] = 21;
        logoXValues[35] = 6; //4th row
        logoXValues[36] = 8;
        logoXValues[37] = 10;
        logoXValues[38] = 12;
        logoXValues[39] = 14;
        logoXValues[40] = 16;
        logoXValues[41] = 17;
        logoXValues[42] = 18;
        logoXValues[43] = 20;
        logoXValues[44] = 3; //5th row
        logoXValues[45] = 4;
        logoXValues[46] = 5;
        logoXValues[47] = 6;
        logoXValues[48] = 7;
        logoXValues[49] = 8;
        logoXValues[50] = 10;
        logoXValues[51] = 11;
        logoXValues[52] = 12;
        logoXValues[53] = 14;
        logoXValues[54] = 15;
        logoXValues[55] = 16;
        logoXValues[56] = 18;
        logoXValues[57] = 19;
        logoXValues[58] = 20;
        logoXValues[59] = 21;
        logoXValues[60] = 22;
        logoXValues[61] = 23;

        logoYValues[0] = 0;
        logoYValues[1] = 0;
        logoYValues[2] = 0;
        logoYValues[3] = 0;
        logoYValues[4] = 0;
        logoYValues[5] = 0;
        logoYValues[6] = 0;
        logoYValues[7] = 0;
        logoYValues[8] = 0;
        logoYValues[9] = 0;
        logoYValues[10] = 0;
        logoYValues[11] = 0;
        logoYValues[12] = 0;
        logoYValues[13] = 0;
        logoYValues[14] = 1;
        logoYValues[15] = 1;
        logoYValues[16] = 1;
        logoYValues[17] = 1;
        logoYValues[18] = 1;
        logoYValues[19] = 1;
        logoYValues[20] = 1;
        logoYValues[21] = 1;
        logoYValues[22] = 1;
        logoYValues[23] = 2;
        logoYValues[24] = 2;
        logoYValues[25] = 2;
        logoYValues[26] = 2;
        logoYValues[27] = 2;
        logoYValues[28] = 2;
        logoYValues[29] = 2;
        logoYValues[30] = 2;
        logoYValues[31] = 2;
        logoYValues[32] = 2;
        logoYValues[33] = 2;
        logoYValues[34] = 2;
        logoYValues[35] = 3;
        logoYValues[36] = 3;
        logoYValues[37] = 3;
        logoYValues[38] = 3;
        logoYValues[39] = 3;
        logoYValues[40] = 3;
        logoYValues[41] = 3;
        logoYValues[42] = 3;
        logoYValues[43] = 3;
        logoYValues[44] = 4;
        logoYValues[45] = 4;
        logoYValues[46] = 4;
        logoYValues[47] = 4;
        logoYValues[48] = 4;
        logoYValues[49] = 4;
        logoYValues[50] = 4;
        logoYValues[51] = 4;
        logoYValues[52] = 4;
        logoYValues[53] = 4;
        logoYValues[54] = 4;
        logoYValues[55] = 4;
        logoYValues[56] = 4;
        logoYValues[57] = 4;
        logoYValues[58] = 4;
        logoYValues[59] = 4;
        logoYValues[60] = 4;
        logoYValues[61] = 4;

    }

    public void move() {

        for (int i = snakeSize - 1; i >= 0; i--) {
            if (i == 0) {
                snakeXPos[i] = snakeXPos[i] + (xVelocity * unitSize);
                snakeYPos[i] = snakeYPos[i] + (yVelocity * unitSize);

            } else {

                snakeXPos[i] = snakeXPos[i - 1];
                snakeYPos[i] = snakeYPos[i - 1];

            }
        }


    }


    public void checkOutOfFrame() {
        if (snakeXPos[0] > screenWidth - unitSize) {
            snakeXPos[0] = 0;
        }

        if (snakeXPos[0] < 0) {
            snakeXPos[0] = screenWidth - unitSize;
        }

        if (snakeYPos[0] > screenHeight - unitSize) {
            snakeYPos[0] = 0;
        }

        if (snakeYPos[0] < 0) {
            snakeYPos[0] = screenHeight - unitSize;
        }

    }

    public void changeDirection(int keyChar) {

        if (keyChar == 37 && xVelocity != 1) {
            xVelocity = -1;
            yVelocity = 0;
        } else if (keyChar == 38 && yVelocity != 1) {
            xVelocity = 0;
            yVelocity = -1;
        } else if (keyChar == 39 && xVelocity != -1) {
            xVelocity = 1;
            yVelocity = 0;
        } else if (keyChar == 40 && yVelocity != -1) {
            xVelocity = 0;
            yVelocity = 1;
        }


    }

    public void checkAppleEaten() {

        if ((appleX == snakeXPos[0]) && (appleY == snakeYPos[0])) {

            boolean finished = false;
            boolean coordinatesConfirmed = true;
            int newX = 0;
            int newY = 0;

            while (!finished) {
                newX = generateNewAppleXLocation();
                newY = generateNewAppleYLocation();

                for (int i = 0; i < snakeSize; i++) {
                    if ((newX == snakeXPos[i]) && (newY == snakeYPos[i])) {
                        coordinatesConfirmed = false;
                    } else {
                        coordinatesConfirmed = true;
                    }
                }

                if (coordinatesConfirmed) {
                    finished = true;
                }
            }


        appleX = newX;
        appleY = newY;
        //add one additional x and y coordinate and increase snakeSize int by 1, adds 1 new bodypart to snake.
        snakeXPos[snakeSize] = snakeXPos[snakeSize -1];
        snakeYPos[snakeSize] = snakeYPos[snakeSize -1];
        snakeSize += 1;
        score += 1000;
    }
}


    public int generateNewAppleXLocation() {
        int newX = rng.nextInt(screenWidth/unitSize) * unitSize;
        return newX;
    }

    public int generateNewAppleYLocation() {
        int newY = rng.nextInt(screenHeight/unitSize) * unitSize;
        return newY;
    }

    public void checkGameOver() {
        for(int i = 1; i < snakeSize; i++) {
            //check for collisions between snake's head and it's bodyparts.
            if((snakeXPos[i] == snakeXPos[0]) && (snakeYPos[i] == snakeYPos[0])){
                //if collision is found: check if score is a highscore then put score in highScore array at the right spot and end game.
                for(int j = 0; (j < 5) ; j++) {
                    if(score > highScores[j]) {
                        int temp = highScores[j];
                        highScores[j] = score;
                        score = temp;
                    }
                }
                running = false;
            }
        }

    }

    public ScorePanel getScorePanel() {

        return scorePanel;

    }

    public void newGame(int keyChar) {
        if (keyChar == 10) {

            if(!started) {
                started = true;
                running = true;
            }

            if (started) {
                snakeSize = 7;

                int i = 0;
                int parts = snakeSize;

                while (i < snakeSize) {
                    snakeXPos[i] = (unitSize * (parts) + unitSize);
                    i++;
                    parts--;
                }

                i = 0;

                while (i < snakeSize) {
                    snakeYPos[i] = unitSize * 3;
                    i++;
                }

                for (int j = snakeSize; j < maxUnits; j++) {
                    snakeXPos[j] = 0;
                    snakeYPos[j] = 0;
                }

                appleX = unitSize * 18;
                appleY = unitSize * 3;
                xVelocity = 1;
                yVelocity = 0;
                score = 0;
                running = true;
            }
        }
    }


    public class Controls extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent k) {
            if(running) {
                keyPressed = k.getKeyCode();
                changeDirection(keyPressed);
            }

            if(!running) {
                keyPressed = k.getKeyCode();
                newGame(keyPressed);
            }
        }
    }

   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       draw(g);
    }

    public void draw(Graphics g) {

        if(running) {
            g.setColor(Color.red);
            g.fillRect(appleX, appleY, unitSize, unitSize);

            g.setColor(Color.green);
            for (int index = 0; index < snakeSize; index++) {
                g.fillRect(snakeXPos[index], snakeYPos[index], unitSize, unitSize);
            }
        }

        if(!running && !started) {
            g.setColor(Color.green);
            for (int index = 0; index < 62; index++) {
                g.fillRect(logoXValues[index]*unitSize - unitSize, logoYValues[index]*unitSize + 7*unitSize, unitSize, unitSize);
            }

            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            FontMetrics adjustments = getFontMetrics(g.getFont());
            g.setColor(Color.blue);
            g.drawString("Press Enter to play", (screenWidth - adjustments.stringWidth("Press Enter to play")) / 2, 0 + unitSize*15);
        }

        if(!running && started) {
            //print Game Over screen and highscores
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
            FontMetrics adjustments = getFontMetrics(g.getFont());
            g.drawString("Game Over", (screenWidth - adjustments.stringWidth("Game Over")) / 2, 0 + unitSize*4);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            adjustments = getFontMetrics(g.getFont());
            g.setColor(Color.green);
            g.drawString("Highscores", (screenWidth - adjustments.stringWidth("Highscores")) / 2, 0 + unitSize*7);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            adjustments = getFontMetrics(g.getFont());
            g.drawString("1. " + highScores[0] + " points", (screenWidth - adjustments.stringWidth("1. " + highScores[0] + " points")) / 2, 0 + unitSize*10);
            g.drawString("2. " + highScores[1] + " points", (screenWidth - adjustments.stringWidth("2. " + highScores[1] + " points")) / 2, 0 + unitSize*12);
            g.drawString("3. " + highScores[2] + " points", (screenWidth - adjustments.stringWidth("3. " + highScores[2] + " points")) / 2, 0 + unitSize*14);
            g.drawString("4. " + highScores[3] + " points", (screenWidth - adjustments.stringWidth("4. " + highScores[3] + " points")) / 2, 0 + unitSize*16);
            g.drawString("5. " + highScores[4] + " points", (screenWidth - adjustments.stringWidth("5. " + highScores[4] + " points")) / 2, 0 + unitSize*18);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            adjustments = getFontMetrics(g.getFont());
            g.setColor(Color.blue);
            g.drawString("Press Enter to play again", (screenWidth - adjustments.stringWidth("Press Enter to play again")) / 2, 0 + unitSize*22);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            adjustments = getFontMetrics(g.getFont());
            g.setColor(Color.blue);
            g.drawString("Made by Lucker Games", (screenWidth - adjustments.stringWidth("Made by Lucker Games")) / 2, 0 + unitSize*24);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkOutOfFrame();
            checkAppleEaten();
            scorePanel.updateScore(score);
            checkGameOver();
        }

        repaint();
    }

}