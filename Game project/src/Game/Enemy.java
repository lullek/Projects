import java.util.ArrayList;
import java.awt.Image;

public class Enemy {
    int hp;
    int walkingSpeed;
    int xPos;
    int yPos;
    int facingDirection;
    int rest;
    boolean alive;
    //private ArrayList<Image> frames;

    public Enemy(int x, int y, int healthPoints, int speed) {
        xPos = x;
        yPos = y;
        hp = healthPoints;
        walkingSpeed = speed;
        rest = 0;
        alive = true;
        //frames = new ArrayList<Image>();
    }

    public void moveX(int x) {
        xPos = xPos + x*walkingSpeed;
    }

    public void moveY(int y) {
        yPos = yPos + y*walkingSpeed;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getHp() {
        return hp;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int restValue) {
        rest = restValue;
    }

    public boolean checkAlive() {
        return alive;
    }

    public void setEnemyDefeated() {
        alive = false;
    }

    public void enemyHit() {
        hp -= 1;
    }
}
