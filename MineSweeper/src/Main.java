import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Grid Size:");
        int size = Integer.valueOf(scanner.nextLine());
        int mines = size * size / 8;

        int[][] field = new int[size][size];
        for(int x = 0; x < size; x++) for(int y = 0; y < size; y++) field[x][y] = 0;
        for(int i = 0; i < mines; i++) {
            boolean unSet = true;
            while(unSet) {
                Random rand = new Random();
                int x = rand.nextInt(size);
                int y = rand.nextInt(size);
                if(field[x][y] != 9) {
                    unSet = false;
                    field[x][y] = 9;
                    if(y < size - 1) {
                        if(x > 0 && field[x - 1][y + 1] != 9) field[x - 1][y + 1]++;
                        if(field[x][y + 1] != 9) field[x][y + 1]++;
                        if(x < size - 1 && field[x + 1][y + 1] != 9) field[x + 1][y + 1]++;
                    }
                    if(x > 0 && field[x - 1][y] != 9) field[x - 1][y]++;
                    if(x < size - 1 && field[x + 1][y] != 9) field[x + 1][y]++;
                    if(y > 0) {
                        if(x > 0 && field[x - 1][y - 1] != 9) field[x - 1][y - 1]++;
                        if(field[x][y - 1] != 9) field[x][y - 1]++;
                        if(x < size - 1 && field[x + 1][y - 1] != 9) field[x + 1][y - 1]++;
                    }
                }
            }
        }
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.Derive(field, size, scanner);

        System.out.println("Enter to continue");
        scanner.nextLine();
        scanner.close();
        System.exit(0);
    }
}
