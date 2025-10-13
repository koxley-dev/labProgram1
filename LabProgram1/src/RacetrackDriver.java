import java.io.*;
import java.util.*;

/**
 * CSC 223 — Program 1: Grid Racers
 * Loads a track text file, computes weights (0 from all 'F' cells, 9999 for 'X', distance for 'T'),
 * and prints the track, weights, and dimensions.
 *
 * Usage:
 *   java RacetrackDriver track1.txt
 *   java RacetrackDriver track2.txt
 */
public class RacetrackDriver {

    private static final int WALL = 9999;
    private static final int FINISH = 0;

    // 8-direction movement: N, NE, E, SE, S, SW, W, NW
    private static final int[] DR = {-1,-1,-1, 0, 0, 1, 1, 1};
    private static final int[] DC = {-1, 0, 1,-1, 1,-1, 0, 1};

    public static void main(String[] args) {
        try {
            String filename = (args.length > 0) ? args[0] : promptForPath();

            // Optional: use the provided Racetrack just for the banner (still "using" the class)
            try {
                Racetrack r = new Racetrack();  // default no-arg is fine
                r.displayBanner();
            } catch (Throwable ignored) {
                // If your Racetrack has no default ctor, just skip the banner.
            }

            char[][] track = loadTrackFromFile(filename);
            int[][] weights = buildWeights(track);

            System.out.println("=== Grid Racers! ===");
            System.out.println("File: " + filename);
            System.out.println("Dimensions: " + track.length + " x " + (track.length == 0 ? 0 : track[0].length));
            System.out.println();

            System.out.println("Track:");
            printChars(track);
            System.out.println();

            System.out.println("Weights:");
            printInts(weights);
            System.out.println();

        } catch (IOException ioe) {
            System.err.println("I/O error: " + ioe.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Read the text file into a rectangular char matrix.
     * Accepts 'T', 'X', 'F' (and tolerates spaces).
     */
    private static char[][] loadTrackFromFile(String filename) throws IOException {
        ArrayList<char[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int width = -1;
            while ((line = br.readLine()) != null) {
                line = line.replace(" ", ""); // ignore embedded spaces if any
                if (line.isEmpty()) continue;
                if (width == -1) width = line.length();
                if (line.length() != width) {
                    throw new IOException("Non-rectangular track: expected width " + width + " but got " + line.length());
                }
                rows.add(line.toCharArray());
            }
        }
        char[][] grid = new char[rows.size()][];
        for (int i = 0; i < rows.size(); i++) grid[i] = rows.get(i);
        return grid;
    }

    /**
     * Multi-source BFS in 8 directions.
     *  - 'X' => 9999
     *  - 'F' => 0 (sources)
     *  - 'T' => shortest 8-neighbor distance to any 'F'
     */
    private static int[][] buildWeights(char[][] track) {
        int r = track.length;
        int c = (r == 0) ? 0 : track[0].length;
        int[][] w = new int[r][c];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // init queue and base weights
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char cell = track[i][j];
                if (cell == 'X') {
                    w[i][j] = WALL;
                } else if (cell == 'F') {
                    w[i][j] = FINISH;
                    q.addLast(new int[]{i, j});
                } else {
                    w[i][j] = -1; // unvisited
                }
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int cr = cur[0], cc = cur[1];
            for (int k = 0; k < 8; k++) {
                int nr = cr + DR[k], nc = cc + DC[k];
                if (inBounds(nr, nc, r, c) && track[nr][nc] != 'X' && w[nr][nc] == -1) {
                    w[nr][nc] = w[cr][cc] + 1;
                    q.addLast(new int[]{nr, nc});
                }
            }
        }

        return w;
    }

    private static boolean inBounds(int i, int j, int r, int c) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }

    private static void printChars(char[][] grid) {
        for (char[] row : grid) {
            StringBuilder sb = new StringBuilder();
            for (char ch : row) sb.append(ch).append(' ');
            System.out.println(sb.toString().trim());
        }
    }

    private static void printInts(int[][] grid) {
        for (int[] row : grid) {
            StringBuilder sb = new StringBuilder();
            for (int v : row) {
                if (v == WALL) sb.append("9999 ");
                else sb.append(v).append(' ');
            }
            System.out.println(sb.toString().trim());
        }
    }

    private static String promptForPath() throws IOException {
        System.out.print("Enter track filename (e.g., track1.txt): ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine().trim();
    }
}

