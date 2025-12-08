// UserCar:
// A simple car that tries to move one row upward each turn.
// It uses the provided Car base class.

public class UserCar extends Car {

    public UserCar(char id) {
        super(id);
        // default maxSpeed is already set in Car constructor (5)
    }

    @Override
    public void move(Racetrack track) {

        // If already a winner, don't move
        if (getWinner()) {
            return;
        }

        int currentRow = getRow();
        int currentCol = getCol();

        // Try to move one row up
        int targetRow = currentRow - 1;
        int targetCol = currentCol;

        // Bounds check
        if (targetRow < 0 || targetRow >= track.height()
                || targetCol < 0 || targetCol >= track.width()) {
            // Out of bounds -> collision
            carCollision();
            return;
        }

        char dest = track.getTrack(targetRow, targetCol);

        // Collision with wall
        if (dest == 'X') {
            carCollision();
            return;
        }

        // Collision with another car (any letter that isn't track tile or finish)
        if (dest != 'T' && dest != 'F' && dest != 'X') {
            carCollision();
            return;
        }

        // Finish line
        if (dest == 'F') {
            setWinner(true);
        }

        // Valid move, update car info and leave a 'T' at previous position
        updateCarInfo(track, targetRow, targetCol);
    }
}
