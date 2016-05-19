import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVMain {
    public static void main(String[] args) {
        Color color = new Color();
        if(args.length > 0) {
            File file = new File(args[0]);
            try {
                Scanner scanner = new Scanner(file);
                String[] firstLine = scanner.nextLine().split(",");
                List<String[]> allCells = new ArrayList<>();
                while(scanner.hasNextLine()) allCells.add(scanner.nextLine().split(","));
                GridPrint gridPrint = new GridPrint(firstLine.length, allCells.size() - 1);
                gridPrint.column = firstLine;
                for(int i = 0; i < allCells.size(); i++) gridPrint.content[i] = allCells.get(i);
                gridPrint.Generate();
            } catch(FileNotFoundException e) {
                System.out.println(color.Red("Error, no file found"));
            }
        } else System.out.println(color.Red("Error, no file chosen"));
    }
}
