
import java.util.*;

public class MineSweeper {
    int mines;
    public static int size;
    public static int covered;
    public static int[][] mineField;
    public static boolean[][]visited;
    boolean inGame = true;
    GridPrint gridPrint = new GridPrint();

    public MineSweeper(int[][] field, int Size, Scanner scanner) {
        size = Size;
        mines = 0;
        mineField = field;
        covered = size * size;
        visited = new boolean[size][size];

        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++) {
                if(mineField[x][y] == 9) mines++;
                mineField[x][y] += 10;
            }

        String[] set = new String[size];
        for(int i = 0; i < size; i++) set[i] = "" + (char) (i + 65);
        gridPrint.column = set;
        set = new String[size];
        for(int i = 1; i <= size; i++) {
            if(i > 9) set[i - 1] = "" + i;
            else set[i - 1] =  i + " ";
        }
        gridPrint.row = set;
        while(inGame) {
            System.out.println("Remaining spaces:" + (covered - mines));
            SetGrid();
            System.out.print("Select Cell:");
            String s = scanner.nextLine();
            System.out.println();
            if(gridPrint.Select(s)) NextTurn();
            else System.out.println("Error: try again");
        }
    }

    public void SetGrid() {
        gridPrint.content = new String[size][size];
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++) {
                if(mineField[x][y] == 0) gridPrint.content[x][y] = "\u001B[36m" + "." + "\u001B[0m";
                else if(mineField[x][y] == 9) gridPrint.content[x][y] = "\u001B[31m" + "*" + "\u001B[0m";
                else if(mineField[x][y] > 9) gridPrint.content[x][y] = "_";
                else gridPrint.content[x][y] = "\u001B[34m" + mineField[x][y] + "\u001B[0m";
            }
        gridPrint.Generate();
    }

    public void NextTurn() {
        int i = mineField[gridPrint.selectX][gridPrint.selectY];
        if(i > 9) i -= 10;
        if(i == 9) {
            System.out.println("  You Lose!  ");
            System.out.println();
            for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) if(mineField[x][y] > 9) mineField[x][y] -= 10;
            mineField[gridPrint.selectX][gridPrint.selectY] = i;
            inGame = false;
            SetGrid();
        } else {
            new Sweep(gridPrint.selectX, gridPrint.selectY, false);
            if(covered == mines) {
                System.out.println("  You Win!  ");
                System.out.println();
                for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) if(mineField[x][y] > 9) mineField[x][y] -= 10;
                inGame = false;
                SetGrid();
            }
        }
    }
}
