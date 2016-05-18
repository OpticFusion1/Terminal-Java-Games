import java.util.*;

public class Main {
    static Color color = new Color();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Grid Size:" + color.Green);
        int size = Integer.valueOf(scanner.nextLine());
        int mines = size * size / 8;
        int[][] field = new int[size][size];
        System.out.println(color.Clear);

        for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) field[x][y] = 0;
        for(int i = 0; i < mines; i++) {
            boolean unSet = true;
            while(unSet) {
                Random rand = new Random();
                int randX = rand.nextInt(size);
                int randY = rand.nextInt(size);
                if(field[randX][randY] != 9) {
                    unSet = false;
                    field[randX][randY] = 9;
                    for(int x = randX - 1; x < randX + 2; x++) if(x >= 0 && x < size) for(int y = randY - 1; y <= randY + 1; y++) if(y >= 0 && y < size && field[x][y] != 9) field[x][y]++;
                }
            }
        }
        new MineSweeper(field, size, scanner);

        System.out.println("Press Enter to close" + color.Black);
        scanner.hasNext();
        scanner.close();
        System.exit(0);
    }
}
