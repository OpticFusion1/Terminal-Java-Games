package gameloader;

import java.util.*;

public class Game {
    public static Place[][] board;
    public static List<Point> changed;
    public static List<Point> allPoints;
    public static Rules rules;
    static int size;

    public static boolean redTurn;
    public static boolean inPlay = true;
    public static boolean lockChanges = true;

    public static int redScore = 0;
    public static int whiteScore = 0;

    public static Place get(Point point) {
        if(point.inSquare(size))
            return Game.board[point.getY()][point.getX()];
        return new Place(new Point(), ' ');
    }

    public static void setup(int size) {
        //Initialize list variables
        board = new Place[size][size];
        changed = new ArrayList<>();
        allPoints = new ArrayList<>();
        Game.size = size;

        //Set up main grid
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                board[y][x] = new Place(new Point(x, y), ' ');
                allPoints.add(new Point(x, y));
            }
        }

        rules.setup();
    }

    public static boolean select(Point point) {
        if(point != null) {
            //Run selected action
            Action a = Game.get(point).getAction();
            rules.clear();
            return a.run();
        }
        return false;
    }
}
