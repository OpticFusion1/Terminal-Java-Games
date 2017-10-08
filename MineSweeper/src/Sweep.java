
class Sweep {
    Sweep(int thisX, int thisY, boolean clear) {
        MineSweeper.visited[thisX][thisY] = true;
        for(int x = thisX - 1; x <= thisX + 1; x++) for(int y = thisY - 1; y <= thisY + 1; y++) if(MineSweeper.gridPrint.contains(x,y)) {
            if(MineSweeper.mineField[x][y] > 9 && clear) {
                MineSweeper.mineField[x][y] -= 10;
                MineSweeper.covered--;
            }
            if((MineSweeper.mineField[x][y] == 0 || MineSweeper.mineField[x][y] == 10) && !MineSweeper.visited[x][y]) new Sweep(x, y, true);
        }
    }
}
