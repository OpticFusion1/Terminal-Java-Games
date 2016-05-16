
public class GridPrint {
    String[][] content;
    int selectX = 0;
    int selectY = 0;
    String[] column;
    String[] row;
    public void Generate() {
        System.out.print("#  ");
        for(String s : column) System.out.print(s + " ");
        for(int y = 0; y < row.length; y++) {
            System.out.println();
            System.out.print(row[y]);
            for(int x = 0; x < column.length; x++) {
                if(y == selectY && x == selectX) System.out.print("[");
                else if(y == selectY && x > 0 && x == selectX + 1) System.out.print("]");
                else System.out.print(" ");
                System.out.print(content[x][y]);
            }
            if(y == selectY && row.length - 1 == selectX) System.out.print("]");
            else System.out.print(" ");
            System.out.print(row[y]);
        }
        System.out.println();
        System.out.print("   ");
        for(String s : column) System.out.print(s + " ");
        System.out.println();
    }
    public boolean Select(String cell) {
        selectX = 0;
        selectY = 0;
        boolean moving = true;
        if(cell.split(" ").length < 2) return false;
        while(selectX < column.length && moving) {
            if(!column[selectX].contains(cell.split(" ")[0])) selectX++;
            else moving = false;
        }
        if(moving) return false;
        moving = true;
        while(selectY < row.length && moving) {
            if(!row[selectY].contains(cell.split(" ")[1])) selectY++;
            else moving = false;
        }
        return !moving;
    }
    public boolean contains(int x, int y) {
        if(x >= 0 && x < column.length && y >= 0 && y < row.length) return true;
        else return false;
    }
}
