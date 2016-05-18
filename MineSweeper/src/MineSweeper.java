
import java.util.*;

class MineSweeper {
    private int mines;
    private Color color = Main.color;
    private boolean inGame = true;
    private GridPrint gridPrint = new GridPrint();

    static int size;
    static int covered;
    static int[][] mineField;
    static boolean[][]visited;

    MineSweeper(int[][] field, int Size, Scanner scanner) {
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
            System.out.print("Select Cell:" + color.Green);
            String s = scanner.nextLine();
            System.out.println(color.Clear);
            if(gridPrint.Select(s)) NextTurn();
            else System.out.println(color.Red("Error: try again"));
        }
    }

    private void SetGrid() {
        gridPrint.content = new String[size][size];
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++) {
                if(mineField[x][y] == 0) gridPrint.content[x][y] = color.Cyan(".");
                else if(mineField[x][y] == 9) gridPrint.content[x][y] = color.Red("*");
                else if(mineField[x][y] > 9) gridPrint.content[x][y] = "_";
                else gridPrint.content[x][y] = color.Blue("" + mineField[x][y]);
            }
        gridPrint.Generate();
    }

    private void NextTurn() {
        int i = mineField[gridPrint.selectX][gridPrint.selectY];
        if(i > 9) i -= 10;
        if(i == 9) {
            System.out.println(color.Red("  You Lose!  "));
            System.out.println();
            for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) if(mineField[x][y] > 9) mineField[x][y] -= 10;
            mineField[gridPrint.selectX][gridPrint.selectY] = i;
            inGame = false;
            SetGrid();
        } else {
            mineField[gridPrint.selectX][gridPrint.selectY] -= 10;
            covered--;
            new Sweep(gridPrint.selectX, gridPrint.selectY, false);
            if(covered == mines) {
                System.out.println(color.Green("  You Win!  "));
                System.out.println();
                for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) if(mineField[x][y] > 9) mineField[x][y] -= 10;
                inGame = false;
                SetGrid();
            }
        }
    }
}
