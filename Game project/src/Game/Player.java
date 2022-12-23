/**
 * Animates the player character
 * 
 * @author  Julia Wang
 * @version 2021-05-12
 */
public class Player extends Sprite{
    
    public Player(int x, int y, int speed) {
        super(x, y, speed);
    }

    public void update(int keyPress, Room room) {
        if(keyPress == 37 || keyPress == 65) {
            facingDirection = 37;
            //Move player left if not occupied space
            int temp = -1 * walkingSpeed + x;
            if(!room.isOccupied(temp, y) && !room.isOccupied(temp, y + walkingSpeed)) {
                previousX = x;
                previousY = y;
                x = temp;
            }
        }

        if(keyPress == 38 || keyPress == 87) {
            facingDirection = 38;
            //Move player up
            int temp = -1 * walkingSpeed + y;
            if(!room.isOccupied(x, temp) && !room.isOccupied(x + walkingSpeed, temp)) {
                previousX = x;
                previousY = y;
                y = temp;
            }
        }

        if(keyPress == 39 || keyPress == 68) {
            facingDirection = 39;
            //Move player right
            int temp = 1 * walkingSpeed + x;
            if(!room.isOccupied(temp + walkingSpeed, y) && !room.isOccupied(temp + walkingSpeed, y + walkingSpeed)) {
                previousX = x;
                previousY = y;
                x = temp;
            }
        }

        if(keyPress == 40 || keyPress == 83) {
            facingDirection = 40;
            //Move player down
            int temp = 1 * walkingSpeed + y;
            if(!room.isOccupied(x, temp + walkingSpeed) && !room.isOccupied(x + walkingSpeed, temp + walkingSpeed)) {
                previousX = x;
                previousY = y;
                y = temp;
            }
        }
        
        i ++;
        
        if (i >= frames.get(facingDirection).size()) {
            i = 0;
        } 
    }
}
