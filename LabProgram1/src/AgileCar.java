import java.util.Random;

// AgileCar:
// An agile car that tries to move diagonally around obstacles.
// Priority: up-right, then up-left, then straight up.

public class AgileCar extends Car {

    private Random rand;

    public AgileCar(char id) {
        super(id);
        rand = new Random();
    }

    @Override
    public void move(Racetrack track) {

        if (getWinner()) {
            return;
        }

        int currentRow = getRow();
        int currentCol = getCol();

        // Try three possible moves: up-right, up-left, straight up.
        // We'll shuffle the diagonal choices a bit to keep movement interesting.
        int[][] offsets = {
                {-1, 1},  // up-right
                {-1, -1}, // up-left
                {-1, 0}   // straight up
        };

        // Simple shuffle of first two diagonal options
        if (rand.nextBoolean()) {
            int[] temp = offsets[0];
            offsets[0] = offsets[1];
            offsets[1] = temp;
        }

        // Try each candidate move until we find a legal one
        for (int i = 0; i < offsets.length; i++) {
            int targetRow = currentRow + offsets[i][0];
            int targetCol = currentCol + offsets[i][1];

            if (targetRow < 0 || targetRow >= track.height()
                    || targetCol < 0 || targetCol >= track.width()) {
                continue; // out of bounds, try next option
            }

            char dest = track.getTrack(targetRow, targetCol);

            if (dest == 'X') {
                continue; // wall, try another option
            }

            if (dest != 'T' && dest != 'F' && dest != 'X') {
                // another car; skip this option
                continue;
            }

            // Finish line
            if (dest == 'F') {
                setWinner(true);
            }

            // Valid move
            updateCarInfo(track, targetRow, targetCol);
            return;
        }

        // If no valid move was found, count as a collision (car slows down)
        carCollision();
    }
}
