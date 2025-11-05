import java.util.ArrayList;
import java.util.List;


// CSC 223 – Lab Program 2: Grid Racers
// Author: Kryssa Oxley
// Date: 03 Nov 2025

// Main driver for the race simulation.

public class Program2 {

    public static void main(String[] args) {
        System.out.println("=== CSC 223 – Lab Program 2: Grid Racers ===\n");

        Racetrack track = new Racetrack("data/track1.txt");  // or "track2.txt"

        // Create at least 3 cars
        List<CarAgent> cars = new ArrayList<CarAgent>();
        cars.add(new CarAgent("Car1"));
        cars.add(new CarAgent("Car2"));
        cars.add(new CarAgent("Car3"));

        track.placeCarsAtHighestWeights(cars);
        track.displayBanner("Cars Start Race");
        track.displayTrack(cars);
        track.displayCarInfo(cars, 0);

        CarAgent winner = track.runRace(cars);

        track.displayBanner("Cars Moving with Winner");
        track.displayTrack(cars);
        track.displayCarInfo(cars, track.getRound());

        if (winner != null)
            System.out.println("Winner: " + winner.id);
        else
            System.out.println("No winner this round.");
    }
}
