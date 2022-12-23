import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;

/**
 * This class contains data for the rooms in the game.
 *
 * @author  Lucas Kerslow & Julia Wang
 * @version 2021-05-12
 */

public class Room {
    private ArrayList<Trap> traps;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Enemy> enemies;
    private ArrayList<NPC> npcs;
    private Image bg;
    private HashMap<String, Room> exits;
    private boolean[][] occupied;
    private int speed;

    // Traps and misc.
    private boolean smallLampPuzzle;
    private boolean largeLampPuzzle;
    private boolean platePuzzle;
    private boolean boss;

    public Room(String file, int speed) {
        traps = new ArrayList<>();
        obstacles = new ArrayList<>();
        enemies = new ArrayList<>();
        npcs = new ArrayList<>();
        try {
            if(file != null) {
                bg = ImageIO.read(new File(file));
            }
        } catch(IOException imgErr) {
            System.err.println("Couldn't load background");
        }
        exits = new HashMap<>();
        occupied = new boolean[26][26];
        this.speed = speed;
        platePuzzle = false;
        smallLampPuzzle = false;
        largeLampPuzzle = false;
        boss = false;
        
        // Set walls
        // Top & Bottom wall
        for(int x = 0; x < 26; x++) {
            occupied[x][1] = true;
            occupied[x][24] = true;
        }
        
        // Left & Bottom wall
        for(int y = 0; y < 26; y++) {
            occupied[1][y] = true;
            occupied[24][y] = true;
        }
    }

    /** Setters */

    public void addObstacle(Obstacle obs) {
        obstacles.add(obs);
        occupied[obs.xPos/speed][obs.yPos/speed] = true;
        occupied[(obs.xPos/speed) + 1][obs.yPos/speed] = true;
        occupied[obs.xPos/speed][(obs.yPos/speed) + 1] = true;
        occupied[(obs.xPos/speed) + 1][(obs.yPos/speed) + 1] = true;
    }

    public void addTrap(Trap trp) {
        traps.add(trp);
    }

    public void addEnemy(Enemy en) {
        enemies.add(en);
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
        occupied[npc.getX()/speed][npc.getY()/speed] = true;
        occupied[(npc.getX()/speed) + 1][npc.getY()/speed] = true;
        occupied[npc.getX()/speed][(npc.getY()/speed) + 1] = true;
        occupied[(npc.getX()/speed) + 1][(npc.getY()/speed) + 1] = true;
    }

    public void setExit(String direction, Room neighbour) {
        // Depending on side remove occupied
        exits.put(direction, neighbour);

        if(direction.equals("North")) {
            occupied[12][1] = false;
            occupied[13][1] = false;
        } else if(direction.equals("South")) {
            occupied[12][24] = false;
            occupied[13][24] = false;
        } else if(direction.equals("West")) {
            occupied[1][12] = false;
            occupied[1][13] = false;
        } else if(direction.equals("East")) {
            occupied[24][12] = false;
            occupied[24][13] = false;
        }
    }

    public void blockExit(String direction) {
        if(direction.equals("North")) {
            occupied[12][1] = true;
            occupied[13][1] = true;
        } else if(direction.equals("South")) {
            occupied[12][24] = true;
            occupied[13][24] = true;
        } else if(direction.equals("West")) {
            occupied[1][12] = true;
            occupied[1][13] = true;
        } else if(direction.equals("East")) {
            occupied[24][12] = true;
            occupied[24][13] = true;
        }
    }

    public void unblockExit(String direction) {
        if(direction.equals("North")) {
            occupied[12][1] = false;
            occupied[13][1] = false;
        } else if(direction.equals("South")) {
            occupied[12][24] = false;
            occupied[13][24] = false;
        } else if(direction.equals("West")) {
            occupied[1][12] = false;
            occupied[1][13] = false;
        } else if(direction.equals("East")) {
            occupied[24][12] = false;
            occupied[24][13] = false;
        }
    }

    public void setSmallLampPuzzle(ArrayList<PuzzleLamp> lamps) {
        smallLampPuzzle = true;
        for(PuzzleLamp lamp : lamps) {
            occupied[lamp.xPos/speed][lamp.yPos/speed] = true;
            occupied[(lamp.xPos/speed) + 1][lamp.yPos/speed] = true;
            occupied[lamp.xPos/speed][(lamp.yPos/speed) + 1] = true;
            occupied[(lamp.xPos/speed) + 1][(lamp.yPos/speed) + 1] = true;
        }
    }

    public void setLargeLampPuzzle(ArrayList<PuzzleLamp> lamps) {
        largeLampPuzzle = true;
        for(PuzzleLamp lamp : lamps) {
            occupied[lamp.xPos/speed][lamp.yPos/speed] = true;
            occupied[(lamp.xPos/speed) + 1][lamp.yPos/speed] = true;
            occupied[lamp.xPos/speed][(lamp.yPos/speed) + 1] = true;
            occupied[(lamp.xPos/speed) + 1][(lamp.yPos/speed) + 1] = true;
        }
    }

    public void setPlatePuzzle() {
        platePuzzle = true;
    }

    public void setBoss() {
        boss = true;
    }

    /** Getters */

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<NPC> getNPCs() {
        return npcs;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Image getBackground() {
        return bg;
    }

    /** Check if it exists */

    public boolean containsSmallLampPuzzle() {
        return smallLampPuzzle;
    }

    public boolean containsLargeLampPuzzle() {
        return largeLampPuzzle;
    }

    public boolean containsPlatePuzzle() {
        return platePuzzle;
    }

    public boolean containsBoss() {
        return boss;
    }

    public boolean containsNPC() {
        return (npcs.size() != 0);
    }

    public boolean isOccupied(int x, int y) {
        int xblock = x/speed; 
        int yblock = y/speed;
        return occupied[xblock][yblock];
    }
}
