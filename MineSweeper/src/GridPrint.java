class GridPrint {
    boolean searchLock = true;
    String[][] content;
    String[] column;
    String[] row;
    int selectX = 0;
    int selectY = 0;
    private Color color = Main.color;

    void Generate() {
        System.out.print(color.Green + "#  ");
        for(String s : column) System.out.print(s + " ");
        for(int y = 0; y < row.length; y++) {
            System.out.println();
            System.out.print("\u001B[32m" + row[y] + "\u001B[0m");
            for(int x = 0; x < column.length; x++) {
                if(y == selectY && x == selectX) System.out.print("[");
                else if(y == selectY && x > 0 && x == selectX + 1) System.out.print("]");
                else System.out.print(" ");
                System.out.print(content[x][y]);
            }
            if(y == selectY && row.length - 1 == selectX) System.out.print("]");
            else System.out.print(" ");
            System.out.print(color.Green(row[y]));
        }
        System.out.println();
        System.out.print("   ");
        for(String s : column) System.out.print(color.Green(s + " "));
        System.out.println();
    }

    boolean Select(String cell) {
        cell = cell.toLowerCase();
        selectX = 0;
        selectY = 0;
        boolean moving = true;
        if(cell.split(" ").length < 2) {
            if(searchLock) return false;
            else {
                while(contains(0,selectY)) {
                    selectX = 0;
                    while(contains(selectX,selectY)) {
                        if(content[selectX][selectY].toLowerCase().contains(cell)) return true;
                        selectX++;
                    }
                    selectY++;
                }
                return false;
            }
        }
        while(selectX < column.length && moving) {
            if(!column[selectX].toLowerCase().contains(cell.split(" ")[0])) selectX++;
            else moving = false;
        }
        if(moving) return false;
        moving = true;
        while(selectY < row.length && moving) {
            if(!row[selectY].toLowerCase().contains(cell.split(" ")[1])) selectY++;
            else moving = false;
        }
        return !moving;
    }

    boolean contains(int x, int y) {
        return x >= 0 && x < column.length && y >= 0 && y < row.length;
    }
}
