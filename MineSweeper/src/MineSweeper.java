
import java.util.*;

class MineSweeper {
    private int mines;
    private Color color = Main.color;
    private boolean inGame = true;
    private int size;



    static int covered;
    static int[][] mineField;
    static boolean[][]visited;
    static GridPrint gridPrint;

    MineSweeper(int Size, Scanner scanner) {
        size = Size;
        mines = size * size / 8;
        covered = size * size;
        visited = new boolean[size][size];
        gridPrint = new GridPrint(size,size);
        mineField = new int[size][size];

        for(Point p : gridPrint.cells) mineField[p.x][p.y] = 0;
        for(int i = 0; i < mines; i++) {
            boolean unSet = true;
            while(unSet) {
                Random rand = new Random();
                int randX = rand.nextInt(size);
                int randY = rand.nextInt(size);
                if(mineField[randX][randY] != 9) {
                    unSet = false;
                    mineField[randX][randY] = 9;
                    for(int x = randX - 1; x < randX + 2; x++) for(int y = randY - 1; y <= randY + 1; y++) if(gridPrint.contains(x,y) && mineField[x][y] != 9) mineField[x][y]++;
                }
            }
        }
        for(Point p : gridPrint.cells) {
                if(MineSweeper.mineField[p.x][p.y] == 9) mines++;
                MineSweeper.mineField[p.x][p.y] += 10;
        }
        while(inGame) {
            System.out.println("Remaining spaces:" + (covered - mines));
            SetGrid();
            gridPrint.Search(scanner);
            NextTurn();
        }
    }

    private void SetGrid() {
        gridPrint.content = new String[size][size];
        for(Point p : gridPrint.cells){
                if(mineField[p.x][p.y] == 0) gridPrint.content[p.x][p.y] = color.Cyan(".");
                else if(mineField[p.x][p.y] == 9) gridPrint.content[p.x][p.y] = color.Red("*");
                else if(mineField[p.x][p.y] > 9) gridPrint.content[p.x][p.y] = "_";
                else gridPrint.content[p.x][p.y] = color.Blue("" + mineField[p.x][p.y]);
        }
        gridPrint.Generate();
    }

    private void NextTurn() {
        if(mineField[gridPrint.select.x][gridPrint.select.y] > 9) {
            mineField[gridPrint.select.x][gridPrint.select.y] -= 10;
            covered--;
        }
        if(mineField[gridPrint.select.x][gridPrint.select.y] == 9) {
            System.out.println(color.Red("  You Lose!  "));
            System.out.println();
            for(Point p : gridPrint.cells) if(mineField[p.x][p.y] > 9) mineField[p.x][p.y] -= 10;
            inGame = false;
            SetGrid();
        } else {
            new Sweep(gridPrint.select.x, gridPrint.select.y, false);
            if(covered == mines) {
                System.out.println(color.Green("  You Win!  "));
                System.out.println();
                for(Point p : gridPrint.cells) if(mineField[p.x][p.y] > 9) mineField[p.x][p.y] -= 10;
                inGame = false;
                SetGrid();
            }
        }
    }
}
