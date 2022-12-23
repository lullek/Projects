import java.util.ArrayList;

/**
 * This class generates a puzzle of lamps for the game.
 *
 * @author  Lucas Kerslow
 * @version 2021-05-08
 */

public class LampPuzzle {
    private int unitSize;
    private int xMiddle;
    private int yMiddle;
    private Room room;
    private ArrayList<PuzzleLamp> lamps;
    private PuzzleLamp leftLeftLamp;
    private PuzzleLamp leftLamp;
    private PuzzleLamp middleLamp;
    private PuzzleLamp rightLamp;
    private PuzzleLamp rightRightLamp;
    private boolean large;
    private boolean checkSolved;
    private boolean solved;

    public LampPuzzle(int gameUnitSize, int xMiddleLamp, int yMiddleLamp, boolean isLarge) {
        unitSize = gameUnitSize;
        xMiddle = xMiddleLamp;
        yMiddle = yMiddleLamp;
        lamps = new ArrayList<PuzzleLamp>();
        large = isLarge;
        checkSolved = true;
        solved = false;
        setUp(xMiddle, yMiddle);
    }

    public void setUp(int xMiddle, int yMiddle){
        if(large) {
            leftLeftLamp = new PuzzleLamp(0, xMiddle - 2*unitSize, yMiddle);
        }
        leftLamp = new PuzzleLamp(1, xMiddle - 1*unitSize, yMiddle);
        middleLamp = new PuzzleLamp(0, xMiddle, yMiddle);
        rightLamp = new PuzzleLamp(1, xMiddle + 1*unitSize, yMiddle);
        if(large) {
            rightRightLamp = new PuzzleLamp(0, xMiddle + 2*unitSize, yMiddle);
        }
        if(large) {
            leftLeftLamp.setNeighbourRight(leftLamp);
            leftLamp.setNeighbourLeft(leftLeftLamp);
        }
        leftLamp.setNeighbourRight(middleLamp);
        middleLamp.setNeighbourLeft(leftLamp);
        middleLamp.setNeighbourRight(rightLamp);
        rightLamp.setNeighbourLeft(middleLamp);
        if(large) {
            rightLamp.setNeighbourRight(rightRightLamp);
            rightRightLamp.setNeighbourLeft(rightLamp);
        }
        if(large) {
            lamps.add(leftLeftLamp);
        }
        lamps.add(leftLamp);
        lamps.add(middleLamp);
        lamps.add(rightLamp);
        if(large) {
            lamps.add(rightRightLamp);
        }
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void checkLamps() {

        for(PuzzleLamp lamp : lamps) {
            if(lamp.getColor() == 0) {
                checkSolved = false;
            }
        }

            if (checkSolved) {
                solved = true;
                for (PuzzleLamp lamp : lamps) {
                    lamp.setDone();
                }
            }

            checkSolved = true; //reset
    }

    public boolean checkPuzzleSolved(){
        return solved;
    }

    public ArrayList<PuzzleLamp> getLamps() {
        return lamps;
    }

    public Room getRoom() {
        return room;
    }
}