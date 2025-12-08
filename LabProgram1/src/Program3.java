import java.util.ArrayList;

// CSC 223 – Program 3: Grid Racers (Collisions & Paths)
// Author: Kryssa Oxley

// Uses the provided Racetrack, Car, and Position classes.
// Cars:
// - UserCar
// - SportsCar
// - AgileCar

// Features:
// - Collision detection with walls, other cars, and finish line.
// - Cars remain on previous position after collisions (no move).
// - Winning car's path is displayed on the racetrack at the end.

public class Program3 {

    public static void main(String[] args) {

        // 1. Create racetrack and display banner
        Racetrack track = new Racetrack();  // uses useDefaultTrack() internally
        track.displayBanner();

        // 2. Create cars (each with a unique ID character)
        Car userCar   = new UserCar('U');
        Car sportsCar = new SportsCar('S');
        Car agileCar  = new AgileCar('A');

        Car[] cars = { userCar, sportsCar, agileCar };

        // 3. Place cars at starting positions (on 'T' tiles near bottom)
        // Adjust as needed; these fit the default track in Racetrack.useDefaultTrack()
        userCar.setRow(7);
        userCar.setCol(2);
        userCar.setCarMove(track);

        sportsCar.setRow(7);
        sportsCar.setCol(4);
        sportsCar.setCarMove(track);

        agileCar.setRow(7);
        agileCar.setCol(6);
        agileCar.setCarMove(track);

        // 4. Path tracking: one list of Positions per car
        ArrayList<Position>[] paths = new ArrayList[cars.length];
        for (int i = 0; i < cars.length; i++) {
            paths[i] = new ArrayList<>();
            paths[i].add(new Position(cars[i].getRow(), cars[i].getCol()));
        }

        System.out.println("Initial racetrack:");
        printTrack(track);

        // 5. Race loop
        boolean winnerFound = false;
        Car winner = null;
        int winnerIndex = -1;
        int maxRounds = 50;
        int round = 0;

        while (!winnerFound && round < maxRounds) {
            round++;
            System.out.println("\n=== ROUND " + round + " ===");

            for (int i = 0; i < cars.length; i++) {
                Car c = cars[i];

                if (c.getWinner()) {
                    continue; // already finished
                }

                int oldRow = c.getRow();
                int oldCol = c.getCol();

                // Let the car decide its move and perform collision checks
                c.move(track);

                // If position changed, record new position in path
                if (c.getRow() != oldRow || c.getCol() != oldCol) {
                    paths[i].add(new Position(c.getRow(), c.getCol()));
                }

                // Check for winner
                if (c.getWinner()) {
                    winnerFound = true;
                    winner = c;
                    winnerIndex = i;
                }
            }

            printTrack(track);
        }

        // 6. End of race results
        if (winner != null) {
            System.out.println("\nWinner: Car ID " + winner.getIdNumber());
            System.out.println("Final racetrack with winner's path highlighted (*):");
            printTrackWithWinnerPath(track, paths[winnerIndex], winner);
        } else {
            System.out.println("\nNo winner within " + maxRounds + " rounds.");
            System.out.println("Final racetrack state:");
            printTrack(track);
        }
    }

    /**
     * Print the current racetrack directly from Racetrack.getTrack().
     */
    private static void printTrack(Racetrack track) {
        for (int r = 0; r < track.height(); r++) {
            for (int c = 0; c < track.width(); c++) {
                System.out.print(track.getTrack(r, c));
            }
            System.out.println();
        }
    }

    /**
     * Print the racetrack with the winning car's path marked as '*'.
     * The winner's final position is shown with its car ID.
     */
    private static void printTrackWithWinnerPath(Racetrack track,
                                                 ArrayList<Position> path,
                                                 Car winner) {

        int rows = track.height();
        int cols = track.width();

        // Copy current track into display array
        char[][] display = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                display[r][c] = track.getTrack(r, c);
            }
        }

        // Mark path positions with '*', but don't overwrite walls
        for (Position p : path) {
            int row = p.getRow();
            int col = p.getCol();
            if (display[row][col] != 'X') {
                display[row][col] = '*';
            }
        }

        // Mark winner's final position with its ID
        int wr = winner.getRow();
        int wc = winner.getCol();
        display[wr][wc] = winner.getIdNumber();

        // Print display grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(display[r][c]);
            }
            System.out.println();
        }
    }
}

