
import java.util.*;public class GridPrint
 {
    String[][] content;
    int selectX = 0;
    int selectY = 0;
    String[] column;
    String[] row;
    public void Generate() {
        System.out.println();
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
            System.out.print(" " + row[y]);
        }
        System.out.println();
        System.out.print("#  ");
        for(String s : column) System.out.print(s + " ");
        System.out.println();
    }
    public void Select(String cell) {
        selectX = 0;
        selectY = 0;
        while(selectX < column.length && !column[selectX].contains(cell.split(" ")[0])) selectX++;
        while(selectY < row.length && !row[selectY].contains(cell.split(" ")[1])) selectY++;
    }
    public boolean contains(int x, int y) {
        if(x >= 0 && x < column.length && y >= 0 && y < row.length) return true;
        else return false;
    }
}
