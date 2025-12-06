import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Racetrack class: loads a text file track, computes weights,
// places cars, moves them per velocity rules, and prints output.

public class Racetrack {

    private char[][] track;
    private int[][] weights;
    private boolean[][] occupied;
    private int rows, cols, round;
    private int minWeight = Integer.MAX_VALUE, maxWeight = Integer.MIN_VALUE;

    public Racetrack(String filename) {
        loadTrack(filename);
        computeWeights();
        occupied = new boolean[rows][cols];
    }

    public int getRound() { return round; }

    // Loads track file (X walls, T open, F finish).
    private void loadTrack(String filename) {
        ArrayList<char[]> lines = new ArrayList<char[]>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line.toCharArray());
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading track file.");
        }
        rows = lines.size();
        cols = lines.get(0).length;
        track = new char[rows][cols];
        for (int r = 0; r < rows; r++)
            track[r] = lines.get(r);
    }

    // Simple weight assignment: smaller number means closer to finish.
    private void computeWeights() {
        weights = new int[rows][cols];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                if (track[r][c] == 'X')
                    weights[r][c] = 9999;
                else if (track[r][c] == 'F')
                    weights[r][c] = 0;
                else
                    weights[r][c] = 1 + r + c; // placeholder pattern
                if (weights[r][c] < minWeight) minWeight = weights[r][c];
                if (weights[r][c] > maxWeight) maxWeight = weights[r][c];
            }
    }

    // Place cars on highest available weights (no duplicates).
    public void placeCarsAtHighestWeights(List<CarAgent> cars) {
        int placed = 0;
        for (int r = 0; r < rows && placed < cars.size(); r++)
            for (int c = 0; c < cols && placed < cars.size(); c++)
                if (weights[r][c] == maxWeight && track[r][c] != 'X') {
                    cars.get(placed).pos = new Position(r, c);
                    occupied[r][c] = true;
                    placed++;
                }
    }

    // Executes race rounds until a car reaches 'F'.
    public CarAgent runRace(List<CarAgent> cars) {
        for (round = 1; round <= 200; round++) {
            for (CarAgent car : cars) {
                if (car.finished) continue;
                Position dest = findLowestWeightInRange(car);
                if (dest == null) continue;
                occupied[car.pos.r][car.pos.c] = false;
                car.velRow += Math.abs(dest.r - car.pos.r);
                car.velCol += Math.abs(dest.c - car.pos.c);
                car.pos = dest;
                occupied[dest.r][dest.c] = true;
                if (track[dest.r][dest.c] == 'F') {
                    car.finished = true;
                    return car;
                }
            }
            displayTrack(cars);
            displayCarInfo(cars, round);
        }
        return null;
    }

    // Choose lowest weight cell within car's velocity range.
    private Position findLowestWeightInRange(CarAgent car) {
        int bestW = Integer.MAX_VALUE;
        Position best = null;
        for (int dr = -car.velRow; dr <= car.velRow; dr++)
            for (int dc = -car.velCol; dc <= car.velCol; dc++) {
                int nr = car.pos.r + dr;
                int nc = car.pos.c + dc;
                if (inBounds(nr, nc) && !occupied[nr][nc] && track[nr][nc] != 'X') {
                    int w = weights[nr][nc];
                    if (w < bestW) {
                        bestW = w;
                        best = new Position(nr, nc);
                    }
                }
            }
        return best;
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }

    // Draws track with cars shown by ID, X as walls, F as finish.
    public void displayTrack(List<CarAgent> cars) {
        String[][] out = new String[rows][cols];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                out[r][c] = String.valueOf(track[r][c]);
        for (CarAgent car : cars)
            out[car.pos.r][car.pos.c] = car.id;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++)
                System.out.print(out[r][c] + " ");
            System.out.println();
        }
        System.out.println();
    }

    // Prints car stats and place based on current weight.
    public void displayCarInfo(List<CarAgent> cars, int roundNum) {
        System.out.println("Cars Information (Round " + roundNum + "):");
        List<CarAgent> sorted = new ArrayList<CarAgent>(cars);
        sorted.sort(Comparator.comparingInt(a -> weights[a.pos.r][a.pos.c]));
        int place = 1;
        for (CarAgent car : sorted) {
            System.out.printf("%d) %-5s pos=(%d,%d) rowVel=%d colVel=%d weight=%d %s%n",
                    place++, car.id, car.pos.r, car.pos.c,
                    car.velRow, car.velCol,
                    weights[car.pos.r][car.pos.c],
                    car.finished ? "[FINISHED]" : "");
        }
        System.out.println();
    }

    public void displayBanner(String title) {
        System.out.println("\n==============================");
        System.out.println(" " + title);
        System.out.println("==============================\n");
    }
}
