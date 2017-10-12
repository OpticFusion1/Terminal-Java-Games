package gameloader;

import java.util.*;

public class Game {
    public static Place[][] board;
    public static Queue<Point> changed;
    public static List<Point> allPoints;
    public static Rules rules;

    public static boolean redTurn;
    public static boolean inPlay = true;

    public static Place get(Point point) {
        return Game.board[point.getX()][point.getY()];
    }

    public static void setup(int size) {
        board = new Place[size][size];
        changed = new ArrayDeque<>();

        allPoints = new ArrayList<>();
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                board[x][y] = new Place(new Point(x, y), ' ');
                allPoints.add(new Point(x, y));
            }
        }

        rules.setup();
    }

    public static void select(Point point) {
        if(point != null) {
            Game.get(point).getAction().run();
        }
    }
}
