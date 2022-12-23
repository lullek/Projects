import java.util.ArrayList;

public class PlatePuzzle {

    private Room room;
    private ArrayList<PuzzlePlate> plates;
    private boolean checkSolved;
    private boolean solved;

    public PlatePuzzle(ArrayList<PuzzlePlate> plateArray) {

        plates = plateArray;
        checkSolved = true;
        solved = false;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<PuzzlePlate> getPlates() {
        return plates;
    }

    public Room getRoom() {
        return room;
    }

    public void checkPlates() {

        for(PuzzlePlate plate : plates) {
            if(!plate.getActivated()) {
                checkSolved = false;
            }
        }

        if (checkSolved) {
            solved = true;
            for (PuzzlePlate plate : plates) {
                plate.setDone();
            }
        }

        checkSolved = true; //reset
    }

    public boolean checkPuzzleSolved(){
        return solved;
    }
}