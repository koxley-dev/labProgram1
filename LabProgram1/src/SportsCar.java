// SportsCar
// A faster car that tries to move two rows upwards each turn.

public class SportsCar extends Car {

    public SportsCar(char id) {
        super(id);
        // Give it a slightly higher max speed to distinguish it
        setMaxSpeed(6);
    }

    @Override
    public void move(Racetrack track) {

        if (getWinner()) {
            return;
        }

        int currentRow = getRow();
        int currentCol = getCol();

        // Try to move two rows up
        int targetRow = currentRow - 2;
        int targetCol = currentCol;

        // If two rows up goes out of bounds, fall back to one row up
        if (targetRow < 0) {
            targetRow = currentRow - 1;
        }

        // Bounds check
        if (targetRow < 0 || targetRow >= track.height()
                || targetCol < 0 || targetCol >= track.width()) {
            carCollision();
            return;
        }

        char dest = track.getTrack(targetRow, targetCol);

        if (dest == 'X') {
            carCollision();
            return;
        }

        if (dest != 'T' && dest != 'F' && dest != 'X') {
            carCollision();
            return;
        }

        if (dest == 'F') {
            setWinner(true);
        }

        updateCarInfo(track, targetRow, targetCol);
    }
}

