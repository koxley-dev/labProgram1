Name: Lab Program 3 (Grid Racers – Collisions & Paths)
Author: Kryssa Oxley
Course: CSC 223 – Data Structures
Date: 07 Dec 2025

Files:
    - Program3.java          (main driver for this assignment)
    - Racetrack.java         (provided, with getWeight() and default track)
    - Car.java               (provided, abstract base class)
    - Position.java          (simple coordinate class used for paths)
    - UserCar.java           (extends Car)
    - SportsCar.java         (extends Car)
    - AgileCar.java          (extends Car)
    - track1.txt             (original track file from earlier lab)
    - track2.txt             (original track file from earlier lab)
    - ProgramReport.txt      (program report for this assignment)
    - Screenshot_of_PR.png   (GitHub pull request screenshot)

How to Run:
1. Unzip the folder and open it in IntelliJ Community Edition.
2. Make sure all .java files are in the same src package or default package.
3. Open Program3.java.
4. Click the green Run button.
5. The program will:
       - Display the Grid Racers banner.
       - Initialize the default racetrack from Racetrack.useDefaultTrack().
       - Create three cars: UserCar, SportsCar, AgileCar.
       - Place the cars on starting positions.
       - Run rounds of movement until a winner is found or the maximum
         number of rounds is reached.
       - Print the racetrack after each round.
       - At the end, highlight the winning car's path with '*' and show
         the winner's final position with its car ID.

Notes:
- The program uses the provided Racetrack and Car classes and a Position class.
- Collisions with walls and other cars cause the moving car to stay at its previous
  position and call carCollision(), which reduces max speed and resets velocities.
- Reaching the finish line sets the car's winner flag and ends the race.
- track1.txt and track2.txt are included as original assets from a previous lab,
  but this version uses the Racetrack's built-in default track.
