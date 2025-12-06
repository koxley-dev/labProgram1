Name: Lab Program 2 (Grid Racers)
Author: Kryssa Oxley
Date: 05 NOV 2025
Course: CSC 223 – Data Structures

Files:
    - Program2.java
    - Racetrack.java
    - CarAgent.java
    - Position.java
    - Car.java       (provided)
    - track1.txt
    - track2.txt
    - ProgramReport.txt
    - Screenshot_of_PR.png

Run:
1. Unzip the folder and open it in IntelliJ Community Edition.
2. Open Program2.java (the main class).
3. Press the green Run button.
4. The program will automatically load track1.txt and place three cars (Car1, Car2, Car3) on the racetrack.
5. Watch the console output:
       • “Cars Start Race” banner
       • Initial racetrack display (X = wall, F = finish)
       • Cars moving each round based on their velocities
       • Car statistics after every round (position, row velocity, column velocity, weight)
6. The race ends when a car reaches the finish (F).  The winner is displayed.

Program Description:
Grid Racers extends Program 1 by animating cars on the weighted racetrack created from BFS calculations.
Each car begins on the highest available weight and moves each round toward the lowest weight within its velocity range.
Velocities grow with every move according to vertical and horizontal distance traveled.
Cars take turns in fixed order (Car1, Car2, Car3), cannot share cells, and the first to land on 'F' wins.

Key Features:
• Uses provided Car.java and Racetrack class
• Incorporates movement logic with variable velocity
• Displays racetrack and car data after each round
• Ends automatically when a car finishes
• Compatible with Program 1 track files (track1.txt, track2.txt)

IDE: IntelliJ Community Edition
GitHub: Pull request screenshot included (Screenshot_of_PR.png)
