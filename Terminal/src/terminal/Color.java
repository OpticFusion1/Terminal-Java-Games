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

    static String color(String text, char color) {
        switch (color) {
            case 'b':
                return black(text);
            case 'r':
                return red(text);
            case 'g':
                return green(text);
            case 'y':
                return yellow(text);
            case 'l':
                return blue(text);
            case 'c':
                return cyan(text);
            case 'p':
                return purple(text);
            case 'w':
                return white(text);
        }
        return clear(text);
    }

    static void reset() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
