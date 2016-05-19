import java.util.*;

public class Main {
    static Color color = new Color();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Grid Size:" + color.Green);
        int size = Integer.valueOf(scanner.nextLine());
        System.out.println(color.Clear);
        new MineSweeper(size, scanner);

        System.out.println("Press Enter to close" + color.Black);
        scanner.hasNext();
        scanner.close();
        System.exit(0);
    }
}
