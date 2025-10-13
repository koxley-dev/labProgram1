public class Racetrack {

    /** Racetrack from text file **/
    private char[][] track;
    /** Weights of the track position **/
    private int[][] weights;
    /** Constant Value for finish line weight **/
    final private int FINISH_WEIGHT = 0;
    /** Constant Value for wall weight **/
    final private int WALL_WEIGHT = 9999;
    /** Constant Value for pre initialized track weight **/
    final private int INIT_TRACK_WEIGHT = -1;


    /**
     * @return - track position
     */
    public char getTrack(int row, int col){
        return track[row][col];
    }

    /**
     * Update position on track
     */
    public void setTrack(int row, int col, char value){
        track[row][col] = value;
    }

    /**
     * @return - number of rows
     */
    public int height() {
        return track.length;
    }

    /**
     * @return - number of columns
     */
    public int width() {
        return  track[0].length;
    }

    /**
     * Display game banner (Optional)
     */
    public void displayBanner(){
		String art = 	"   ______     _     __   ____                           \n" +
						"  / ____/____(_)___/ /  / __ \\____ _________  __________\n" +
						" / / __/ ___/ / __  /  / /_/ / __ `/ ___/ _ \\/ ___/ ___/\n" +
						"/ /_/ / /  / / /_/ /  / _, _/ /_/ / /__/  __/ /  (__  ) \n" +
						"\\____/_/  /_/\\__,_/  /_/ |_|\\__,_/\\___/\\___/_/  /____/  \n" +
						"                                                        ";

        System.out.println(art + "\n");
    }
	
	

    /**
     * This method creates a track and weights using static values (For Demo Purposes only)
     */
    public void useDefaultTrack(){
        weights = new int[][]{
                {WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT},
                {WALL_WEIGHT, 11, 11, 11, 11, 11, 11, WALL_WEIGHT, FINISH_WEIGHT, FINISH_WEIGHT, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 10, 10, 10, 10, 11, WALL_WEIGHT, 1, 1, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 9, 9, 10, WALL_WEIGHT, WALL_WEIGHT, 2, 2, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, 3, 3, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, 7, 6, 5, 4, 4, 4, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, 7, 6, 5, 5, 5, 5, WALL_WEIGHT},
                {WALL_WEIGHT, 10, 9, 8, 7, 6, 6, 6, 6, 6, WALL_WEIGHT},
                {WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT, WALL_WEIGHT}
        };
        track = new char[][]{
            {'X','X','X','X','X','X','X','X','X','X','X'},
            {'X','T','T','T','T','T','T','X','F','F','X'},
            {'X','T','T','T','T','T','T','X','T','T','X'},
            {'X','T','T','T','T','T','X','X','T','T','X'},
            {'X','T','T','T','X','X','X','X','T','T','X'},
            {'X','T','T','T','T','T','T','T','T','T','X'},
            {'X','T','T','T','T','T','T','T','T','T','X'},
            {'X','T','T','T','T','T','T','T','T','T','X'},
            {'X','X','X','X','X','X','X','X','X','X','X'},
        };
    }
}


