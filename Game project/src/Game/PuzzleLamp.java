public class PuzzleLamp extends GameObject{

    private int color;
    private PuzzleLamp neighbourLeft;
    private PuzzleLamp neighbourRight;
    private boolean done;

    public PuzzleLamp(int lampColor, int x, int y) {
        xPos = x;
        yPos = y;
        color = lampColor; // 0 or 1
        done = false;
    }

    public void setNeighbourLeft(PuzzleLamp leftNeighbourLamp) {
        neighbourLeft = leftNeighbourLamp;
    }

    public void setNeighbourRight(PuzzleLamp rightNeighbourLamp) {
        neighbourRight = rightNeighbourLamp;
    }

    public void changeColor() {
        if(!done) {
            if (color == 0) {
                color = 1;
            }
            else if (color == 1) {
                color = 0;
            }
            if(neighbourLeft != null) {
                neighbourLeft.changeNeighbourColor();
            }
            if(neighbourRight != null) {
                neighbourRight.changeNeighbourColor();
            }
        }
    }

    public void changeNeighbourColor() {
        if (color == 0) {
            color = 1;
        }
        else if (color == 1) {
            color = 0;
        }
    }

    public int getColor() {
        return color;
    }

    public void setDone() {
        done = true;
    }

}