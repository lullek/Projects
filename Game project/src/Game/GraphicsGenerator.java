import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import javax.sound.sampled.*;

/**
 * This class generates all the graphics for the game.
 *
 * @author  Lucas Kerslow & Julia Wang
 * @version 2021-05-20
 */

public class GraphicsGenerator extends JPanel implements ActionListener {

    // Game Specifications
    private Timer gameTimer;
    private int screenWidth;
    private int screenHeight;
    private int gameSpeed;
    private final int unitSize;
    //private int xDirection;
    //private int yDirection;
    private boolean running;
    private boolean started;
    private boolean end;

    // Rooms
    private Room currentRoom;
    private HashMap<String, Room> rooms;
    
    // Character Select
    private JButton jb;
    private JButton lb;

    // Combat & Interaction
    private int keyPressed;
    private boolean attacking;
    private boolean shooting;
    private boolean damageAble;
    private int projectileXPos;
    private int projectileYPos;
    private int shootingDirection;
    private int interactXpos;
    private int interactYpos;
    private int[] hp;
    private int[] bossHp;
    private Boss charlie;


    // Puzzle
    private LampPuzzle smallLampPuzzle;
    private LampPuzzle largeLampPuzzle;
    private PlatePuzzle platePuzzle;
    private boolean smallLampPuzzleSolved;
    private boolean largeLampPuzzleSolved;
    private boolean platePuzzleSolved;

    // Animation-related
    private Character hero;
    private Player player;
    private Image projectile;
    private Image bossProj;
    private Image boss;
    private Image bg;
    private Image hpFull;
    private Image hpHalf;
    private Image hpEmpty;
    private Image bossHpFull;
    private Image bossHpHalf;
    private Image greenLamp;
    private Image redLamp;
    private Image litLamp;
    private int animationTick;
    private int damageImmuneTick;
    private Image fire1;
    private Image fire2;
    private Image chest;
    private Image obstacle;
    private Image bossDoor;
    private Image noDoor;
    private Image bgNoDoor;
    private Image enemy1;
    private Image enemy2;
    private Image deActivatedPlate;
    private Image activatedPlate;
    private Image donePlate;
    private Image startScreen;
    private Image gameOver;
    private Image congratulationsScreen;

    private Music musicPlayer;

    public GraphicsGenerator(int width, int height) {

        gameSpeed = 75;
        unitSize = 64;
        gameTimer = new Timer(gameSpeed, this);
        screenWidth = width;
        screenHeight = height;
        running = false;
        started = false;
        end = false;
        attacking = false;
        shooting = false;
        damageAble = true;
        animationTick = 1;
        damageImmuneTick = 0;

        hp = new int[]{2, 2, 2};

        //setUp();

        this.setBackground(Color.black);
        this.setOpaque(true);
        //this.setBounds(0, 0, screenWidth, screenHeight);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(new Controls());

        setUpRooms("text/RoomData.txt");
        choosePlayable();
        gameTimer.start();
    }

    /**
     * Runs when either button at the start screen is clicked and a start character is set.
     * 
     * Removes the button, revalidates the panel and sets the booleans, running and started = true.
     */
    public void load() {
        loadImages();
        this.remove(jb);
        this.remove(lb);
        this.requestFocus();
        this.revalidate();
        this.repaint();
        running = true;
        started = true;

        try {
            musicPlayer = new Music();

            musicPlayer.play();


        } catch(Exception ex)
            {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();

            }
        }

