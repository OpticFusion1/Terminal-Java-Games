import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static GridPrint gridPrint = new GridPrint(8,8);
    static int[][] board = new int[8][8];
    public static void main(String[] args) {
        for(Point p: gridPrint.cells) board[p.x][p.y] = 0;
        for(Point p: gridPrint.cells) {
            switch(p.y) {
                case 0:
                    switch(p.x) {
                        case 0:case 7:
                            board[p.x][p.y] = 3;
                            break;
                        case 1:case 6:
                            board[p.x][p.y] = 5;
                            break;
                        case 2:case 5:
                            board[p.x][p.y] = 4;
                            break;
                        case 3:
                            board[p.x][p.y] = 2;
                            break;
                        case 4:
                            board[p.x][p.y] = 1;
                            break;
                    }
                    break;
                case 1:
                    board[p.x][p.y] = 6;
                    break;
                case 6:
                    board[p.x][p.y] = 12;
                    break;
                case 7:
                    switch(p.x) {
                        case 0:case 7:
                            board[p.x][p.y] = 9;
                            break;
                        case 1:case 6:
                            board[p.x][p.y] = 11;
                            break;
                        case 2:case 5:
                            board[p.x][p.y] = 10;
                            break;
                        case 3:
                            board[p.x][p.y] = 7;
                            break;
                        case 4:
                            board[p.x][p.y] = 8;
                            break;
                    }
                    break;
            }
        }
        MakeGrid();
        Scanner scanner = new Scanner(System.in);
        boolean inGame = true;
        while(inGame) {
            boolean empty = true;
            while(empty) {
                gridPrint.Search(scanner);
                if(board[gridPrint.select.x][gridPrint.select.y] > 0) empty = false;
            }
            List<Point> moves = FindMoves();
        }

    }
    private static List<Point> FindMoves() {
        List<Point> moves = new ArrayList<>();
        boolean white = true;
        int x = 0;
        int y = 0;
        if(board[gridPrint.select.x][gridPrint.select.y] > 7) white = false;
        switch(board[gridPrint.select.x][gridPrint.select.y]) {
            case 1:case 7:
                for(x = gridPrint.select.x - 1; x < gridPrint.select.x + 1; x++) for(y = gridPrint.select.y - 1; y < gridPrint.select.y + 1; y++) moves.add(new Point(x,y));
                break;
            case 2:case 8:case 3:case 9:
                x = gridPrint.select.x;
                while(board[x][gridPrint.select.y] == 0) {
                    x++;
                    moves.add(new Point(x,gridPrint.select.y));
                }
                x = gridPrint.select.x;
                while(board[x][gridPrint.select.y] == 0) {
                    x--;
                    moves.add(new Point(x,gridPrint.select.y));
                }

                y = gridPrint.select.y;
                while(board[gridPrint.select.x][y] == 0) {
                    y++;
                    moves.add(new Point(gridPrint.select.x,y));
                }
                y = gridPrint.select.y;
                while(board[gridPrint.select.x][y] == 0) {
                    y--;
                    moves.add(new Point(gridPrint.select.x,y));
                }
                if(board[gridPrint.select.x][gridPrint.select.y] == 3 || board[gridPrint.select.x][gridPrint.select.y] == 9) break;
            case 4:case 10:
                x = gridPrint.select.x;
                y = gridPrint.select.y;
                while(board[x][y] == 0) {
                    x++;
                    y++;
                    moves.add(new Point(x,y));
                }
                x = gridPrint.select.x;
                y = gridPrint.select.y;
                while(board[x][y] == 0) {
                    x--;
                    y++;
                    moves.add(new Point(x,y));
                }
                x = gridPrint.select.x;
                y = gridPrint.select.y;
                while(board[x][y] == 0) {
                    x++;
                    y--;
                    moves.add(new Point(x,y));
                }
                x = gridPrint.select.x;
                y = gridPrint.select.y;
                while(board[x][y] == 0) {
                    x--;
                    y--;
                    moves.add(new Point(x,y));
                }
                break;
            case 11:case 5:
                break;

        }
        return moves;
    }
    private static void MakeGrid() {
        Color color = new Color();
        for(Point p: gridPrint.cells) {
            switch(board[p.x][p.y]) {
                case 0:
                    if(p.x % 2 == p.y % 2) gridPrint.content[p.x][p.y] = color.Red("_");
                    else gridPrint.content[p.x][p.y] = "_";
                    break;
                case 1:
                    //White King\u9812
                    gridPrint.content[p.x][p.y] = "K";
                    break;
                case 2:
                    //White Queen\u9813
                    gridPrint.content[p.x][p.y] = "Q";
                    break;
                case 3:
                    //White Rook\u9814
                    gridPrint.content[p.x][p.y] = "R";
                    break;
                case 4:
                    //White Bishop\u9815
                    gridPrint.content[p.x][p.y] = "B";
                    break;
                case 5:
                    //White Knight\u9816
                    gridPrint.content[p.x][p.y] = "N";
                    break;
                case 6:
                    //White Pawn\u9817
                    gridPrint.content[p.x][p.y] = "P";
                    break;
                case 7:
                    //Black King\u9818
                    gridPrint.content[p.x][p.y] = color.Red("K");
                    break;
                case 8:
                    //Black Queen\u9819
                    gridPrint.content[p.x][p.y] = color.Red("Q");
                    break;
                case 9:
                    //Black Rook\u9820
                    gridPrint.content[p.x][p.y] = color.Red("R");
                    break;
                case 10:
                    //Black Bishop\u9821
                    gridPrint.content[p.x][p.y] = color.Red("B");
                    break;
                case 11:
                    //Black Knight\u9822
                    gridPrint.content[p.x][p.y] = color.Red("N");
                    break;
                case 12:
                    //Black Pawn\u9823
                    gridPrint.content[p.x][p.y] = color.Red("P");
                    break;
            }
        }
        gridPrint.Generate();
    }
}
