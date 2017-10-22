package terminal;

class Color {
    static final String CLEAR = "\u001B[0m";
    static final String BLACK = "\u001B[90m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[92m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final String WHITE = "\u001B[39m";

    static String clear(String text) {
        return CLEAR + text;
    }
    static String black(String text) {
        return BLACK + text + CLEAR;
    }
    static String red(String text) {
        return RED + text + CLEAR;
    }
    static String green(String text) {
        return GREEN + text + CLEAR;
    }
    static String yellow(String text) {
        return YELLOW + text + CLEAR;
    }
    static String blue(String text) {
        return BLUE + text + CLEAR;
    }
    static String cyan(String text) {
        return CYAN + text + CLEAR;
    }
    static String purple(String text) {
        return PURPLE + text + CLEAR;
    }
    static String white(String text) {
        return WHITE + text + CLEAR;
    }

    static void reset() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static String color(String text, char color) {
        //Set color based on char
        switch (color) {
            case 'b':
                return Color.black(text);
            case 'r':
                return Color.red(text);
            case 'g':
                return Color.green(text);
            case 'y':
                return Color.yellow(text);
            case 'l':
                return Color.blue(text);
            case 'c':
                return Color.cyan(text);
            case 'p':
                return Color.purple(text);
            case 'w':
                return Color.white(text);
        }
        return clear(text);
    }
}
