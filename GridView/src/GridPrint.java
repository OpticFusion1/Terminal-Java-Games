import java.util.Scanner;

class GridPrint {
    String[][] content;
    String[] column;
    String[] row;
    Point select = new Point(0,0);
    Point max;
    Point min;
    Point range;
    Point[] cells;
    private int[] columnMax;
    private int rowMax;
    private Color color = new Color();

    GridPrint(int xSize, int ySize) {
        content = new String[xSize][ySize];
        column = new String[xSize];
        row = new String[ySize];
        cells = new Point[xSize * ySize];
        max = new Point(xSize,ySize);
        min = new Point(0,0);
        rowMax = 0;
        columnMax = new int[xSize];
        range = max;
        int i = 0;
        for(int x = 0; x < xSize; x++) for(int y = 0; y < ySize; y++) {
            cells[i] = new Point(x,y);
            i++;
        }
        for(Point p : cells) content[p.x][p.y] = "_";
        for(i = 0; i < column.length; i++) column[i] = "" + (char) (i + 65);
        for(i = 1; i <= row.length; i++) row[i - 1] = "" + i;
        Scale();
    }

    void Scale() {
        for(int x = 0; x < column.length; x++) {
            columnMax[x] = column[x].length();
            for(int y = 0; y < row.length; y++)
                if(columnMax[x] < content[x][y].length()) columnMax[x] = content[x][y].length();
        }
        for(String s : row) if(rowMax < s.length()) rowMax = s.length();
    }

    void Generate() {
        min = new Point(0, 0);
        if(select.x > range.x / 2) min.x = select.x - range.x / 2;
        if(min.x > column.length - range.x) min.x = column.length - range.x;
        if(select.y > range.y / 2) min.y = select.y - range.y / 2;
        if(min.y > row.length - range.y) min.y = row.length - range.y;
        max = new Point(min.x + range.x, min.y + range.y);

        System.out.print(color.Green + "#" + space(rowMax));
        for(int x = min.x; x < max.x; x++) System.out.print(column[x] + " " + space(columnMax[x] - column[x].length()));

        for(int y = min.y; y < max.y; y++) {
            System.out.println();
            System.out.print(color.Green(row[y]) + space(rowMax - row[y].length()));
            for(int x = min.x; x < max.x; x++) {
                if(y == select.y && x == select.x) System.out.print("[");
                else if(y == select.y && x > 0 && x == select.x + 1) System.out.print("]");
                else System.out.print(" ");
                System.out.print(content[x][y] + space(columnMax[x] - content[x][y].length()));
            }
            if(y == select.y && max.y - 1 == select.x) System.out.print("]");
            else System.out.print(" ");
            System.out.print(color.Green(row[y]));
        }
        System.out.println();
        System.out.print(color.Green + " " + space(rowMax));
        for(int x = min.x; x < max.x; x++) System.out.print(column[x] + " " + space(columnMax[x] - column[x].length()));
        System.out.println(color.Clear);
    }

    void Search(Scanner scanner) {
        boolean error = true;
        while(error) {
            System.out.print("Search for Cell:" + color.Green);
            String cell = scanner.nextLine().toLowerCase();
            System.out.println(color.Clear);

            select.y = 0;
            select.x = 0;
            boolean moving = true;
            error = false;
            if(cell.split(" ").length < 2) error = true;
            else {
                while(select.x < column.length && moving) {
                    if(!column[select.x].toLowerCase().contains(cell.split(" ")[0])) select.x++;
                    else moving = false;
                }
                if(moving) error = true;
                else {
                    moving = true;
                    while(select.y < row.length && moving) {
                        if(!row[select.y].toLowerCase().contains(cell.split(" ")[1])) select.y++;
                        else moving = false;
                    }
                }
            }

            if(moving || error) {
                error = true;
                System.out.println(color.Red("Error: try again"));
                Generate();
            }
        }
    }

    boolean contains(int x, int y) { return x >= 0 && x < column.length && y >= 0 && y < row.length;}

    private String space(int multiplier) {
        String s = "";
        for(int i = 0; i < multiplier; i++) s += " ";
        return s;
    }
}
