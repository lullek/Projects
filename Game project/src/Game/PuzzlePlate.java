public class PuzzlePlate extends GameObject {

    private boolean activated;
    private boolean done;

    public PuzzlePlate(int x, int y) {
        xPos = x;
        yPos = y;
        activated = false;
        done = false;
    }

    public void markActivated() {
        if(!done) {
            activated = true;
        }
    }

    public void markDeactivated() {
        if(!done) {
            activated = false;
        }
    }

    public boolean getActivated() {
        return activated;
    }

    public void setDone() {
        done = true;
    }
}