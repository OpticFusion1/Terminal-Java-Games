import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static GridPrint gridPrint = new GridPrint(8,8);
    private static int[][] board = new int[8][8];
    private static Color color = new Color();

    public static void main(String[] args) {
        boolean quantum = false;
        if(args.length > 0 && args[0].toUpperCase().equals("-Q")) {
            quantum = true;
            System.out.println(color.Purple("Quantum mode activated"));
        }

        //Create game board
        for(Point p: gridPrint.cells) {
            board[p.x][p.y] = 0;
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

        //Set up game play
        System.out.println("Red's Turn");
        gridPrint.Generate();
        Scanner scanner = new Scanner(System.in);
        boolean inGame = true;
        boolean whiteTurn = false;

        //Run game
        while(inGame) {
            boolean empty = true;
            //Choose piece to move
            while(empty) {
                gridPrint.Search(scanner);
                if(board[gridPrint.select.x][gridPrint.select.y] > 0) {
                    if((!whiteTurn && board[gridPrint.select.x][gridPrint.select.y] > 6) || (whiteTurn && board[gridPrint.select.x][gridPrint.select.y] < 7)) empty = false;
                    else System.out.print(color.Red("Wrong side"));
                } else System.out.print(color.Red("No piece there"));
            }
            MakeGrid();

            //Show all possible moves
            Point current = new Point(gridPrint.select.x,gridPrint.select.y);
            List<Point> moves = FindMoves();
            for(Point p : moves) {
                if(gridPrint.content[p.x][p.y].contains(color.Red)) gridPrint.content[p.x][p.y] = gridPrint.content[p.x][p.y].replace(color.Red,color.Green);
                else gridPrint.content[p.x][p.y] = color.Green(gridPrint.content[p.x][p.y]);
            }
            gridPrint.Generate();

            if(moves.size() > 0) {
                boolean legalMove = false;
                //Choose where to move
                while(!legalMove) {
                    gridPrint.Search(scanner);
                    for(Point p : moves) if(p.x == gridPrint.select.x && p.y == gridPrint.select.y) legalMove = true;
                    if(!legalMove) System.out.println(color.Red("Not legal move"));
                }
                //Check if end of game
                if(board[gridPrint.select.x][gridPrint.select.y] == 1 || board[gridPrint.select.x][gridPrint.select.y] == 7) inGame = false;
                //Relocate piece
                board[gridPrint.select.x][gridPrint.select.y] = board[current.x][current.y];

                if(quantum) {
                    board[gridPrint.select.x][gridPrint.select.y]--;
                    if(board[gridPrint.select.x][gridPrint.select.y] == 1) board[gridPrint.select.x][gridPrint.select.y] = 6;
                    if(board[gridPrint.select.x][gridPrint.select.y] == 7) board[gridPrint.select.x][gridPrint.select.y] = 11;
                }
                board[current.x][current.y] = 0;

                //Check who's turn
                if(!whiteTurn) {
                    if(inGame) {
                        System.out.println("White's Turn");
                        whiteTurn = true;
                    } else System.out.println("  Red Wins!  ");
                } else {
                    if(inGame) {
                        System.out.println(color.Red("Red's Turn"));
                        whiteTurn = false;
                    } else System.out.println("  White Wins!  ");
                }
            } else System.out.println(color.Red("No legal moves"));
            //Display game board
            if(inGame) {
                MakeGrid();
                gridPrint.Generate();
            }
        }

        //Pause until close
        System.out.println("Press Enter to close" + color.Black);
        scanner.nextLine();
        scanner.close();
        System.exit(0);
    }
    private static List<Point> FindMoves() {
        List<Point> moves = new ArrayList<>();
        boolean white = true;
        boolean moving = true;
        int x = gridPrint.select.x;
        int y = gridPrint.select.y;
        int dir = 1;
        if(board[gridPrint.select.x][gridPrint.select.y] > 6) white = false;

        switch(board[gridPrint.select.x][gridPrint.select.y]) {
            case 1:case 7:
                while(dir != 0) {
                    moves.add(new Point(x + dir,y + dir));
                    moves.add(new Point(x - dir,y + dir));
                    moves.add(new Point(x + dir,y));
                    moves.add(new Point(x,y + dir));
                    if(dir == 1) dir = -1;
                    else dir = 0;
                }
                break;
            case 2:case 8:case 3:case 9:
                while(dir != 0) {
                    while(gridPrint.contains(x,y) && moving) {
                        x += dir;
                        moves.add(new Point(x,y));
                        if(gridPrint.contains(x,y) && board[x][y] != 0) moving = false;
                    }
                    x = gridPrint.select.x;
                    moving = true;
                    while(gridPrint.contains(x,y) && moving) {
                        y += dir;
                        moves.add(new Point(x,y));
                        if(gridPrint.contains(x,y) && board[x][y] != 0) moving = false;
                    }
                    y = gridPrint.select.y;
                    moving = true;
                    if(dir == 1) dir = -1;
                    else dir = 0;
                }
                dir = 1;
                if(board[gridPrint.select.x][gridPrint.select.y] == 3 || board[gridPrint.select.x][gridPrint.select.y] == 9) break;
            case 4:case 10:
                while(dir != 0) {
                    while(gridPrint.contains(x,y) && moving) {
                        x += dir;
                        y += dir;
                        moves.add(new Point(x,y));
                        if(gridPrint.contains(x,y) && board[x][y] != 0) moving = false;
                    }
                    x = gridPrint.select.x;
                    y = gridPrint.select.y;
                    moving = true;
                    while(gridPrint.contains(x,y) && moving) {
                        x -= dir;
                        y += dir;
                        moves.add(new Point(x,y));
                        if(gridPrint.contains(x,y) && board[x][y] != 0) moving = false;
                    }
                    x = gridPrint.select.x;
                    y = gridPrint.select.y;
                    moving = true;
                    if(dir == 1) dir = -1;
                    else dir = 0;
                }
                break;
            case 11:case 5:
                while(dir != 0) {
                    moves.add(new Point(gridPrint.select.x + 2 * dir,gridPrint.select.y + dir));
                    moves.add(new Point(gridPrint.select.x + 2 * dir,gridPrint.select.y - dir));
                    moves.add(new Point(gridPrint.select.x + dir,gridPrint.select.y + 2 * dir));
                    moves.add(new Point(gridPrint.select.x - dir,gridPrint.select.y + 2 * dir));
                    if(dir == 1) dir = -1;
                    else dir = 0;
                }
                break;
            case 12:case 6:
                if(white) {
                    if(y == 1) moves.add(new Point(x, y + 2));
                    moves.add(new Point(x, y + 1));
                    while(dir != 0) {
                        if(gridPrint.contains(x + dir,y + 1) && board[x + dir][y + 1] != 0) moves.add(new Point(x + dir, y + 1));
                        if(dir == 1) dir = -1;
                        else dir = 0;
                    }
                } else {
                    if(y == 6) moves.add(new Point(x,y - 2));
                    moves.add(new Point(x, y - 1));
                    while(dir != 0) {
                        if(gridPrint.contains(x + dir,y - 1) && board[x + dir][y - 1] != 0) moves.add(new Point(x + dir,y - 1));
                        if(dir == 1) dir = -1;
                        else dir = 0;
                    }
                }
                break;
        }

        List<Point> remove = new ArrayList<>();
        for(Point p : moves) {
            if(!gridPrint.contains(p.x,p.y)) remove.add(p);
            else {
                if(white && board[p.x][p.y] < 7 && board[p.x][p.y] != 0) remove.add(p);
                if(!white && board[p.x][p.y] > 6) remove.add(p);
                if(p.x == gridPrint.select.x && p.y == gridPrint.select.y) remove.add(p);
            }
        }
        moves.removeAll(remove);
        return moves;
    }
    private static void MakeGrid() {
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
    }
}
