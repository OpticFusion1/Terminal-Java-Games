import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVMain {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Color color = new Color();
        //Locating chosen file
        if(args.length > 0) {
            String path = args[0];
            if(args[0].equals("-Q")) {
                path = "";
                boolean moving = false;
                boolean space = false;
                for(String s : args) {
                    if(moving && !s.equals("-Q")) {
                        if(space) path += " ";
                        path += s;
                        space = true;
                    }
                    else if(s.equals("-Q") && !moving) moving = true;
                    else if(s.equals("-Q") && moving) moving = false;
                }
            }
            File file = new File(path);
            try {
                Scanner fileScanner = new Scanner(file);
                //Read other lines
                List<String[]> allCells = new ArrayList<>();
                while(fileScanner.hasNextLine()) allCells.add(fileScanner.nextLine().split(","));
                //Limit grid size
                Point range = new Point(0,0);
                //if(args.length > 1) range.x = Integer.valueOf(args[1]);
                //if(args.length > 2) range.y = Integer.valueOf(args[2]);
                ShowFile(allCells,range);
            } catch(FileNotFoundException e) {
                System.out.println(color.Red("Error, no file found at: " + path));
            }
        } else System.out.println(color.Red("Error, no file chosen"));

        //Pause until close
        System.out.println("Press Enter to close" + color.Black);
        scanner.nextLine();
        scanner.close();
        System.exit(0);
    }
    private static void ShowFile(List<String[]> allCells, Point range) {
        //Creating display grid
        GridPrint gridPrint = new GridPrint(allCells.get(0).length - 1, allCells.size() - 1);
        for(Point p : gridPrint.cells) {
            if(p.y == 0 && p.x + 1 < allCells.get(0).length) gridPrint.column[p.x] = allCells.get(0)[p.x];
            if(p.y + 1 < allCells.size()) gridPrint.content[p.x][p.y] = allCells.get(p.y + 1)[p.x];
        }
        //Set range
        boolean zoomed = false;
        if(range.x > 0) {
            gridPrint.range.x = range.x;
            zoomed = true;
        }
        if(range.y > 0) {
            gridPrint.range.y = range.y;
            zoomed = true;
        }
        //Show grid
        gridPrint.Scale();
        gridPrint.Generate();
        //Select a cell
        if(zoomed) while(gridPrint.select.x != 1 || gridPrint.select.y != 1) {
            gridPrint.Search(scanner);
            gridPrint.Generate();
        }
    }
}
