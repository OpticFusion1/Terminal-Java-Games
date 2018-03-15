package gameloader;

import java.util.*;

import gameloader.base.*;

public class Game {
    public static Place[][] board;
    public static List<Point> changed;
    public static List<Point> allPoints;
    public static Map<String, Action> choices;
    public static Rules rules;
    public static int size;

    public static boolean redTurn;
    public static boolean inPlay = true;
    public static boolean lockChanges = true;
    public static boolean singlePlayer = false;

    public static int redScore = 0;
    public static int whiteScore = 0;

    public static Place get(Point point) {
        if(point.inSquare(size))
            return Game.board[point.getY()][point.getX()];
        return null;
    }

    public static void setup() {
        //Initialize list variables
        int size = Game.size = rules.getSize();
        board = new Place[size][size];
        changed = new ArrayList<>();
        allPoints = new ArrayList<>();
        choices = new HashMap<>();

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
        return point != null && select(Game.get(point).getAction());
    }

    public static boolean select(Action action) {
        if(action == null) return false;

        rules.clear();
        choices.clear();
        return action.run();
    }

    public static void changeScore(int change, boolean red) {
        if(red) redScore += change;
        else whiteScore += change;
    }
}
