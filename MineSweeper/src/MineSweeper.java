
import java.util.*;public class MineSweeper {
    int size;
    int mines;
    int covered;
    int[][] mineField;
    boolean inGame = true;
    GridPrint gridPrint = new GridPrint();
    public void Derive(int[][] field, int Size) {
        size = Size;
        mineField = field;
        mines = 0;
        covered = size * size;
        for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) {
                if(mineField[x][y] == 9) mines++;
                mineField[x][y] += 10;
            }

        String[] set =  new String[size];
        for(int i = 0; i < size; i++) set[i] = "" + (char)(i + 65);
        gridPrint.column = set;
        set =  new String[size];
        for(int i = 0; i < size; i++) {
            if(i > 9) set[i] = "" + i;
            else set[i] = i + " ";
        }
        gridPrint.row = set;
        while(inGame) {
            SetGrid();
            NextTurn();
        }
    }
    public void SetGrid() {
        gridPrint.content = new String[size][size];
        for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) {
                if(mineField[x][y] == 0) gridPrint.content[x][y] = ".";
                else if(mineField[x][y] == 9) gridPrint.content[x][y] = "*";
                else if(mineField[x][y] > 9) gridPrint.content[x][y] = "_";
                else gridPrint.content[x][y] = "" + mineField[x][y];
            }
        gridPrint.Generate();
    }
    public void NextTurn() {
        System.out.print("Select Cell: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        gridPrint.Select(s.toUpperCase());
        int i = mineField[gridPrint.selectX][gridPrint.selectY];
        i -= 10;
        if(i == 9) {
            System.out.println();
            System.out.println("  You Lose!  ");
            for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) if(mineField[x][y] > 9) mineField[x][y] -= 10;
            mineField[gridPrint.selectX][gridPrint.selectY] = i;
            inGame = false;
            SetGrid();
        } else {
            int x = gridPrint.selectX;
            int y = gridPrint.selectY;
            boolean moving = true;
            int dir = 1;
            while(dir != 0) {
                while(gridPrint.contains(gridPrint.selectX, y) && moving && mineField[gridPrint.selectX][y] != 19) {
                    if(mineField[gridPrint.selectX][y] > 9) {
                        mineField[gridPrint.selectX][y] -= 10;
                        covered--;
                    }
                    moving = true;
                    x = gridPrint.selectX + 1;
                    while(gridPrint.contains(x, y) && moving && mineField[x][y] != 19) {
                        if(mineField[x][y] > 9) {
                            mineField[x][y] -= 10;
                            covered--;
                        }
                        if(mineField[x][y] == 0) x++;
                        else moving = false;
                    }
                    x = gridPrint.selectX - 1;
                    moving = true;
                    while(gridPrint.contains(x, y) && moving && mineField[x][y] != 19) {
                        if(mineField[x][y] > 9) {
                            mineField[x][y] -= 10;
                            covered--;
                        }
                        if(mineField[x][y] == 0) x--;
                        else moving = false;
                    }
                    if(mineField[gridPrint.selectX][y] == 0) y += dir;
                    if(gridPrint.contains(gridPrint.selectX, y)) moving = true;
                }
                if(dir == 1) {
                    dir = -1;
                    y = gridPrint.selectY - 1;
                } else dir = 0;
            }
            if(covered == mines) {
                System.out.println();
                System.out.println("  You Win!  ");
                for(x = 0; x < size; x++) for(y = 0; y < size; y++) if(mineField[x][y] > 9) mineField[x][y] -= 10;
                inGame = false;
                SetGrid();
            }
        }
    }
}
