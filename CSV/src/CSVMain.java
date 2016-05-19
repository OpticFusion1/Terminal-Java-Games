import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVMain {
    public static void main(String[] args) {
        Color color = new Color();
        Scanner scanner = new Scanner(System.in);
        if(args.length > 0) {
            File file = new File(args[0]);
            try {
                Scanner fileScanner = new Scanner(file);
                String[] firstLine = fileScanner.nextLine().split(",");
                List<String[]> allCells = new ArrayList<>();
                while(fileScanner.hasNextLine()) allCells.add(fileScanner.nextLine().split(","));
                GridPrint gridPrint = new GridPrint(firstLine.length, allCells.size());
                gridPrint.column = firstLine;
                for(Point p : gridPrint.cells) gridPrint.content[p.x][p.y] = allCells.get(p.y)[p.x];
                if(args.length > 1) gridPrint.range.x = Integer.valueOf(args[1]);
                if(args.length > 2) gridPrint.range.y = Integer.valueOf(args[2]);
                gridPrint.Generate();
                while(gridPrint.select.x != 1 || gridPrint.select.y != 1) {
                    if(gridPrint.Search(scanner)) {
                        System.out.println(gridPrint.select.Print() + " Showing: " + gridPrint.min.Print() + '-' + gridPrint.max.Print());
                        gridPrint.Generate();
                    }
                    else System.out.println(color.Red("Error: try again"));
                }
            } catch(FileNotFoundException e) {
                System.out.println(color.Red("Error, no file found"));
            }
        } else System.out.println(color.Red("Error, no file chosen"));

        System.out.println("Press Enter to close" + color.Black);
        scanner.nextLine();
        scanner.close();
        System.exit(0);
    }
}