    /**
     * Sets up the rooms from a text file which specifies location of obstacles and exits for rooms.
     * 
     * Also loads the Boss, NPCs and Puzzles in
     * 
     * @param filePath
     */
    public void setUpRooms(String filePath) {
        int speed = unitSize/2;
        rooms = new HashMap<>();
        charlie = new Boss(((screenWidth - unitSize) / 2) - 32, ((screenHeight - unitSize) / 2) - 32, 6, speed);

        // Puzzle - Setup
        smallLampPuzzle = new LampPuzzle(unitSize, unitSize*6, unitSize*6, false);
        largeLampPuzzle = new LampPuzzle(unitSize, unitSize*6, unitSize*6, true);
        smallLampPuzzleSolved = false;
        largeLampPuzzleSolved = false;

        ArrayList<PuzzlePlate> platesList1 = new ArrayList<PuzzlePlate>();

        int plateMidX = (screenWidth - unitSize) / 2;
        int plateMidY = (screenHeight - unitSize) / 2;
        int diff = unitSize + (unitSize / 2);

        PuzzlePlate puzzlePlate1 = new PuzzlePlate(plateMidX - diff, plateMidY - diff);
        PuzzlePlate puzzlePlate2 = new PuzzlePlate(plateMidX, plateMidY - diff);
        PuzzlePlate puzzlePlate3 = new PuzzlePlate(plateMidX, plateMidY); //middle one
        PuzzlePlate puzzlePlate4 = new PuzzlePlate(plateMidX - diff, plateMidY);
        PuzzlePlate puzzlePlate5 = new PuzzlePlate(plateMidX - diff, plateMidY + diff);
        PuzzlePlate puzzlePlate6 = new PuzzlePlate(plateMidX, plateMidY + diff);
        PuzzlePlate puzzlePlate7 = new PuzzlePlate(plateMidX + diff, plateMidY + diff);
        PuzzlePlate puzzlePlate8 = new PuzzlePlate(plateMidX + diff, plateMidY);
        PuzzlePlate puzzlePlate9 = new PuzzlePlate(plateMidX + diff, plateMidY - diff);

        platesList1.add(puzzlePlate1);
        platesList1.add(puzzlePlate2);
        platesList1.add(puzzlePlate3);
        platesList1.add(puzzlePlate4);
        platesList1.add(puzzlePlate5);
        platesList1.add(puzzlePlate6);
        platesList1.add(puzzlePlate7);
        platesList1.add(puzzlePlate8);
        platesList1.add(puzzlePlate9);

        platePuzzle = new PlatePuzzle(platesList1);
        platePuzzleSolved = false;

        // Create and Set Room, Exit and Such
        try {
            String[] data;
            Scanner input = new Scanner(new FileReader(filePath));
            
            while(input.hasNextLine()) {
                data = input.nextLine().split(", ");
                
                if(data[0].equals("Room")) {
                    rooms.put(data[1], new Room(data[2], speed));
                } else if(data[0].equals("Exit")) {
                    rooms.get(data[1]).setExit(data[2], rooms.get(data[3]));
                } else {
                    Room getRoom = rooms.get(data[1]);
                    int x = Integer.parseInt(data[3]) * unitSize; 
                    int y = Integer.parseInt(data[4]) * unitSize;
                    if(data[0].equals("Trap")) {
                        getRoom.addTrap(new Trap(x, y));
                    } else if(data[0].equals("Obstacle")) {
                        getRoom.addObstacle(new Obstacle(x, y));                        
                    } else if(data[0].equals("Enemy")) {
                        getRoom.addEnemy(new Enemy(x, y, Integer.parseInt(data[5]), speed));
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException fileErr) {
            System.err.println("Room.txt not succesfully parsed");
            fileErr.printStackTrace();
        }

        // NPC test
        NPC npc1 = new NPC(unitSize*10, unitSize*1, unitSize/2);
        try {
            npc1.addFrame(ImageIO.read(new File("../../lib/NPC/kermit.png")), 37);
            npc1.addFrame(ImageIO.read(new File("../../lib/NPC/kermit.png")), 38);
            npc1.addFrame(ImageIO.read(new File("../../lib/NPC/kermit.png")), 39);
            npc1.addFrame(ImageIO.read(new File("../../lib/NPC/kermit.png")), 40);
            npc1.setDialogue("It's not easy being green!");
        } catch(IOException imgErr) {
            System.err.println("Couldn't load NPC");
        }

        currentRoom = rooms.get("Start");
        currentRoom.addNPC(npc1);
        Room empty = rooms.get("Empty");
        empty.setSmallLampPuzzle(smallLampPuzzle.getLamps());
        Room fourth = rooms.get("Fourth");
        fourth.setLargeLampPuzzle(largeLampPuzzle.getLamps());
        Room third = rooms.get("Third");
        third.setPlatePuzzle();
        rooms.get("Boss").setBoss();

        //test
        //bossRoom.setExit("East", fifthRoom);
        //fifthRoom.setExit("West", bossRoom);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if(running) {
            if(bg != null) {
                // Draws Background
                g.drawImage(bg, 0, 0, null);

                // Draws locked doors - Couldn't think of good solution to integrate with the block below
                
                if(currentRoom.containsBoss() && charlie.checkAlive()) {
                    g.drawImage(bossDoor, screenWidth - unitSize, (screenHeight - unitSize) / 2, null);
                    g.drawImage(bossDoor, 0, (screenHeight - unitSize) / 2, null);
                }

                if(!smallLampPuzzleSolved) {
                    if(currentRoom == rooms.get("Second")) {
                        g.drawImage(bossDoor, 0, (screenHeight - unitSize) / 2, null);
                    } else if(currentRoom == rooms.get("Empty")) {
                        g.drawImage(bossDoor, 0, (screenHeight - unitSize) / 2, null);
                    }
                }

                if(currentRoom.containsPlatePuzzle() && !platePuzzleSolved) {
                    g.drawImage(bossDoor, screenWidth - unitSize, (screenHeight - unitSize) / 2, null);
                    g.drawImage(bossDoor, (screenWidth - unitSize) / 2, screenHeight - unitSize, null);
                }

                if(currentRoom.containsLargeLampPuzzle() && !largeLampPuzzleSolved) {
                    g.drawImage(bossDoor, (screenWidth - unitSize) / 2, 0, null);
                    g.drawImage(bossDoor, screenWidth - unitSize, (screenHeight - unitSize) / 2, null);
                }
            }

            // Draws exits and such for a Room without custom background image
            if(bg == null) {

                g.drawImage(bgNoDoor, 0, 0, null);

                if (currentRoom.getExit("North") == null) {
                    g.drawImage(noDoor, (screenWidth - unitSize) / 2, 0, null);
                }
                if (currentRoom.getExit("East") == null) {
                    g.drawImage(noDoor, screenWidth - unitSize, (screenHeight - unitSize) / 2, null);
                }
                if (currentRoom.getExit("South") == null) {
                    g.drawImage(noDoor, (screenWidth - unitSize) / 2, screenHeight - unitSize, null);
                }
                if (currentRoom.getExit("West") == null) {
                    g.drawImage(noDoor, 0, (screenHeight - unitSize) / 2, null);
                }
            }

            // Draws the player HP bar
            for(int i = 0; i < hp.length; i ++) {
                if (hp[i] == 2) {
                    g.drawImage(hpFull, i*32, 0, null);
                }
                if (hp[i] == 1) {
                    g.drawImage(hpHalf, i*32, 0, null);
                }
                if (hp[i] == 0) {
                    g.drawImage(hpEmpty, i*32, 0, null);
                }
            }

            /* Puzzles */

            if(currentRoom.containsSmallLampPuzzle()) {
                for (PuzzleLamp lamp : smallLampPuzzle.getLamps()) {
                    if (lamp.getColor() == 0) {
                        g.drawImage(redLamp, lamp.getX(), lamp.getY(), null);
                    }
                    if (lamp.getColor() == 1 && !smallLampPuzzle.checkPuzzleSolved()) {
                        g.drawImage(greenLamp, lamp.getX(), lamp.getY(), null);
                    }
                    if (lamp.getColor() == 1 && smallLampPuzzle.checkPuzzleSolved()) {
                        g.drawImage(litLamp, lamp.getX(), lamp.getY(), null);
                    }
                }
            }

            if(currentRoom.containsLargeLampPuzzle()) {
                for (PuzzleLamp lamp : largeLampPuzzle.getLamps()) {
                    if (lamp.getColor() == 0) {
                        g.drawImage(redLamp, lamp.getX(), lamp.getY(), null);
                    }
                    if (lamp.getColor() == 1 && !largeLampPuzzle.checkPuzzleSolved()) {
                        g.drawImage(greenLamp, lamp.getX(), lamp.getY(), null);
                    }
                    if (lamp.getColor() == 1 && largeLampPuzzle.checkPuzzleSolved()) {
                        g.drawImage(litLamp, lamp.getX(), lamp.getY(), null);
                    }
                }
            }

            if(currentRoom.containsPlatePuzzle()) {
                for (PuzzlePlate plate : platePuzzle.getPlates()) {
                    if (!plate.getActivated()) {
                        g.drawImage(deActivatedPlate, plate.getX(), plate.getY(), null);
                    }
                    if (plate.getActivated() && !platePuzzle.checkPuzzleSolved()) {
                        g.drawImage(activatedPlate, plate.getX(), plate.getY(), null);
                    }
                    if (plate.getActivated() && platePuzzle.checkPuzzleSolved()) {
                        g.drawImage(donePlate, plate.getX(), plate.getY(), null);
                    }
                }
            }

            /* Boss */

            if(currentRoom.containsBoss()) {
                if (charlie.getDamageImmuneTick() == 0) {
                    g.drawImage(boss, charlie.getX(), charlie.getY(), null);
                }

                if (charlie.getDamageImmuneTick() != 0) {
                    if (charlie.getDamageImmuneTick() == 3 || charlie.getDamageImmuneTick() == 4
                        || charlie.getDamageImmuneTick() == 7 || charlie.getDamageImmuneTick() == 8) {

                        g.drawImage(boss, charlie.getX(), charlie.getY(), null);

                    }
                }

                if(charlie.getShooting()) {
                    g.drawImage(bossProj, charlie.getBossProjectileX(), charlie.getBossProjectileY(), null);
                }

                bossHp = charlie.getBossHp();
                int j = 1;

                if(charlie.checkAlive()) {

                    for (int i = bossHp.length - 1; i >= 0; i--) {

                        if (bossHp[i] == 2) {
                            g.drawImage(bossHpFull, screenWidth - j * 32, 0, null);
                        }
                        if (bossHp[i] == 1) {
                            g.drawImage(bossHpHalf, screenWidth - j * 32, 0, null);
                        }
                        if (bossHp[i] == 0) {
                            g.drawImage(hpEmpty, screenWidth - j * 32, 0, null);
                        }
                        j++;
                    }
                } else if(!charlie.checkAlive()) {
                    for(int i = bossHp.length - 1; i >= 0; i--) {
                        g.drawImage(hpFull, screenWidth - j * 32, 0, null);
                        j++;
                    }
                }

            }

            //Draw player
            if (damageImmuneTick == 0) {
                g.drawImage(player.getSprite(), player.getX(), player.getY(), null);
            }

            if (damageImmuneTick != 0) {
                if(damageImmuneTick  == 3 || damageImmuneTick == 4 || damageImmuneTick == 7 || damageImmuneTick == 8
                        || damageImmuneTick == 11 || damageImmuneTick == 12) {
                    g.drawImage(player.getSprite(), player.getX(), player.getY(), null);
                }
            }

            /* Misc. Obstacles and Such */

            for(Trap trap : currentRoom.getTraps()) {
                if (animationTick < 3) {
                    g.drawImage(fire1, trap.getX(), trap.getY(), null);
                } else {
                    g.drawImage(fire2, trap.getX(), trap.getY(), null);
                }
            }

            for(Obstacle roomObstacle : currentRoom.getObstacles()) {
                g.drawImage(obstacle, roomObstacle.getX(), roomObstacle.getY(), null);
            }

            for(Enemy roomEnemy : currentRoom.getEnemies()) {
                if(roomEnemy.checkAlive()) {
                    if (animationTick < 3) {
                        g.drawImage(enemy1, roomEnemy.getX(), roomEnemy.getY(), null);
                    } else {
                        g.drawImage(enemy2, roomEnemy.getX(), roomEnemy.getY(), null);
                    }
                }
            }

            for(NPC npc : currentRoom.getNPCs()) {
                g.drawImage(npc.getSprite(), npc.getX(), npc.getY(), null);
                // Some trigger to check if interacted with
                npc.drawDialogue(g);   
            }

            /* Projectiles */

            if(attacking) {
                if(player.getDirection() == 37) {
                    g.setColor(Color.red);
                    g.fillOval(player.getX() - unitSize * 2, player.getY(), unitSize * 2, unitSize * 2);
                }
            }

            if(shooting) {
                g.drawImage(projectile, projectileXPos, projectileYPos, null);
            }
        } else if(!started) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            FontMetrics adjustments = getFontMetrics(g.getFont());
            g.setColor(Color.blue);
            g.drawString("Press a Character to Play", (screenWidth - adjustments.stringWidth("Press a Character to Play")) / 2, (screenHeight-2*unitSize)/2);
            g.drawImage(startScreen, 0, 0, null);
        } else if(started && !end) {
            //print Game Over screen and highscores
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
            FontMetrics adjustments = getFontMetrics(g.getFont());
            g.drawString("Game Over", (screenWidth - adjustments.stringWidth("Game Over")) / 2, 0 + unitSize*4);
            g.drawString("Made by Jules & Lucker Games", (screenWidth - adjustments.stringWidth("Made by Jules & Lucker Games")) / 8, 0 + unitSize*20);
            g.drawImage(gameOver, 0, 0, null);
        } else if(end) {
            // To-Do

            g.setColor(Color.blue);
            FontMetrics adjustments = getFontMetrics(g.getFont());
            g.drawString("Congratulations!", (screenWidth - adjustments.stringWidth("Congratulations!")) / 2, 0 + unitSize*4);
            g.drawString("You have passed your first year at KTH.", (screenWidth - adjustments.stringWidth("You have passed your first year at KTH.")) / 2, 0 + unitSize*5);
            g.drawImage(congratulationsScreen, 0, 0, null);
        }
    }

    /**
     * Performs actions based on the key pressed by the player.
     *
     * @param k The key pressed by the player
     */

    public class Controls extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent k) {
            if(running) {
                keyPressed = k.getKeyCode();
                if(keyPressed == 37 || keyPressed == 38 || keyPressed == 39 || keyPressed == 40 ||
                keyPressed == 65 || keyPressed == 87 || keyPressed == 68 || keyPressed == 83) {
                    player.update(keyPressed, currentRoom);
                }

                if(keyPressed == 32) {
                    shoot();
                }

                if(keyPressed == 16  || keyPressed == 69) {
                    interact();
                }
            }
        }
    }
    
    public void shoot() {

        if(!shooting) {
            projectileXPos = player.getX();
            projectileYPos = player.getY();
            shootingDirection = player.getDirection();
            shooting = true;
        }
    }

    public void interact() {
        if(currentRoom.containsSmallLampPuzzle()) {
            for (int i = 0; i < smallLampPuzzle.getLamps().size(); i++) {
                if (interactXpos == smallLampPuzzle.getLamps().get(i).getX() && interactYpos == smallLampPuzzle.getLamps().get(i).getY()) {
                    smallLampPuzzle.getLamps().get(i).changeColor();
                }
            }
        }

        if(currentRoom.containsLargeLampPuzzle()) {
            for (int i = 0; i < largeLampPuzzle.getLamps().size(); i++) {
                if (interactXpos == largeLampPuzzle.getLamps().get(i).getX() && interactYpos == largeLampPuzzle.getLamps().get(i).getY()) {
                    largeLampPuzzle.getLamps().get(i).changeColor();
                }
            }
        }

        if(currentRoom.containsNPC()) {
            for(NPC npc : currentRoom.getNPCs()) {
                if(interactXpos == npc.getX() && interactYpos == npc.getY()) {
                    npc.talking();
                }
            }
        }
    }

    /**
     * Updates the players HP.
     */

    public void changeHp() {
        if(damageAble) {
            if (hp[2] != 0) {
                hp[2] = hp[2] - 1;
            } else if (hp[1] != 0) {
                hp[1] = hp[1] - 1;
            } else {
                hp[0] = hp[0] - 1;
            }
            damageAble = false;
            damageImmuneTick = 16;
        }
    }

    public void correctPosition() {
        player.setX(player.getPrevX());
        player.setY(player.getPrevY());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            checkGameOver();
            checkPuzzleDone();
            checkTrap();
            updateAnimationTick();
            updateDamageImmuneTick();
            updateProjectile();
            updateInteractPos();
            checkNewRoom();
            activateEnemies();
            checkPlatePuzzle();
        }

        repaint();
    }

    /** Actions to perform */

    /**
     * Updates the coordinates of a projectile shot by the players character.
     * This method is called from the actionPerformed() method to update the projectile's
     * coordinates with every tick of the game timer.
     *
     * Also keeps track of the projectile to check if it is out of the screen. It then
     * registers the shot as being done.
     */

    public void updateProjectile() {
        if(shooting) {
            projectileXPos += (unitSize/2)*getProjectileXVelocity();
            projectileYPos += (unitSize/2)*getProjectileYVelocity();
        }

        if(projectileXPos > screenWidth || projectileXPos < 0 - unitSize || projectileYPos > screenHeight || projectileYPos < 0 - unitSize) {
            shooting = false;
        }
    }

    /**
     * A helper method for updateProjectile(). Gives it the correct x values
     * to make the projectile go the right direction.
     *
     * @return an int with the value of which the projectile should update in the x direction.
     */

    public int getProjectileXVelocity() {
        if(shootingDirection == 37) {
            return -1;
        }

        if(shootingDirection == 38) {
            return 0;
        }

        if(shootingDirection == 39) {
            return 1;
        }

        if(shootingDirection == 40) {
            return 0;
        }

        return 0;
    }

    
    /**
     * A helper method for updateProjectile(). Gives it the correct y values
     * to make the projectile go the right direction.
     *
     * @return an int with the value of which the projectile should update in the y direction.
     */

    public int getProjectileYVelocity() {
        if(shootingDirection == 37) {
            return 0;
        }

        if(shootingDirection == 38) {
            return -1;
        }

        if(shootingDirection == 39) {
            return 0;
        }

        if(shootingDirection == 40) {
            return 1;
        }

        return 0;
    }

    /**
     * Updates the space where the player can interact to the space in front of
     * The player.
     */

    public void updateInteractPos() {
        if(player.getDirection() == 37) {
            //left
            interactXpos = player.getX() - unitSize;
            interactYpos = player.getY();
        }

        if(player.getDirection() == 38) {
            //up
            interactXpos = player.getX();
            interactYpos = player.getY() - unitSize;
        }

        if(player.getDirection() == 39) {
            //right
            interactXpos = player.getX() + unitSize;
            interactYpos = player.getY();
        }

        if(player.getDirection() == 40) {
            //down
            interactXpos = player.getX();
            interactYpos = player.getY() + unitSize;
        }
    }

    /**
     * Check if the player has stepped on a trap.
     */

    public void checkTrap() {
        for(Trap trap : currentRoom.getTraps()) {
            if ((player.getX() == trap.getX() || player.getX() == trap.getX() + unitSize/2 || player.getX() == trap.getX() - unitSize/2) &&
                    (player.getY() == trap.getY() || player.getY() == trap.getY() + unitSize/2 || player.getY() == trap.getY() - unitSize/2)) {
                changeHp();
            }
            //if (player.getX() == trap.getX() + unitSize / 2 && player.getY() == trap.getY()) {
            //    changeHp();
            //}
            //if (player.getX() == trap.getX() && player.getY() == trap.getY() - unitSize / 2) {
            //    changeHp();
            //}
            //if (player.getX() == trap.getX() && player.getY() == trap.getY() + unitSize / 2) {
            //    changeHp();
            //}
            //if (player.getX() == trap.getX() - unitSize / 2 && player.getY() == trap.getY() - unitSize / 2) {
            //    changeHp();
            //}
            //if (player.getX() == trap.getX() + unitSize / 2 && player.getY() == trap.getY() - unitSize / 2) {
            //    changeHp();
            //}
            //if (player.getX() == trap.getX() - unitSize / 2 && player.getY() == trap.getY() + unitSize / 2) {
            //    changeHp();
            //}
            //if (player.getX() == trap.getX() + unitSize / 2 && player.getY() == trap.getY() + unitSize / 2) {
            //    changeHp();
            //}
        }
    }

    public void checkPuzzleDone() {

        smallLampPuzzle.checkLamps();

        if(smallLampPuzzle.checkPuzzleSolved()) {
            smallLampPuzzleSolved = true;
            if(currentRoom.containsSmallLampPuzzle()) {
                rooms.get("Second").setExit("West", rooms.get("Third"));
                currentRoom.setExit("West", rooms.get("Second"));
            }
        } else if(currentRoom.containsSmallLampPuzzle()) {
            currentRoom.blockExit("West");
        }

        largeLampPuzzle.checkLamps();

        if(largeLampPuzzle.checkPuzzleSolved()) {
            largeLampPuzzleSolved = true;
            if(currentRoom.containsLargeLampPuzzle()) {
                currentRoom.setExit("North", rooms.get("Third"));
                currentRoom.setExit("East", rooms.get("Boss"));
            }
        } else if(currentRoom.containsLargeLampPuzzle()) {
            currentRoom.blockExit("North");
        }

        platePuzzle.checkPlates();

        if(platePuzzle.checkPuzzleSolved()) {
            platePuzzleSolved = true;
            if(currentRoom.containsPlatePuzzle()) {
                currentRoom.setExit("East", rooms.get("Second"));
                currentRoom.setExit("South", rooms.get("Fourth"));
            }
        } else if(currentRoom.containsPlatePuzzle()) {
            currentRoom.blockExit("East");
        }
    }

    /**
     * Checks if the player's HP is 0. Ends the game if it is.
     */

    public void checkGameOver() {
        if(hp[0] == 0) {
            running = false;
            musicPlayer.stop();
        }
    }

    public void updateAnimationTick() {
        animationTick += 1;
        if(animationTick == 5) {
            animationTick = 1;
        }
    }

    public void updateDamageImmuneTick() {
        if(damageImmuneTick != 0) {
            damageImmuneTick -= 1;
        }

        if(damageImmuneTick == 0 && !damageAble) {
            damageAble = true;
        }
    }

    public void checkNewRoom() {
        if(player.getY() == 0*unitSize) {
            if(currentRoom.getExit("North") != null) {
                currentRoom = currentRoom.getExit("North");
                bg = currentRoom.getBackground();
                player.setY(screenHeight-2*unitSize);
            }
        }
        if(player.getX() == screenWidth - 1*unitSize) {
            if(currentRoom.getExit("East") != null) {
                currentRoom = currentRoom.getExit("East");
                bg = currentRoom.getBackground();
                player.setX(1*unitSize);
            } else if(currentRoom.containsBoss()) {
                running = false;
                end = true;
                musicPlayer.stop();
            }
        }
        if(player.getY() == screenHeight - 1*unitSize) {
            if(currentRoom.getExit("South") != null) {
                currentRoom = currentRoom.getExit("South");
                bg = currentRoom.getBackground();
                player.setY(1*unitSize);
            }
        }
        if(player.getX() == 0*unitSize) {
            if(currentRoom.getExit("West") != null) {
                currentRoom = currentRoom.getExit("West");
                bg = currentRoom.getBackground();
                player.setX(screenWidth-2*unitSize);
            }
        }
    }

    public void activateEnemies() {
        for(Enemy enemy : currentRoom.getEnemies()) {
            if(enemy.getRest() == 0 && enemy.checkAlive()) {
                if (enemy.getY() < player.getY()) {
                    enemy.moveY(1);
                }
                if (enemy.getY() > player.getY()) {
                    enemy.moveY(-1);
                }
                if (enemy.getX() < player.getX()) {
                    enemy.moveX(1);
                }
                if (enemy.getX() > player.getX()) {
                    enemy.moveX(-1);
                }
            } else if(enemy.getRest() != 0 && enemy.checkAlive()){
                enemy.setRest(enemy.getRest() - 1);
            }

            if(enemy.getY() == player.getY() && enemy.getX() == player.getX() && enemy.checkAlive()) {
                changeHp();
                enemy.setRest(14);
            }
            if(!(enemy.getY() == player.getY() && enemy.getX() == player.getX()) && enemy.checkAlive()) {
            if(     enemy.getY() == projectileYPos && enemy.getX() == projectileXPos ||
                    enemy.getY() + unitSize/2 == projectileYPos && enemy.getX() == projectileXPos ||
                    enemy.getY() == projectileYPos && enemy.getX() + unitSize/2 == projectileXPos ||
                    enemy.getY() + unitSize/2 == projectileYPos && enemy.getX() + unitSize/2 == projectileXPos ) {

                enemy.enemyHit();

                if (enemy.getHp() == 0 && enemy.checkAlive()) {
                    enemy.setEnemyDefeated();
                }
            }
            }
        }

        if(currentRoom.containsBoss()) {
            currentRoom.blockExit("West");
            activateCharlie();
        }
    }

    public void activateCharlie() {
        if(charlie.checkAlive()) {
            if (charlie.getY() < player.getY()) {
                charlie.moveY(1);
            }

            if (charlie.getY() > player.getY()) {
                charlie.moveY(-1);
            }

            if (charlie.getX() - player.getX() < 4*unitSize) {
                if(charlie.getX() < 10*unitSize) {
                    charlie.moveX(1);
                }
            }

            if (charlie.getX() - player.getX() > 4*unitSize) {
                if(charlie.getX() > unitSize) {
                    charlie.moveX(-1);
                }
            }

           // if(charlie.getX() != (screenWidth - unitSize) / 2) {
           //     if(charlie.getX() < (screenWidth - unitSize) / 2) {
           //         charlie.moveX(1);
           //     }
            // if(charlie.getX() > (screenWidth - unitSize) / 2) {
            //        charlie.moveX(-1);
            //    }
            //}

            //if (charlie.getX() - 6 * unitSize < player.getX()) {
            //   charlie.moveX(1);
            // }

            //if (charlie.getX() + 5 * unitSize > player.getX()) {
            //    charlie.moveX(-1);
            //}
        }

        //if (charlie.getX() < player.getX()) {
        //    if (player.getX() - charlie.getX() < 4 * unitSize) {
        //        charlie.moveX(-1);
        //    }
        //    if (charlie.getX() - charlie.getX() > 4 * unitSize) {
        //        charlie.moveX(1);
        //    }
        //}


        //if (charlie.getX() > player.getX()) {
        //    if (charlie.getX() - player.getX() > 4 * unitSize) {
        //        charlie.moveX(-1);
        //    }
        //    if (charlie.getX() - player.getX() < 4 * unitSize) {
        //        charlie.moveX(1);
        //    }
        //}

        if((charlie.getY() == player.getY()) && !charlie.getShooting() && charlie.checkAlive()) {
            charlie.shoot(player.getX());
        }

        if(charlie.getShooting()) {
            charlie.shoot(player.getX());
            if(charlie.getBossProjectileX() < 0 || charlie.getBossProjectileX() > screenWidth  ||
                    charlie.getBossProjectileY() < 0 || charlie.getBossProjectileY() > screenHeight) {
                charlie.setShootingFalse();
            }

            if((charlie.getBossProjectileX() == player.getX() || charlie.getBossProjectileX() == player.getX() + 32)
                    && (charlie.getBossProjectileY() == player.getY() || charlie.getBossProjectileY() == player.getY() + 32)) {
                changeHp();
            }
        }

        if(charlie.getDamageImmuneTick() != 0) {
            charlie.refreshDamageImmuneTick();
        }

        if(charlie.checkAlive()) {
            if(     (charlie.getY() == projectileYPos || charlie.getY() + unitSize/2 == projectileYPos || charlie.getY() + unitSize == projectileYPos)
                    && (charlie.getX() == projectileXPos || charlie.getX() + unitSize/2 == projectileXPos)) {

                charlie.enemyHit();

                if (charlie.getHp() == 0 && charlie.checkAlive()) {
                    charlie.setEnemyDefeated();
                }
            }
        }

        if(!charlie.checkAlive()) { //move if defeated
            currentRoom.unblockExit("West");
            currentRoom.unblockExit("East");

            if(charlie.getX() != screenWidth - 3*unitSize) {
                if(charlie.getX() < screenWidth - 3*unitSize) {
                    charlie.moveX(1);
                }
                if(charlie.getX() > screenWidth - 3*unitSize) {
                    charlie.moveX(-1);
                }
            }

            if(charlie.getY() != (screenHeight - 2*unitSize) / 2) {
                if(charlie.getY() < (screenHeight - 2*unitSize) / 2) {
                    charlie.moveY(1);
                }
                if(charlie.getX() > (screenHeight - 2*unitSize) / 2) {
                    charlie.moveY(-1);
                }
            }
        }
    }

    public void checkPlatePuzzle() {
        if(currentRoom.containsPlatePuzzle()) {
            //activate first plate
            if(player.getX() == platePuzzle.getPlates().get(0).getX() && (player.getY() == platePuzzle.getPlates().get(0).getY() || player.getY() == platePuzzle.getPlates().get(0).getY() - 32)) {
                platePuzzle.getPlates().get(0).markActivated();
            }

            for(int i = 1; i < platePuzzle.getPlates().size(); i++) {
                //activate other than first plates
                if(player.getX() == platePuzzle.getPlates().get(i).getX() && (player.getY() == platePuzzle.getPlates().get(i).getY() || player.getY() == platePuzzle.getPlates().get(i).getY() - 32)) {
                    if(platePuzzle.getPlates().get(i - 1).getActivated()) {
                        platePuzzle.getPlates().get(i).markActivated();
                    } else {
                        for(int j = i; j >= 0; j--) {
                            platePuzzle.getPlates().get(j).markDeactivated();
                        }
                    }
                }
            }
        }
    }
    
    // Helper methods

    /** 
     * Add buttons that lets the player select from two Characters
     */
    private void choosePlayable() {
        Icon jicon = new ImageIcon("../../lib/Julia/Julia_front1.png");
        Icon licon = new ImageIcon("../../lib/Lucas/Lucas_front1.png");
        jb = new JButton(jicon);
        lb = new JButton(licon);
        jb.setBounds(4*unitSize, (screenHeight-unitSize)/2, unitSize, unitSize);
        lb.setBounds(screenWidth-5*unitSize, (screenHeight-unitSize)/2, unitSize, unitSize);
        setLayout(null);
        jb.setFocusable(false);
        lb.setFocusable(false);
        jb.setContentAreaFilled(false);
        lb.setContentAreaFilled(false);
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hero = Character.Julia;
                load();
            }
        });
        lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hero = Character.Lucas;
                load();
            }
        });
        add(jb);
        add(lb);
    }

    /**
     * Loads all images to use in the Game
     */
    private void loadImages() {
        try {
            // Sprite
            player = new Player(unitSize*2, unitSize*2, unitSize/2);
            projectile = hero.getProjectile();            
            boss = ImageIO.read(new File("../../lib/Charlie/Holy-Charlie1.png"));
            bossProj = ImageIO.read(new File("../../lib/Projectile/Bulle.png"));
            player.addFrames(hero.getImages());

            // Background
            bg = currentRoom.getBackground();
            bgNoDoor = ImageIO.read(new File("../../lib/BG/Background832.png")); // Something map
            bossDoor = ImageIO.read(new File("../../lib/BG/Secret_Door.png"));
            noDoor = ImageIO.read(new File("../../lib/BG/noDoor.png"));

            startScreen = ImageIO.read(new File("../../lib/BG/startscreen.png"));
            gameOver = ImageIO.read(new File("../../lib/BG/gameover.png"));
            congratulationsScreen = ImageIO.read(new File("../../lib/BG/congratulations.png"));

            // HP-bar
            hpFull = ImageIO.read(new File("../../lib/UI/HPfilled.png"));
            hpHalf = ImageIO.read(new File("../../lib/UI/HPhalffilled.png"));
            hpEmpty = ImageIO.read(new File("../../lib/UI/HPempty.png"));

            bossHpFull = ImageIO.read(new File("../../lib/UI/bossHPfilled.png"));
            bossHpHalf = ImageIO.read(new File("../../lib/UI/bossHPhalffilled.png"));

            // Lamps
            greenLamp = ImageIO.read(new File("../../lib/Obj/greenlamp.png"));
            redLamp = ImageIO.read(new File("../../lib/Obj/redlamp.png"));
            litLamp = ImageIO.read(new File("../../lib/Obj/litlamp.png"));

            // Fire
            fire1 = ImageIO.read(new File("../../lib/Obj/fire1.png"));
            fire2 = ImageIO.read(new File("../../lib/Obj/fire2.png"));

            // Chest
            chest = ImageIO.read(new File("../../lib/Obj/chest.png"));

            // Obstacle
            obstacle = ImageIO.read(new File("../../lib/Obj/obstacle.png"));

            // Enemy
            enemy1 = ImageIO.read(new File("../../lib/Obj/enemy1.png"));
            enemy2 = ImageIO.read(new File("../../lib/Obj/enemy2.png"));

            // Plates
            deActivatedPlate = ImageIO.read(new File("../../lib/Obj/deactivatedplate.png"));
            activatedPlate = ImageIO.read(new File("../../lib/Obj/activatedplate.png"));
            donePlate = ImageIO.read(new File("../../lib/Obj/doneplate.png"));

        } catch (IOException imgErr) {
            System.err.println("Images couldn't load");
        }
    }

}